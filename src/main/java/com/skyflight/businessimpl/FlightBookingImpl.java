package com.skyflight.businessimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.SMS;
import com.skyflight.BookingCustomModel.AdditionalSSRDetails;
import com.skyflight.BookingCustomModel.AirBookResponse;
import com.skyflight.BookingCustomModel.BookedSegments;
import com.skyflight.BookingCustomModel.BookingRequest;
import com.skyflight.BookingCustomModel.BookingSegmentDetail;
import com.skyflight.BookingCustomModel.CancelRequest;
import com.skyflight.BookingCustomModel.CancelResponse;
import com.skyflight.BookingCustomModel.CancelResponsedetails;
import com.skyflight.BookingCustomModel.CustomerDetail;
import com.skyflight.BookingCustomModel.Fare;
import com.skyflight.BookingCustomModel.Meal;
import com.skyflight.BookingCustomModel.Passenger;
import com.skyflight.BookingCustomModel.PassengerDetails;
import com.skyflight.BookingCustomModel.Seat;
import com.skyflight.BookingCustomModel.TicketDetails;
import com.skyflight.MealBaggagesCustomModel.Baggages;
import com.skyflight.servicedao.FlightBookingDao;
import com.skyflight.servicedao.FlightFareDao;
import com.skyflight.businessdao.FlightBookingImplDao;
import com.skyflight.model.Airline;
import com.skyflight.model.Airport_code;
import com.skyflight.model.BaggageDetails;
import com.skyflight.model.CancellationDetail;
import com.skyflight.model.FlightFare;
import com.skyflight.model.Flightbooking;
import com.skyflight.model.Flightdetail;
import com.skyflight.model.GstFlight;
import com.skyflight.model.MealDetails;
import com.skyflight.model.PassengerDetail;
import com.skyflight.model.Passengerfare;
import com.skyflight.model.SeatDetails;
import com.skyflight.servicedao.FlightdetailDao;
import com.skyflight.servicedao.GstFlightDao;
import com.skyflight.servicedao.MealDetailsDao;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.servicedao.AirportDao;
import com.skyflight.servicedao.BaggageDao;
import com.skyflight.servicedao.CancellationDetailDao;
import com.skyflight.servicedao.PassengerdetailDao;
import com.skyflight.servicedao.PassengerfareDao;
import com.skyflight.servicedao.SeatDetailsDao;
import com.skyflight.webserver.WebServerClient;

@Service("FlightBookingDao")
public class FlightBookingImpl implements FlightBookingImplDao{
	
	private static final Logger log = Logger.getLogger(FlightBookingImplDao.class);
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired CommissionService commissionService;
	@Autowired FlightBookingDao flightBookingDao;
	@Autowired FlightdetailDao flightdetailDao;
	@Autowired PassengerdetailDao passengerdetaildao;
	@Autowired PassengerfareDao passengerfaredao;
	@Autowired FlightFareDao FlightFareDao;
	@Autowired MealDetailsDao MealDetailsDao;
	@Autowired BaggageDao BaggageDao;
	@Autowired SeatDetailsDao SeatDetailsDao;
	@Autowired AirportDao airportdao;
	@Autowired GstFlightDao GstFlightDao;
	@Autowired CancellationDetailDao CancellationDetailDao;
	@Autowired UserDao userDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired AirlineDao AirlineDao;
	
	@Override
	@SuppressWarnings({"unchecked"})
	public Map<String, Object> Flightbookrequest(User userDetails,Map<String,Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		log.warn("Flightbookrequest request:::"+request);
		boolean flag=false;
		Map<String, String> parameters = new HashMap<String, String>();
		AirBookResponse output=new AirBookResponse();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		Map<String, Object> prevrecord = (Map<String, Object>) request.get("prevrecord");
		Map<String, Object> detail=(Map<String, Object>) request.get("detail");
		 boolean isDomestic=(boolean) detail.get("isDomestic");
		Map<String, Object> requestDetails=(Map<String, Object>) prevrecord.get("requestDetails");
		Map<String, Object> Taxoutput=(Map<String, Object>) prevrecord.get("Taxoutput");
		System.out.println("mealbag::"+request.get("mealbag"));
		List<Map<String, Object>> trip1meal=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> trip2meal=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> trip1bag=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> trip2bag=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> mealbags=new ArrayList<Map<String, Object>>();
		if(request.containsKey("mealbag")){
			 mealbags=(List<Map<String, Object>>) request.get("mealbag");
		}
		
		
		 Map<String, Object> searchDetails = (Map<String, Object>)prevrecord.get("searchDetails");
		 ArrayList<Map<String, Object>> FlightSegmentsarray=(ArrayList<Map<String, Object>>) searchDetails.get("FlightSegments");
		 
		 //--------------------------------BookingSegmentDetail--------------------------------------------------------
		 List<BookingSegmentDetail> listbookseg=new ArrayList<BookingSegmentDetail>();
		 String tripfl="";
			String tripfl2="";
		 for(int n=0;n<FlightSegmentsarray.size();n++){
			 Map<String, Object> flightsegobj=FlightSegmentsarray.get(n);
			// JSONObject flightsegobj=(JSONObject) FlightSegmentsarray.get(n);
			 BookingSegmentDetail bsdt=new BookingSegmentDetail();
			 if(searchDetails.get("IsLCC").toString().equalsIgnoreCase("true")){
				 bsdt.setIsLCC(true);
			 }else{
				 bsdt.setIsLCC(false);
			 }
			 
			 bsdt.setResultIndex(searchDetails.get("ResultIndex").toString());
			 int trip=(int)Double.parseDouble(flightsegobj.get("TripIndicator").toString());
			 bsdt.setTripIndicator(Integer.toString(trip));
			 bsdt.setSegmentIndicator(Integer.toString((int)Double.parseDouble(flightsegobj.get("SegmentIndicator").toString())));
			 bsdt.setAirlineCode(flightsegobj.get("AirlineCode").toString());
			 bsdt.setOrigin(flightsegobj.get("Origin").toString());
			 bsdt.setDestination(flightsegobj.get("Destination").toString());
			 bsdt.setFlightNumber(flightsegobj.get("FlightNumber").toString());
			 bsdt.setClassCode(flightsegobj.get("FareClass").toString());
			 bsdt.setFlightId(flightsegobj.get("FlightId").toString());
			 bsdt.setSupplierId(flightsegobj.get("SupplierId").toString());
			 listbookseg.add(bsdt);
			 tripfl=flightsegobj.get("AirlineCode").toString();
		 }
		 if(prevrecord.containsKey("returnDetail")){
			 Map<String, Object> returnDetail = (Map<String, Object>)prevrecord.get("returnDetail");
			 ArrayList<Map<String, Object>> FlightSegmentsarrayre=(ArrayList<Map<String, Object>>) returnDetail.get("FlightSegments");
			 for(int n=0;n<FlightSegmentsarrayre.size();n++){
				 Map<String, Object> flightsegobj=FlightSegmentsarrayre.get(n);
				 
				 BookingSegmentDetail bsdt=new BookingSegmentDetail();
				 if(returnDetail.get("IsLCC").toString().equalsIgnoreCase("true")){
					 bsdt.setIsLCC(true);
				 }else{
					 bsdt.setIsLCC(false);
				 }
				 bsdt.setResultIndex(returnDetail.get("ResultIndex").toString());
				 int trip=(int)Double.parseDouble(flightsegobj.get("TripIndicator").toString());
				 bsdt.setTripIndicator(Integer.toString(trip));
				 bsdt.setSegmentIndicator(Integer.toString((int)Double.parseDouble(flightsegobj.get("SegmentIndicator").toString())));
				 bsdt.setAirlineCode(flightsegobj.get("AirlineCode").toString());
				 bsdt.setOrigin(flightsegobj.get("Origin").toString());
				 bsdt.setDestination(flightsegobj.get("Destination").toString());
				 bsdt.setFlightNumber(flightsegobj.get("FlightNumber").toString());
				 bsdt.setClassCode(flightsegobj.get("FareClass").toString());
				 bsdt.setFlightId(flightsegobj.get("FlightId").toString());
				 bsdt.setSupplierId(flightsegobj.get("SupplierId").toString());
				 listbookseg.add(bsdt);
				 tripfl2=flightsegobj.get("AirlineCode").toString();
			 }
		 }
		 
		 
		
		if(!mealbags.isEmpty()){
			for(int i=0;i<mealbags.size();i++){
				Map<String, Object> mealobj=mealbags.get(i);
				if(mealobj.get("TripIndicator").equals("1")){
					if(mealobj.containsKey("Meals")){
						trip1meal=(List<Map<String, Object>>) mealobj.get("Meals");
					}
					if(mealobj.containsKey("Baggages")){
					trip1bag= (List<Map<String, Object>>) mealobj.get("Baggages");
					}
				}else{
					if(mealobj.containsKey("Meals")){
					trip2meal=(List<Map<String, Object>>) mealobj.get("Meals");
					}
					if(mealobj.containsKey("Baggages")){
					trip2bag= (List<Map<String, Object>>) mealobj.get("Baggages");
					}
				}
			}
		}
		
		System.out.println("mealbags::"+mealbags);
		String[] source = requestDetails.get("source").toString().split(",");
		int adultno=(int)((double)requestDetails.get("adult"));
		int childno=(int)((double)requestDetails.get("child"));
		int infantno=(int)((double)requestDetails.get("infant"));
		int totalpassenger=adultno+childno+infantno;
		//--------------------------------passengerdetail--------------------------------------------------------
		double mealamount=0.0;
		double baggageamount=0.0;
		 Map<String, Object> passengerdetail = (Map<String, Object>) request.get("passengerdetail");
		 System.out.println("passengerdetail::::"+passengerdetail);
		 Map<String, Object> adultdetail = (Map<String, Object>)passengerdetail.get("adultdetail");
		
		 List<String> adultfname = new ArrayList<>();
		 adultfname = (List<String>) adultdetail.get("adultfname");
		 List<String> adultlname = new ArrayList<>();
		 adultlname = (List<String>) adultdetail.get("adultlname");
		 List<String> adulttitle = (List<String>) adultdetail.get("adulttitle");
		 List<String> adultdob =  (List<String>) adultdetail.get("adultDOB");
		 
		 String customername = adultfname.get(0)+" "+adultlname.get(0);
		 System.out.println("customername::"+customername);
		 List<PassengerDetails> listpassen=new ArrayList<PassengerDetails>();
		 for(int i=0;i<adultno;i++){
			 PassengerDetails pd=new PassengerDetails();
			 pd.setPassengerType("Adult");
			 pd.setTitle(adulttitle.get(i));
			 if(i==0){
				 pd.setIsLeadPax(true);
			 }else{
				 pd.setIsLeadPax(false);
			 }
			 pd.setFirstName(adultfname.get(i));
			 pd.setLastName(adultlname.get(i));
			 if(adulttitle.get(i).equalsIgnoreCase("Mr")){
				 pd.setGender("Male");
					}else{
						pd.setGender("Female");	
					}
			 pd.setEmail(detail.get("emailid").toString());
			 pd.setMobile(detail.get("mobileNo").toString());
			 pd.setCity(detail.get("city").toString());
			 String adob = adultdob.get(i);
			 Date date = sdf.parse(adob);
			 pd.setDateofBirth(sdf2.format(date));
			 pd.setPaxNumber("");
			
			 if(!isDomestic){
				 List<String> adultPassport =  (List<String>) adultdetail.get("adultPassport");
				 pd.setPassportNo(adultPassport.get(i));
			 }
			
/*			 List<String> adultMeal =(List<String>) adultdetail.get("adultLMeal");
			 List<Meal> listmeal=new ArrayList<Meal>();
			 if(adultMeal.size()!=0){
				 String selectmeal[]=adultMeal.get(i).split("~");
				 for(int j=0;j<trip1meal.size();j++){
					 if(trip1meal.get(j).get("Code").equals(selectmeal[2])){
						 System.out.println("::::::::::::::::::::::::::::::::::::::::"+trip1meal.get(j).get("Code"));
						 Meal ml=new Meal();
						 ml.setTripIndicator("1"); 
						 ml.setCode(selectmeal[2]);
						 ml.setDescription(trip1meal.get(j).get("Description").toString());
						 ml.setAmount(selectmeal[1]);
						 if(trip1meal.get(j).containsKey("AirlineDescription")){
							 ml.setAirlineDescription(selectmeal[0]);
						 }
						 if(trip1meal.get(j).containsKey("Origin")){
							 ml.setOrigin(trip1meal.get(j).get("Origin").toString());
						 }
						 if(trip1meal.get(j).containsKey("Destination")){
							 ml.setDestination(trip1meal.get(j).get("Destination").toString());
						 }
						 if(trip1meal.get(j).containsKey("FlightNumber")){
							 ml.setFlightNumber(trip1meal.get(j).get("FlightNumber").toString());
						 }
						 if(trip1meal.get(j).containsKey("WayType")){
							 ml.setWayType(trip1meal.get(j).get("WayType").toString()); 
						 }
						 
						 ml.setAirlineCode(tripfl);
						 listmeal.add(ml);
						 mealamount=mealamount+Double.parseDouble(selectmeal[1]);
					 }
				 }
				
				 
			 }
			 List<Baggages> listBaggages=new ArrayList<Baggages>();
			 List<String> adultLBaggage =(List<String>) adultdetail.get("adultLBaggage");
			 if(!adultLBaggage.isEmpty()){
				 String selectadultLBaggage[]=adultLBaggage.get(i).split("~");
					for(int k=0;k<trip1bag.size();k++){
						if(trip1bag.get(k).get("code").equals(selectadultLBaggage[2])){
							 Baggages bg=new Baggages();
							 bg.setTripIndicator("1");
							 if(trip1bag.get(k).containsKey("WayType")){
								 bg.setWayType(trip1bag.get(k).get("WayType").toString());
							 }
							 bg.setCode(selectadultLBaggage[2]);
							 bg.setAmount(Integer.parseInt(selectadultLBaggage[1]));
							 baggageamount=baggageamount+Double.parseDouble(selectadultLBaggage[1]);
							 bg.setDescription(selectadultLBaggage[0]);
							 listBaggages.add(bg);
						}
					}
			 }
			
		
			 if(prevrecord.get("type").equals("R")){
				 List<String> adultMeal2 =(List<String>) adultdetail.get("adultLMeal2");
		
				 if(adultMeal2.size()!=0){
					  String selectmeal[]=adultMeal2.get(i).split("~");
					 for(int j=0;j<trip2meal.size();j++){
						 if(trip2meal.get(j).get("Code").equals(selectmeal[2])){
				
						 Meal ml=new Meal();
						 ml.setTripIndicator("2"); 
						 ml.setCode(selectmeal[2]);
						 ml.setDescription(trip2meal.get(j).get("Description").toString());
						 ml.setAmount(selectmeal[1]);
						 mealamount=mealamount+Double.parseDouble(selectmeal[1]);
						 ml.setOrigin(trip2meal.get(j).get("Origin").toString());
						 ml.setDestination(trip2meal.get(j).get("Destination").toString());
						 ml.setFlightNumber(trip2meal.get(j).get("FlightNumber").toString());
						 ml.setWayType(trip2meal.get(j).get("WayType").toString());
						 ml.setAirlineCode(tripfl2);
						 listmeal.add(ml);
						 }
					 }
				 }
				 List<String> adultLBaggage2 =(List<String>) adultdetail.get("adultLBaggage2");
				 if(!adultLBaggage2.isEmpty()){
					 String selectadultLBaggage2[]=adultLBaggage2.get(i).split("~");
					 for(int k=0;k<trip2bag.size();k++){
							if(trip2bag.get(k).get("code").equals(selectadultLBaggage2[2])){
								 Baggages bgs=new Baggages();
								 bgs.setTripIndicator("2");
								 bgs.setWayType(trip2bag.get(k).get("WayType").toString());
								 bgs.setCode(selectadultLBaggage2[2]);
								 bgs.setAmount(Integer.parseInt(selectadultLBaggage2[1]));
								 baggageamount=baggageamount+Double.parseDouble(selectadultLBaggage2[1]);
								 bgs.setDescription(selectadultLBaggage2[0]);
								 listBaggages.add(bgs);
							}
					 }
				 }
				 
			 }*/
			 
			
			 List<Seat> listSeat=new ArrayList<Seat>();
			// pd.setSeat(listSeat);
			/* pd.setMeal(listmeal);
			 pd.setBaggages(listBaggages);*/
			 listpassen.add(pd);
		 }
		
		 if(childno!=0){
			 Map<String, Object> childdetail = (Map<String, Object>)passengerdetail.get("childdetail");
			 System.out.println("childdetail::"+childdetail);
			 List<String> childfname =(List<String>) childdetail.get("childfname");
			 List<String> childlname =(List<String>) childdetail.get("childlname");
			 List<String> childtitle = (List<String>) childdetail.get("childtitle");
			 List<String> childlDob = (List<String>) childdetail.get("childlDob");
			 
			 for(int i=0;i<childno;i++){
				 PassengerDetails pd=new PassengerDetails();
				 pd.setPassengerType("Child");
				 pd.setTitle(childtitle.get(i));
					 pd.setIsLeadPax(false);
				
				 pd.setFirstName(childfname.get(i));
				 pd.setLastName(childlname.get(i));
				 if(adulttitle.get(i).equalsIgnoreCase("Master")){
					 pd.setGender("Male");
						}else{
							pd.setGender("Female");	
						}
				 pd.setEmail(detail.get("emailid").toString());
				 pd.setMobile(detail.get("mobileNo").toString());
				 pd.setCity(detail.get("city").toString());
				 pd.setPaxNumber("");
				 String cdob = childlDob.get(i);
				 Date date = sdf.parse(cdob);
				 pd.setDateofBirth(sdf2.format(date));
				 if(!isDomestic){
					 List<String> childPassport =  (List<String>) adultdetail.get("childPassport");
					 pd.setPassportNo(childPassport.get(i));
				 }
				/* 
				 List<String> adultMeal =(List<String>) childdetail.get("childLmeal");
				 System.out.println("childLmeal:::"+adultMeal);
				 List<Meal> listmeal=new ArrayList<Meal>();
				 if(adultMeal.size()!=0){

					 String selectmeal[]=adultMeal.get(i).split("~");
					 for(int j=0;j<trip1meal.size();j++){
						 if(trip1meal.get(j).get("Code").equals(selectmeal[2])){
							 Meal ml=new Meal();
							 ml.setTripIndicator("1"); 
							 ml.setCode(selectmeal[2]);
							 ml.setDescription(trip1meal.get(j).get("Description").toString());
							 ml.setAmount(selectmeal[1]);
							 ml.setAirlineDescription(selectmeal[0]);
							 ml.setOrigin(trip1meal.get(j).get("Origin").toString());
							 ml.setDestination(trip1meal.get(j).get("Destination").toString());
							 ml.setFlightNumber(trip1meal.get(j).get("FlightNumber").toString());
							 ml.setWayType(trip1meal.get(j).get("WayType").toString());
							 ml.setAirlineCode(tripfl);
							 listmeal.add(ml);
							 mealamount=mealamount+Double.parseDouble(selectmeal[1]);
						 }
					 }
				
				 }
				 System.out.println("listmeal::"+listmeal);
				 List<Baggages> listBaggages=new ArrayList<Baggages>();
				 List<String> adultLBaggage =(List<String>) childdetail.get("childLbag");
				 if(!adultLBaggage.isEmpty()){
					 String selectadultLBaggage[]=adultLBaggage.get(i).split("~");
						for(int k=0;k<trip1bag.size();k++){
							if(trip1bag.get(k).get("code").equals(selectadultLBaggage[2])){
								 Baggages bg=new Baggages();
								 bg.setTripIndicator("1");
								 bg.setWayType(trip1bag.get(k).get("WayType").toString());
								 bg.setCode(selectadultLBaggage[2]);
								 bg.setAmount(Integer.parseInt(selectadultLBaggage[1]));
								 baggageamount=baggageamount+Double.parseDouble(selectadultLBaggage[1]);
								 bg.setDescription(selectadultLBaggage[0]);
								 listBaggages.add(bg);
							}
						}
				 }
				 
			
				 
				 if(prevrecord.get("type").equals("R")){
					 List<String> adultMeal2 =(List<String>) childdetail.get("childLmeal2");
					 if(adultMeal2.size()!=0){
						  String selectmeal[]=adultMeal2.get(i).split("~");
						 for(int j=0;j<trip2meal.size();j++){
							 if(trip2meal.get(j).get("Code").equals(selectmeal[2])){
					
					 Meal ml=new Meal();
					 ml.setTripIndicator("2"); 
					 ml.setCode(selectmeal[2]);
					 ml.setDescription(trip2meal.get(j).get("Description").toString());
					 ml.setAmount(selectmeal[1]);
					 mealamount=mealamount+Double.parseDouble(selectmeal[1]);
					 ml.setOrigin(trip2meal.get(j).get("Origin").toString());
					 ml.setDestination(trip2meal.get(j).get("Destination").toString());
					 ml.setFlightNumber(trip2meal.get(j).get("FlightNumber").toString());
					 ml.setWayType(trip2meal.get(j).get("WayType").toString());
					 ml.setAirlineCode(tripfl2);
					 listmeal.add(ml);
							 }
						 }
					 }
					 
					 
					 List<String> adultLBaggage2 =(List<String>) childdetail.get("childLbag2");
					 if(!adultLBaggage2.isEmpty()){
					 String selectadultLBaggage2[]=adultLBaggage2.get(i).split("~");
					 for(int k=0;k<trip2bag.size();k++){
							if(trip2bag.get(k).get("code").equals(selectadultLBaggage2[2])){
							 Baggages bgs=new Baggages();
							 bgs.setTripIndicator("2");
							 bgs.setWayType(trip2bag.get(k).get("WayType").toString());
							 bgs.setCode(selectadultLBaggage2[2]);
							 bgs.setAmount(Integer.parseInt(selectadultLBaggage2[1]));
							 baggageamount=baggageamount+Double.parseDouble(selectadultLBaggage2[1]);
							 bgs.setDescription(selectadultLBaggage2[0]);
							 listBaggages.add(bgs);
							}
					 }
				 }
					 
					
				 }
				 */
				 
				 List<Seat> listSeat=new ArrayList<Seat>();
			//	 pd.setSeat(listSeat);
				 /*pd.setMeal(listmeal);
				 pd.setBaggages(listBaggages);*/
				 listpassen.add(pd);
			 }
		 }
		 if(infantno!=0){
			 Map<String, Object> infantdetail = (Map<String, Object>)passengerdetail.get("infantdetail");
			 List<String> infantfname =(List<String>) infantdetail.get("infantfname");
			 List<String> infantlname =(List<String>) infantdetail.get("infantlname");
			 List<String> infanttitle = (List<String>) infantdetail.get("infanttitle");
			 List<String> infantDOB = (List<String>) infantdetail.get("infantDOB");
			 for(int i=0;i<childno;i++){
				 PassengerDetails pd=new PassengerDetails();
				 pd.setPassengerType("Infant");
				 pd.setTitle(infanttitle.get(i));
			
					 pd.setIsLeadPax(false);
			
				 pd.setFirstName(infantfname.get(i));
				 pd.setLastName(infantlname.get(i));
				 if(adulttitle.get(i).equalsIgnoreCase("Master")){
					 pd.setGender("Male");
						}else{
							pd.setGender("Female");	
						}
				 pd.setEmail(detail.get("emailid").toString());
				 pd.setMobile(detail.get("mobileNo").toString());
				 pd.setCity(detail.get("city").toString());
				 pd.setPaxNumber("");
				 String idob = infantDOB.get(i);
				 Date date = sdf.parse(idob);
				 pd.setDateofBirth(sdf2.format(date));
				 if(!isDomestic){
					 List<String> infantPassport =  (List<String>) adultdetail.get("infantPassport");
					 pd.setPassportNo(infantPassport.get(i));
				 }
				/* List<Meal> listmeal=new ArrayList<Meal>();
				 List<Seat> listSeat=new ArrayList<Seat>();
				 pd.setSeat(listSeat);
				 pd.setMeal(listmeal);*/
				 listpassen.add(pd);
			 }
		 } 
		 System.out.println("listpassen::"+listpassen);
		
		 System.out.println("listbookseg::"+listbookseg);
		 double TotalGrossAmount =0;
		 double Totalcomm =0;
		 //------------------------Fare------------------------------------
		 List<Fare> listFare=new ArrayList<Fare>();
		 ArrayList<Map<String, Object>> FareBreakdownarray=(ArrayList<Map<String, Object>>) Taxoutput.get("FareBreakdown");
		 for(int h=0;h<FareBreakdownarray.size();h++){
			 Map<String, Object> objfare=FareBreakdownarray.get(h);
			 Fare Fare=new Fare();
			 Fare.setTripIndicator(objfare.get("TripIndicator").toString());
			 if(objfare.get("PassengerType").equals("1")){
				 Fare.setPassengerType("Adult");
			 } if(objfare.get("PassengerType").equals("2")){
				 Fare.setPassengerType("Child");
			 } if(objfare.get("PassengerType").equals("3")){
				 Fare.setPassengerType("Infant");
			 }
			 Totalcomm=Totalcomm+Double.parseDouble(objfare.get("portalcomm").toString());
			 
			 Fare.setBaseFare(Double.parseDouble(objfare.get("BaseFare").toString()));
			 Fare.setGrossAmount(Double.parseDouble(objfare.get("GrossAmount").toString()));
			 TotalGrossAmount=TotalGrossAmount+Double.parseDouble(objfare.get("GrossAmount").toString());
			 Fare.setTax(Double.parseDouble(objfare.get("Tax").toString()));
			 Fare.setTransactionFee(Double.parseDouble(objfare.get("TransactionFee").toString()));
			 Fare.setYQTax(Double.parseDouble(objfare.get("YQ").toString()));
			 Fare.setAdditionalTxnFeeOfrd(Double.parseDouble(objfare.get("AdditionalTxnFeeOfrd").toString()));
			 Fare.setAdditionalTxnFeePub(Double.parseDouble(objfare.get("AdditionalTxnFeePub").toString()));
			 Fare.setAirTransFee(Double.parseDouble(objfare.get("AdditionalTxnFeePub").toString()));
			 Fare.setOtherCharges(Double.parseDouble(objfare.get("Commission").toString()));
			 listFare.add(Fare);
		 }
		 log.warn("listFare::"+listFare);
		//----------------------------------input---------------------------
		BookingRequest input=new BookingRequest();
		input.setAuthentication(WebServerClient.getAuthentication());
		input.setTrackId(Taxoutput.get("TrackId").toString());
		if(prevrecord.containsKey("returnDetail")){
			input.setTripType("R");
		}else{
			input.setTripType("O");
		}
		
		if(source[2].equals("India")){
			input.setDomestic(true);
		}else{
			input.setDomestic(false);
		}
		input.setPassengerDetails(listpassen);
		input.setBookingSegmentDetail(listbookseg);
		input.setFare(listFare);
		
		log.warn("Double.parseDouble(er.get('portalcommission').toString())::"+Totalcomm);
		 parameters.put("userId", userDetails.getUserId());	
		 double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
		 log.warn("Taxoutput.get('adminmarkup').toString()::"+Taxoutput.get("adminmarkup").toString());
		 log.warn("Taxoutput.get('usermarkup').toString()::"+Taxoutput.get("usermarkup").toString());
		 log.warn("TotalGrossAmount::"+TotalGrossAmount);
		// double totalamnt=TotalGrossAmount+mealamount+baggageamount+Double.parseDouble(Taxoutput.get("adminmarkup").toString())-Double.parseDouble(Taxoutput.get("usermarkup").toString())-Totalcomm
		 double totalamnt=TotalGrossAmount+mealamount+baggageamount+Double.parseDouble(Taxoutput.get("adminmarkup").toString())-Totalcomm;
		double clbl=op_bal-(totalamnt);
		System.out.println("clbl::"+clbl);
		log.warn("totalamnt::"+totalamnt);
		if(clbl>=userDetails.getLockedAmount()){
			flag = commissionService.updateBalance(userDetails.getUserId(), "FLIGHT BOOKING FOR TRACK ID  "+Taxoutput.get("TrackId").toString(), "FLIGHT BOOKING", totalamnt, "DEBIT",0);
			List<Airline> air=AirlineDao.getAllAirline();
			output=WebServerClient.getFlightBooking(input,air);
			
			if(output.getResponseStatus()==1){
			
				returnData.put("status", "1")	;
				returnData.put("bookingresult", output);
				log.warn("output::"+output.getTicketDetails());
				if(output.getTicketDetails()!=null){
					List<TicketDetails>  ticketlist = output.getTicketDetails();
					if(!ticketlist.isEmpty()){
						for(int i=0;i<ticketlist.size();i++){
							TicketDetails td=ticketlist.get(i);
							List<Passenger> listpassenger=td.getPassenger();
							
							List<BookedSegments> listbook=listpassenger.get(0).getBookedSegments();
							
							int totalduration=0;
				    		 for(int gh=0;gh<listbook.size();gh++){
				    			 totalduration=totalduration+listbook.get(gh).getDuration();
				    			 
				    		 }
				    		 long diff1 =  new Long(totalduration);
				    		 long diffMinutes1 = 0;
								long diffHours1 = 0;
								String finalhours1 = "";
								diffMinutes1 = diff1 % 60;
								 diffHours1 = diff1 / 60;
								 finalhours1=String.valueOf(diffHours1)+"hrs"+" "+String.valueOf(diffMinutes1)+"mins";
								 Map<String, Object> param = new HashMap<String, Object>();
									/*param.put("airline_code", td.getAirlineCode());
									List<Airline> air=AirlineDao.getAirlineByNamedQuery("getAirlinebyairline_code", param);
								*/	
							Flightbooking fb = new Flightbooking(userDetails.getUserId(), td.getBookingId(), td.getSkypointPNR(), output.getTrackId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime() , td.getOrigin(), td.getDestination(),listbook.get(0).getDepartureDateTime(), "Ticketed",true, td.getAirlinePNR(), td.getCRSPNR(),td.getTravelType(),td.getAirlineCode(),finalhours1,op_bal,totalamnt,clbl);
						      boolean flag2 = flightBookingDao.insertFlightbooking(fb);
						      if(flag2){
						    		//===============================
									 param = new HashMap<String, Object>();
									param.put("wlId", userDetails.getWlId());
									SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
									String sms = "Dear "+userDetails.getName()+",your flight from "+td.getOrigin()+" To "+td.getDestination()+" booking is successful. You PNR NO "+td.getSkypointPNR()+" Your Journey Date "+listbook.get(0).getDepartureDateTime();
									//SMS.sendSMS(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
									SMS.sendSMS2(userDetails.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
									
						    	  FlightFare FlightFare=new FlightFare(output.getTrackId(), TotalGrossAmount+mealamount+baggageamount, Double.parseDouble(Taxoutput.get("usermarkup").toString()), Double.parseDouble(Taxoutput.get("adminmarkup").toString()),Totalcomm,0.0,0.0);
						    	  FlightFareDao.insertFlightFare(FlightFare);
						    	  Date dt1 = null;
						  		Date dt2 = null;
						  		long diffMinutes = 0;
								long diffHours = 0;
								String finalhours = "";

					    		 for(int gh=0;gh<listbook.size();gh++){
						    		  long diff =  new Long(listbook.get(gh).getDuration());
						    		  System.out.println(diff);
						    		  diffMinutes = diff % 60;
						    		  System.out.println("diffMinutes::"+diffMinutes);
						    		  diffHours = diff / 60;
						    		  finalhours=String.valueOf(diffHours)+"hrs"+" "+String.valueOf(diffMinutes)+"mins";
						    		  System.out.println(finalhours);
					    			 Flightdetail fd = new Flightdetail(td.getAirlinePNR(),td.getAirlineCode(),listbook.get(gh).getFlightNumber(), listbook.get(gh).getOrigin(), listbook.get(gh).getDestination(), listbook.get(gh).getDepartureDateTime(), listbook.get(gh).getArrivaldatetime(), listbook.get(gh).getClassCode(), listbook.get(gh).getClassType(), listbook.get(gh).getOriginAirport(), listbook.get(gh).getDestinationAirport(), finalhours);
							    	 flightdetailDao.insertFlightdetail(fd);
					    		 }
						    	  
						    	  CustomerDetail cd=td.getCustomerDetail();
						    	  boolean isleadpassenger=false;
						    	  String contact="";
						    	  String email="";
						    	  for(int l=0;l<listpassenger.size();l++){
						    		  Passenger pi = listpassenger.get(l);
						    		  if(l==0){
						    			  isleadpassenger = true;
						    			  contact = cd.getContactNumber();
						    			  email= cd.getEmailId();
						    			  
						    		  }else{
						    			  isleadpassenger = false;
						    			  contact = "";
						    			  email= "";
						    		  }
						    		  List<BookedSegments> listbooks=listpassenger.get(l).getBookedSegments();
						    		 PassengerDetail psgd = new PassengerDetail(pi.getTitle(), pi.getFirstName(), pi.getLastName(), pi.getTicketNumber(), isleadpassenger, contact,email ,"", "", td.getAirlinePNR(),pi.getPaxType(),pi.getTicketId());
						    		 flag2 = passengerdetaildao.insertPassengerDetail(psgd);
						    		
						    		 if(flag2){
						    			 Passengerfare pf = new Passengerfare(psgd.getTicketnumber(), Double.parseDouble(listbooks.get(0).getBaseAmount()), Double.parseDouble(listbooks.get(0).getTaxAmount()), Double.parseDouble(listbooks.get(0).getServiceCharge()),Double.parseDouble(listbooks.get(0).getTransactionFee()) , Double.parseDouble(listbooks.get(0).getGrossAmount()), td.getAirlinePNR());
						    			 passengerfaredao.insertPassengerfare(pf);
						    			 
						    		 }
						    		// System.out.println("listbooks.size()"+listbooks.toString());
						    		 for(int h=0;h<listbooks.size();h++){
						    			 
						    			// System.out.println("listbooks.get(h).getAdditionalSSRDetails():"+bs.getAdditionalSSRDetails().size());
						    			 List<AdditionalSSRDetails> listAdditionalSSRDetails=listbooks.get(h).getAdditionalSSRDetails();
						    			 if(!listAdditionalSSRDetails.isEmpty()){
						    				// System.out.println("listAdditionalSSRDetails.size()"+listAdditionalSSRDetails.size());
						    				 for(int m=0;m<listAdditionalSSRDetails.size();m++){
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Meals")){
						    						 MealDetails MealDetails=new MealDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(), listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()));
						    						 MealDetailsDao.insertMealDetails(MealDetails);
						    					 }
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Baggage")){
						    						 BaggageDetails BaggageDetails=new BaggageDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(),listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()), (listAdditionalSSRDetails.get(m).getWeight()));
						    						 BaggageDao.insertBaggageDetails(BaggageDetails);
						    					 }
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Seat")){
						    						 SeatDetails SeatDetails=new SeatDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(), listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()));
						    						 SeatDetailsDao.insertSeatDetails(SeatDetails);
						    					 }
						    					 
						    				 }
						    			 }
						    		 }
						    		 
						    	  }
						    	  
						      }
						}
					}
				}
				returnData.put("message", "BOOKING SUCCESSFULLY");
			}else{
				flag = commissionService.updateBalance(userDetails.getUserId(), "FLIGHT BOOKING FOR TRACK ID  "+Taxoutput.get("TrackId").toString(), "FLIGHT BOOKING", totalamnt, "CREDIT",0);
				returnData.put("status", "0");
				returnData.put("bookingresult", output);
				returnData.put("adminmarkup", Taxoutput.get("adminmarkup").toString());
				returnData.put("usermarkup", Taxoutput.get("usermarkup").toString());
				returnData.put("message", output.getErrorMessage());
			}
			
		}else{
			returnData.put("status", "0")	;
			returnData.put("message", "Insufficient Balance")	;
		}
		}catch(Exception e){
			System.out.println(e);
			returnData.put("status", "0")	;
			returnData.put("message", e)	;
		}
		return returnData;
		
	}
	
	
	
	
	@Override
	@SuppressWarnings({"unchecked"})
	public Map<String, Object> Flightbookrequestandroid(User userDetails,BookingRequest input,double total){
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		Map<String, String> parameters = new HashMap<String, String>();
		AirBookResponse output=new AirBookResponse();
		try{
			
		 double TotalGrossAmount =0;
		
		 
		 parameters.put("userId", userDetails.getUserId());	
		 double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
		 double totalamnt=total;
		double clbl=op_bal-(totalamnt);
		System.out.println("clbl::"+clbl);
		if(clbl>=userDetails.getLockedAmount()){
			List<Airline> air=AirlineDao.getAllAirline();
			output=WebServerClient.getFlightBooking(input,air);
			
			if(output.getResponseStatus()==1){
				flag = commissionService.updateBalance(userDetails.getUserId(), "FLIGHT BOOKING FOR TRACK ID  "+output.getTrackId(), "FLIGHT BOOKING", totalamnt, "DEBIT",0);
				returnData.put("status", "1")	;
				returnData.put("bookingresult", output);
				System.out.println("output::"+output.getTicketDetails());
				if(output.getTicketDetails()!=null){
					List<TicketDetails>  ticketlist = output.getTicketDetails();
					if(!ticketlist.isEmpty()){
						for(int i=0;i<ticketlist.size();i++){
							TicketDetails td=ticketlist.get(i);
							List<Passenger> listpassenger=td.getPassenger();
							
							List<BookedSegments> listbook=listpassenger.get(0).getBookedSegments();
							
							int totalduration=0;
				    		 for(int gh=0;gh<listbook.size();gh++){
				    			 totalduration=totalduration+listbook.get(gh).getDuration();
				    			 
				    		 }
				    		 long diff1 =  new Long(totalduration);
				    		 long diffMinutes1 = 0;
								long diffHours1 = 0;
								String finalhours1 = "";
								diffMinutes1 = diff1 % 60;
								 diffHours1 = diff1 / 60;
								 finalhours1=String.valueOf(diffHours1)+"hrs"+" "+String.valueOf(diffMinutes1)+"mins";
							
							Flightbooking fb = new Flightbooking(userDetails.getUserId(), td.getBookingId(), td.getSkypointPNR(), output.getTrackId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime() , td.getOrigin(), td.getDestination(),listbook.get(0).getDepartureDateTime(), "Ticketed",true, td.getAirlinePNR(), td.getCRSPNR(),td.getTravelType(),td.getAirlineCode(),finalhours1,op_bal,totalamnt,clbl);
						      boolean flag2 = flightBookingDao.insertFlightbooking(fb);
						      if(flag2){
						    	//===============================
									Map<String, Object> param = new HashMap<String, Object>();
									param.put("wlId", userDetails.getWlId());
									SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
									String sms = "Dear "+userDetails.getName()+",your flight from "+td.getOrigin()+" To "+td.getDestination()+" booking is successful. You PNR NO "+td.getSkypointPNR()+" Your Journey Date "+listbook.get(0).getDepartureDateTime();
									//SMS.sendSMS(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
									SMS.sendSMS2(userDetails.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
									
						    	  Date dt1 = null;
						  		Date dt2 = null;
						  		long diffMinutes = 0;
								long diffHours = 0;
								String finalhours = "";

					    		 for(int gh=0;gh<listbook.size();gh++){
						    		  long diff =  new Long(listbook.get(gh).getDuration());
						    		  System.out.println(diff);
						    		  diffMinutes = diff % 60;
						    		  System.out.println("diffMinutes::"+diffMinutes);
						    		  diffHours = diff / 60;
						    		  finalhours=String.valueOf(diffHours)+"hrs"+" "+String.valueOf(diffMinutes)+"mins";
						    		  System.out.println(finalhours);
					    			 Flightdetail fd = new Flightdetail(td.getAirlinePNR(),td.getAirlineCode(),listbook.get(gh).getFlightNumber(), listbook.get(gh).getOrigin(), listbook.get(gh).getDestination(), listbook.get(gh).getDepartureDateTime(), listbook.get(gh).getArrivaldatetime(), listbook.get(gh).getClassCode(), listbook.get(gh).getClassType(), listbook.get(gh).getOriginAirport(), listbook.get(gh).getDestinationAirport(), finalhours);
							    	 flightdetailDao.insertFlightdetail(fd);
					    		 }
						    	  
						    	  CustomerDetail cd=td.getCustomerDetail();
						    	  boolean isleadpassenger=false;
						    	  String contact="";
						    	  String email="";
						    	  for(int l=0;l<listpassenger.size();l++){
						    		  Passenger pi = listpassenger.get(l);
						    		  if(l==0){
						    			  isleadpassenger = true;
						    			  contact = cd.getContactNumber();
						    			  email= cd.getEmailId();
						    			  
						    		  }else{
						    			  isleadpassenger = false;
						    			  contact = "";
						    			  email= "";
						    		  }
						    		  List<BookedSegments> listbooks=listpassenger.get(l).getBookedSegments();
						    		 PassengerDetail psgd = new PassengerDetail(pi.getTitle(), pi.getFirstName(), pi.getLastName(), pi.getTicketNumber(), isleadpassenger, contact,email ,"", "", td.getAirlinePNR(),pi.getPaxType(),pi.getTicketId());
						    		 flag2 = passengerdetaildao.insertPassengerDetail(psgd);
						    		
						    		 if(flag2){
						    			 Passengerfare pf = new Passengerfare(psgd.getTicketnumber(), Double.parseDouble(listbooks.get(0).getBaseAmount()), Double.parseDouble(listbooks.get(0).getTaxAmount()), Double.parseDouble(listbooks.get(0).getServiceCharge()),Double.parseDouble(listbooks.get(0).getTransactionFee()) , Double.parseDouble(listbooks.get(0).getGrossAmount()), td.getAirlinePNR());
						    			 passengerfaredao.insertPassengerfare(pf);
						    			 
						    		 }
						    		// System.out.println("listbooks.size()"+listbooks.toString());
						    		 for(int h=0;h<listbooks.size();h++){
						    			 
						    			// System.out.println("listbooks.get(h).getAdditionalSSRDetails():"+bs.getAdditionalSSRDetails().size());
						    			 List<AdditionalSSRDetails> listAdditionalSSRDetails=listbooks.get(h).getAdditionalSSRDetails();
						    			 if(!listAdditionalSSRDetails.isEmpty()){
						    				// System.out.println("listAdditionalSSRDetails.size()"+listAdditionalSSRDetails.size());
						    				 for(int m=0;m<listAdditionalSSRDetails.size();m++){
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Meals")){
						    						 MealDetails MealDetails=new MealDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(), listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()));
						    						 MealDetailsDao.insertMealDetails(MealDetails);
						    					 }
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Baggage")){
						    						 BaggageDetails BaggageDetails=new BaggageDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(),listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()), (listAdditionalSSRDetails.get(m).getWeight()));
						    						 BaggageDao.insertBaggageDetails(BaggageDetails);
						    					 }
						    					 if(listAdditionalSSRDetails.get(m).getName().equals("Seat")){
						    						 SeatDetails SeatDetails=new SeatDetails(td.getAirlinePNR(), pi.getTicketNumber(), listAdditionalSSRDetails.get(m).getDescription(), listbook.get(0).getOrigin(), listbook.get(0).getDestination(), Double.parseDouble(listAdditionalSSRDetails.get(m).getAmount()));
						    						 SeatDetailsDao.insertSeatDetails(SeatDetails);
						    					 }
						    					 
						    				 }
						    			 }
						    		 }
						    		 
						    	  }
						    	  
						      }
						}
					}
				}
				returnData.put("message", "BOOKING SUCCESSFULLY");
			}else{
				returnData.put("status", "0")	;
				returnData.put("bookingresult", output);
				returnData.put("message", output.getErrorMessage());
			}
			
		}else{
			returnData.put("status", "0")	;
			returnData.put("message", "Insufficient Balance")	;
		}
		}catch(Exception e){
			System.out.println(e);
			returnData.put("status", "0")	;
			returnData.put("message", e)	;
		}
		return returnData;
		
	}
	
	@Override
	public Map<String, Object> viewBooikngReport(User userDetails,Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println(request);
		List<Flightbooking> list2 = new ArrayList<>();
		String traceId = "";
		String sql="";
		if(userDetails.getRoleId()==1){
			if(request.get("status").equals("ALL")){
				sql="getFltghtBookingBystatusall";
			}else{
				param.put("booking_status", request.get("status"));
				sql="getFltghtBookingBystatus";
			}
			
		}else{
			if(request.get("status").equals("ALL")){
				param.put("username", userDetails.getUserId());
				sql="getFltghtBookingByUsernameAnddate";
			}else{
			param.put("booking_status", request.get("status"));
			param.put("username", userDetails.getUserId());
			sql="getFltghtBookingByUsernameAndstatus";
			}
		}
		param.put("start_date", request.get("startDate"));
		param.put("end_date", request.get("endDate"));
		
		List<Flightbooking> list=flightBookingDao.getFlightBookingByNamedQuery(sql, param);
		if(!list.isEmpty()){
			List<Flightdetail> flightdetail = new ArrayList<>();
			List<Flightdetail> flightdetail2 = new ArrayList<>();
			for(int i=0;i<list.size();i++){
				Flightbooking fb = list.get(i);
				//System.out.println("fb.getAirlinecode():"+fb.getAirlinecd());
				param = new HashMap<String, Object>();
				param.put("airline_code", fb.getAirlinecd());
				List<Airline> air=AirlineDao.getAirlineByNamedQuery("getAirlinebyairline_code", param);
				fb.setAirlinename(air.get(0).getAirline_name());
				User uflight=userDao.getUserByUserId(fb.getUsername());
				fb.setUname(uflight.getName());
				fb.setUsermobile(uflight.getMobile());
				param = new HashMap<String, Object>();
				param.put("PNR", fb.getAirlinepnr());
				param.put("cancellation_status","cancelled");
				if(!fb.getTraceid().equals(traceId)){
				List<PassengerDetail> passengerlist = passengerdetaildao.getPassengerDetailByNamedQuery("getPassengerDetailByPNRAndStatus",param);
				fb.setPassengerlist(passengerlist);
				param = new HashMap<String, Object>();
				param.put("PNR", fb.getAirlinepnr());
			    flightdetail = flightdetailDao.getFlightdetailByNamedQuery("getFlightdetailbyPNR", param);
				fb.setFlighdetail(flightdetail);
				list2.add(fb);
				}
				if(fb.getTraceid().equals(traceId)){
					param = new HashMap<String, Object>();
					param.put("PNR", fb.getAirlinepnr());
				  flightdetail = flightdetailDao.getFlightdetailByNamedQuery("getFlightdetailbyPNR", param);
				  Flightbooking fb2 = list.get(i-1);
				  String airPNR = fb2.getAirlinepnr()+","+fb.getAirlinepnr();
				  String hermesPNR = fb2.getPNR()+","+fb.getPNR();
				  fb2.setPNR(hermesPNR);
				  fb2.setAirlinepnr(airPNR);
				  List<Flightdetail> flightdetail3 = fb2.getFlighdetail();
				  flightdetail3.addAll(flightdetail);
				  list2.remove(list2.size()-1);
				  list2.add(fb2);
				}
				
				traceId = fb.getTraceid();
				
				
			}
			
			
			returnData.put("status", "1")	;
			returnData.put("bookingreport",list2)	;
		}else{
			returnData.put("status", "0")	;
			returnData.put("message", "No Booking Record Found")	;
		}
		return returnData;
	}

	@Override
	public Map<String, Object> ViewTicket(User userDetails, Map<String, Object> request) {
		System.out.println(request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		String trav[]=request.get("travel_date").toString().split(" ");
		
		List<Airport_code> lists = airportdao.getCities(request.get("source").toString());
		request.put("sourcescity", lists.get(0).getCity());
		List<Airport_code> listd = airportdao.getCities(request.get("destination").toString());
		request.put("destinationcity", listd.get(0).getCity());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PNR", request.get("PNR"));
		List<Flightdetail> Flightdetaillist=flightdetailDao.getFlightdetailByNamedQuery("getFlightdetailbyPNR", param);
		request.put("arrivtime", Flightdetaillist.get(Flightdetaillist.size()-1).getArrtime());
		request.put("depart", Flightdetaillist.get(0).getDepttime());
		returnData.put("flightdetails", Flightdetaillist);
		
		List<PassengerDetail> listpassenger=passengerdetaildao.getPassengerDetailByNamedQuery("getPassengerDetailByPNR", param);
		returnData.put("request", request);
		returnData.put("passengerlist", listpassenger);
		List<Passengerfare> allfare=new ArrayList<Passengerfare>();
		System.out.println(listpassenger.size());
		List<Passengerfare> listPassengerfare=passengerfaredao.getPassengerfareByNamedQuery("getPassengerfareByPNR", param);
	/*	for(int i=0;i<listpassenger.size();i++){
			PassengerDetail pd=listpassenger.get(i);
			System.out.println(pd.getTicketnumber());
			param = new HashMap<String, Object>();
			param.put("ticketnumber", pd.getTicketnumber());
			List<Passengerfare> listPassengerfare=passengerfaredao.getPassengerfareByNamedQuery("getPassengerfareByticket", param);
			System.out.println(listPassengerfare.size());
			allfare.add(listPassengerfare.get(0));
		}*/
		returnData.put("listPassengerfare", listPassengerfare);
		param = new HashMap<String, Object>();
		param.put("traceId", request.get("traceid"));
		List<FlightFare> listFlightFare=FlightFareDao.getFlightFareByNamedQuery("getFlightFarebytraceId",param);
		returnData.put("listFlightFare", listFlightFare);
		returnData.put("traveldate", trav[0]);
		
		GstFlight GstFlight=GstFlightDao.getGstFlightByUserId(request.get("username").toString());
		if(GstFlight==null){
			GstFlight=GstFlightDao.getGstFlightByUserId("admin");
		}
		returnData.put("GstFlight", GstFlight);
		
		return returnData;
	}

	
	@Override
	public Map<String, Object> cancelTicket(Map<String, Object> request, User user) {
		List<CancelRequest> list = new ArrayList<CancelRequest>();
		Map<String, Object> returndata = new HashMap<String, Object>();
		CancelResponse output=new CancelResponse();
		try {
			System.out.println("request:::::::::::::::"+request);
			//{id=22.0, username=Y7F077, booking_id=43240252, PNR=C66RNY, traceid=TB0258490e-26b5-45c5-a820-61c7c413bfa7, date=2020-10-17, time=12:51:07 PM, source=CCU, destination=PAT, travel_date=2021-03-20 05:55:00, booking_status=Ticketed, isRefundable=true, airlinepnr=C66RNY, crspnr=, flighttype=Domestic, airlinecd=SG, duration=1hrs 0mins, flighdetail=[{id=17.0, PNR=C66RNY, flightid=SG, flight_number=3507, origin=Kolkata, destination=Patna, depttime=2021-03-20 05:55:00, arrtime=2021-03-20 06:55:00, classcode=U, classcodedesc=PUB, sourceterminal=CCU, destinationterminal=PAT, duration=1hrs 0mins}], passengerlist=[{id=26.0, title=Mr, firstname=Partha, lastname=Ghosh, ticketnumber=45552149, isleadpassenger=true, contact=9903712171, email=partha.pciexpress@gmail.com, birth_date=, cancellation_status=, PNR=C66RNY, category=Adult}], type=FULL, ticketlist=[45552149]}

			ArrayList ticktdt=(ArrayList) request.get("ticketlist");
			System.out.println("request:::::::::::::::"+ticktdt.size());
			for(int i=0;i<ticktdt.size();i++){
				System.out.println(ticktdt);
				if(ticktdt.get(i)==null){
					ticktdt.remove(i);
				}
				System.out.println(ticktdt);
			}
			System.out.println("request:::::::::::::::"+ticktdt.size());
			String ticklist="";
			if(ticktdt.size()==1){
				ticklist=ticktdt.get(0).toString();
			}else if(ticktdt.size()>1){
				ticklist=ticktdt.get(0).toString();
				for(int i=1;i<ticktdt.size();i++){
					ticklist=ticklist+","+ticktdt.get(i).toString();
				}
			}
			
			CancelRequest input=new CancelRequest();
			//WebServerClient.getAuthentication(), request.get("traceid").toString(), request.get("booking_id").toString(),  request.get("type").toString(), request.get("PNR").toString(), ticklist
			input.setAuthentication(WebServerClient.getAuthentication());
			input.setTrackId(request.get("traceid").toString());
			input.setBookingId(request.get("booking_id").toString());
			input.setRequestType(request.get("type").toString());
			input.setPNR(request.get("PNR").toString());
			//if(request.get("type").toString().equals("PARTIAL")){
				input.setTicketId(ticklist);
			//}
			output=WebServerClient.getcancelTicket(input);
			System.out.println(output.getCancellationDetail());
			List<CancelResponsedetails> listcan=output.getCancellationDetail();
			for(int i=0;i<listcan.size();i++){
				CancelResponsedetails cn=listcan.get(i);
				if(cn.getResponseStatus()==1){
					if(request.get("type").toString().equals("FULL")){
						flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "FULL");
						returndata.put("type","FULL");
					CancellationDetail CancellationDetail=new CancellationDetail(request.get("booking_id").toString(), request.get("PNR").toString(), request.get("traceid").toString(), cn.getTicketId(), cn.getChangeRequestId(), cn.getRefundedAmount(), cn.getCancellationCharge(), cn.getStatus());
					CancellationDetailDao.insertCancellationDetail(CancellationDetail);
					 commissionService.updateBalance(request.get("username").toString(), "FLIGHT BOOKING CANCEL FOR TICKET ID  "+cn.getTicketId(), "FLIGHT BOOKING", Double.parseDouble(cn.getRefundedAmount()), "CREDIT",0.0);

					}else{
						CancellationDetail CancellationDetail=new CancellationDetail(request.get("booking_id").toString(), request.get("PNR").toString(), request.get("traceid").toString(), cn.getTicketId(), cn.getChangeRequestId(), cn.getRefundedAmount(), cn.getCancellationCharge(), cn.getStatus());
						CancellationDetailDao.insertCancellationDetail(CancellationDetail);
						commissionService.updateBalance(request.get("username").toString(), "FLIGHT BOOKING CANCEL FOR TICKET ID  "+cn.getTicketId(), "FLIGHT BOOKING", Double.parseDouble(cn.getRefundedAmount()), "CREDIT",0.0);
						flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "PARTIAL");
						//for(int k=0;k<ticktdt.size();k++){
							passengerdetaildao.updatePassengerDetailbyairlinepnrAndTicketNumber(request.get("PNR").toString(), cn.getTicketId());
							
							//}
						//list.add(output);	
						returndata.put("cancellationlist",list);
						returndata.put("type","PARTIAL");
						returndata.put("traceid",request.get("traceid"));
					}
					returndata.put("message", "Cancel Done");
					returndata.put("status", 1);
				}else{
					returndata.put("message", "Cancel Failed");
					returndata.put("status", 0);
				}
			}
			/*if(output.getResponseStatus()==1){
				if(request.get("type").toString().equals("FULL")){
					flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "FULL");
					returndata.put("type","FULL");
				}else{
					flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "FULL");
					for(int k=0;k<ticktdt.size();k++){
						passengerdetaildao.updatePassengerDetailbyairlinepnrAndTicketNumber(request.get("PNR").toString(), ticktdt.get(k).toString());
						
						}
					//list.add(output);	
					returndata.put("cancellationlist",list);
					returndata.put("type","PARTIAL");
					returndata.put("traceid",request.get("traceid"));
				}
				returndata.put("message", output.getRemarks());
				returndata.put("status", 1);
			}else{
				
			}*/
        log.warn("cancelTicket::::::::::::::::::::"+returndata);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("cancelTicket:::::::::::::" + e);
		}
		return returndata;
	}
	
	
	@Override
	public Map<String, Object> cancelTicketapp(Map<String, String> request, User user) {
		List<CancelRequest> list = new ArrayList<CancelRequest>();
		Map<String, Object> returndata = new HashMap<String, Object>();
		CancelResponse output=new CancelResponse();
		try {
			System.out.println("request:::::::::::::::"+request);
			//{id=22.0, username=Y7F077, booking_id=43240252, PNR=C66RNY, traceid=TB0258490e-26b5-45c5-a820-61c7c413bfa7, date=2020-10-17, time=12:51:07 PM, source=CCU, destination=PAT, travel_date=2021-03-20 05:55:00, booking_status=Ticketed, isRefundable=true, airlinepnr=C66RNY, crspnr=, flighttype=Domestic, airlinecd=SG, duration=1hrs 0mins, flighdetail=[{id=17.0, PNR=C66RNY, flightid=SG, flight_number=3507, origin=Kolkata, destination=Patna, depttime=2021-03-20 05:55:00, arrtime=2021-03-20 06:55:00, classcode=U, classcodedesc=PUB, sourceterminal=CCU, destinationterminal=PAT, duration=1hrs 0mins}], passengerlist=[{id=26.0, title=Mr, firstname=Partha, lastname=Ghosh, ticketnumber=45552149, isleadpassenger=true, contact=9903712171, email=partha.pciexpress@gmail.com, birth_date=, cancellation_status=, PNR=C66RNY, category=Adult}], type=FULL, ticketlist=[45552149]}

			CancelRequest input=new CancelRequest();
			//WebServerClient.getAuthentication(), request.get("traceid").toString(), request.get("booking_id").toString(),  request.get("type").toString(), request.get("PNR").toString(), ticklist
			input.setAuthentication(WebServerClient.getAuthentication());
			input.setTrackId(request.get("traceid").toString());
			input.setBookingId(request.get("booking_id").toString());
			input.setRequestType(request.get("type").toString());
			input.setPNR(request.get("PNR").toString());
			//if(request.get("type").toString().equals("PARTIAL")){
				input.setTicketId(request.get("ticketlist").toString());
			//}
			output=WebServerClient.getcancelTicket(input);
			System.out.println(output.getCancellationDetail());
			List<CancelResponsedetails> listcan=output.getCancellationDetail();
			for(int i=0;i<listcan.size();i++){
				CancelResponsedetails cn=listcan.get(i);
				if(cn.getResponseStatus()==1){
					if(request.get("type").toString().equals("FULL")){
						flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "FULL");
						returndata.put("type","FULL");
					CancellationDetail CancellationDetail=new CancellationDetail(request.get("booking_id").toString(), request.get("PNR").toString(), request.get("traceid").toString(), cn.getTicketId(), cn.getChangeRequestId(), cn.getRefundedAmount(), cn.getCancellationCharge(), cn.getStatus());
					CancellationDetailDao.insertCancellationDetail(CancellationDetail);
					 commissionService.updateBalance(request.get("username").toString(), "FLIGHT BOOKING CANCEL FOR TICKET ID  "+cn.getTicketId(), "FLIGHT BOOKING", Double.parseDouble(cn.getRefundedAmount()), "CREDIT",0.0);

					}else{
						CancellationDetail CancellationDetail=new CancellationDetail(request.get("booking_id").toString(), request.get("PNR").toString(), request.get("traceid").toString(), cn.getTicketId(), cn.getChangeRequestId(), cn.getRefundedAmount(), cn.getCancellationCharge(), cn.getStatus());
						CancellationDetailDao.insertCancellationDetail(CancellationDetail);
						commissionService.updateBalance(request.get("username").toString(), "FLIGHT BOOKING CANCEL FOR TICKET ID  "+cn.getTicketId(), "FLIGHT BOOKING", Double.parseDouble(cn.getRefundedAmount()), "CREDIT",0.0);
						flightBookingDao.updateFlightBookingbyAirlinePNR(request.get("PNR").toString(), "PARTIAL");
						//for(int k=0;k<ticktdt.size();k++){
							passengerdetaildao.updatePassengerDetailbyairlinepnrAndTicketNumber(request.get("PNR").toString(), cn.getTicketId());
							
							//}
						//list.add(output);	
						returndata.put("cancellationlist",list);
						returndata.put("type","PARTIAL");
						returndata.put("traceid",request.get("traceid"));
					}
					returndata.put("message", "Cancel Done");
					returndata.put("status", 1);
				}else{
					returndata.put("message", "Cancel Failed");
					returndata.put("status", 0);
				}
			}
		
        log.warn("cancelTicket::::::::::::::::::::"+returndata);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("cancelTicket:::::::::::::" + e);
		}
		return returndata;
	}



	@Override
	public Map<String, Object> partialCancel(Map<String, Object> request, User user) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		try{/*
		boolean flag = false;
		String[] ticketnumber = null;
		GetPartialCancellationRequest  input = null;
		String[]  cancelPassenger = request.get("cancelPassenger").toString().split(",");
		String triptype=request.get("triptype").toString();
	     int passenger=Integer.parseInt(request.get("passenger").toString());
		if(triptype.equalsIgnoreCase("One Way")){
		JSONObject jobj = new JSONObject(request);
		JSONArray jarray =(JSONArray)jobj.getJSONArray("cancelPassenger");
		if(jarray.length()<passenger){
			input = new GetPartialCancellationRequest();
			input.setAuthentication(WebServiceClient.getAuthentication());
			String	hermesPNR = request.get("hermesPNR").toString();
			String	airlinePNR = request.get("airlinePNR").toString();
			String	CRSPNR = request.get("CRSPNR").toString();
			PartialCancellationInput partialCancellationInput = new PartialCancellationInput();
			partialCancellationInput.setAirlinePNR(airlinePNR);
			partialCancellationInput.setHermesPNR(hermesPNR);
			partialCancellationInput.setCRSPNR(CRSPNR);
			List<PartialCancelPaxItem> PartialCancelPassengerDetails = new ArrayList<>();
			for(int i=0;i<jarray.length();i++){
				JSONObject jpassengerobj =new JSONObject(jarray.get(i).toString());
				System.out.println(jpassengerobj);
				PartialCancelPaxItem partialCancelPaxItem = new PartialCancelPaxItem();
				partialCancelPaxItem.setPaxNumber(jpassengerobj.getInt("PaxNumber"));
				List<PartialCancelTicketItem> list = new ArrayList<>();
				JSONArray jticketarr =(JSONArray) jpassengerobj.getJSONArray("CancelTicketDetails");
				ticketnumber = new String[jticketarr.length()];
				for(int j=0;j<jticketarr.length();j++){
					JSONObject jticketobj =(JSONObject)jticketarr.getJSONObject(j);
					PartialCancelTicketItem partialCancelTicketItem = new PartialCancelTicketItem();
					partialCancelTicketItem.setDestination(jticketobj.getString("Destination"));
					partialCancelTicketItem.setFlightNumber(jticketobj.getString("FlightNumber"));
					partialCancelTicketItem.setOrigin(jticketobj.getString("Origin"));
					partialCancelTicketItem.setSegmentId(jticketobj.getInt("SegmentId"));
					partialCancelTicketItem.setTicketNumber(jticketobj.getString("TicketNumber"));
					ticketnumber[j]=jticketobj.getString("TicketNumber");
					list.add(partialCancelTicketItem);
				}
				partialCancelPaxItem.setPartialCancelTicketDetails(list);
				PartialCancelPassengerDetails.add(partialCancelPaxItem);
			}
			partialCancellationInput.setPartialCancelPassengerDetails(PartialCancelPassengerDetails);
			input.setPartialCancellationInput(partialCancellationInput);
			GetPartialCancellationResponse output = new WebServiceClient().getPartialCancellationResponse(input);
			if(output.getResponseStatus()==1){
				if(output.getPartialCancellationOutput().getPartialCancellationRemarks()!=null){
					flag=flightDao.updateFlightBookingbyAirlinePNR(airlinePNR,"PARTIAL");
					if(flag){
						for(int k=0;k<ticketnumber.length;k++){
						flag=passengerdetaildao.updatePassengerDetailbyairlinepnrAndTicketNumber(airlinePNR, ticketnumber[k]);
						if(flag){
							returndata.put("status","1");
							returndata.put("message",output.getPartialCancellationOutput().getPartialCancellationRemarks());
						}
						}
					}
				}
			}
			
		}else{
			returndata.put("status","0");
			returndata.put("message","You have select all passenger in partial cancellation");
		}
		
		
		}else if(triptype.equalsIgnoreCase("RoundTrip")){
			JSONObject jobj = new JSONObject(request);
			JSONArray jarray =(JSONArray)jobj.getJSONArray("cancelPassenger");
			String[] hermesPNR = request.get("hermesPNR").toString().split(",");
			String[] airlinePNR = request.get("airlinePNR").toString().split(",");
			String[] CRSPNR = request.get("CRSPNR").toString().split(",");
			System.out.println(CRSPNR.length);
			for(int l=0;l<hermesPNR.length;l++){
				System.out.println("l::::::::::::::::::::::::::::::::::"+l);
				System.out.println("hermesPNR::::::::::::::::::::::::::::::::::"+hermesPNR[l]);
				if(l==1){
					jarray =(JSONArray)jobj.getJSONArray("cancelroundPassenger");
				}
			if(jarray.length()<passenger){
				input = new GetPartialCancellationRequest();
				input.setAuthentication(WebServiceClient.getAuthentication());
				PartialCancellationInput partialCancellationInput = new PartialCancellationInput();
				partialCancellationInput.setAirlinePNR(airlinePNR[l]);
				partialCancellationInput.setHermesPNR(hermesPNR[l]);
				if(CRSPNR.length>0){
				partialCancellationInput.setCRSPNR(CRSPNR[l]);
				}else{
				partialCancellationInput.setCRSPNR(" ");	
				}
				List<PartialCancelPaxItem> PartialCancelPassengerDetails = new ArrayList<>();
				for(int i=0;i<jarray.length();i++){
					JSONObject jpassengerobj =new JSONObject(jarray.get(i).toString());
					System.out.println(jpassengerobj);
					PartialCancelPaxItem partialCancelPaxItem = new PartialCancelPaxItem();
					partialCancelPaxItem.setPaxNumber(jpassengerobj.getInt("PaxNumber"));
					List<PartialCancelTicketItem> list = new ArrayList<>();
					JSONArray jticketarr =(JSONArray) jpassengerobj.getJSONArray("CancelTicketDetails");
					ticketnumber = new String[jticketarr.length()];
					for(int j=0;j<jticketarr.length();j++){
						JSONObject jticketobj =(JSONObject)jticketarr.getJSONObject(j);
						PartialCancelTicketItem partialCancelTicketItem = new PartialCancelTicketItem();
						partialCancelTicketItem.setDestination(jticketobj.getString("Destination"));
						partialCancelTicketItem.setFlightNumber(jticketobj.getString("FlightNumber"));
						partialCancelTicketItem.setOrigin(jticketobj.getString("Origin"));
						partialCancelTicketItem.setSegmentId(jticketobj.getInt("SegmentId"));
						partialCancelTicketItem.setTicketNumber(jticketobj.getString("TicketNumber"));
						ticketnumber[j]=jticketobj.getString("TicketNumber");
						list.add(partialCancelTicketItem);
					}
					partialCancelPaxItem.setPartialCancelTicketDetails(list);
					PartialCancelPassengerDetails.add(partialCancelPaxItem);
				}
				partialCancellationInput.setPartialCancelPassengerDetails(PartialCancelPassengerDetails);
				input.setPartialCancellationInput(partialCancellationInput);
				GetPartialCancellationResponse output = new WebServiceClient().getPartialCancellationResponse(input);
				if(output.getResponseStatus()==1){
					if(output.getPartialCancellationOutput().getPartialCancellationRemarks()!=null){
						flag=flightDao.updateFlightBookingbyAirlinePNR(airlinePNR[l],"PARTIAL");
						if(flag){
							for(int k=0;k<ticketnumber.length;k++){
							flag=passengerdetaildao.updatePassengerDetailbyairlinepnrAndTicketNumber(airlinePNR[l], ticketnumber[k]);
							if(flag){
								returndata.put("status","1");
								returndata.put("message",output.getPartialCancellationOutput().getPartialCancellationRemarks());
							}
							}
						}
					}
				}
			
			}else{
				returndata.put("status","0");
				returndata.put("message","You have select all passenger in partial cancellation");
			}
		}
			
		}
		log.warn("partialCancel::::::::::::::::::::"+returndata);
		*/}catch (Exception e) {
			log.error("partialCancel::::::::::::::::::::"+e);
		}
		return returndata;
	}




	@Override
	public Map<String, Object> donetransactionfess(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{System.out.println(request);
		FlightFare FlightFare=FlightFareDao.getFlightFareByApId((int)Double.parseDouble(request.get("id").toString()));
		FlightFare.setFees(Double.parseDouble(request.get("fees").toString()));
		FlightFare.setDiscount(Double.parseDouble(request.get("discount").toString()));
		boolean flag=FlightFareDao.updateFlightFare(FlightFare);
		if(flag){
			returnData.put("status","1");
		}else{
			returnData.put("status","0");
		}
		}catch(Exception e){
			log.error("donetransactionfess::::::::::::::::::::"+e);
			}
		return returnData;
	}



}
