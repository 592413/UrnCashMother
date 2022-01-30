package com.skyflight.businessimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.skyflight.webserver.WebServerClient;
import org.apache.log4j.Logger;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.recharge.businessdao.CommissionService;
import com.recharge.model.AssignedPackage;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.PackageWiseChargeCommDao;
import com.skyflight.MealBaggagesCustomModel.FetchMealBaggageDetails;
import com.skyflight.MealBaggagesCustomModel.SSRFlightSegments;
import com.skyflight.MealBaggagesCustomModel.SSRRequest;
import com.skyflight.MealBaggagesCustomModel.SSRResponse;
import com.skyflight.SeatCustomModel.SeatMapInput;
import com.skyflight.SeatCustomModel.SeatPassengerDetails;
import com.skyflight.SeatCustomModel.SeatRequest;
import com.skyflight.SeatCustomModel.SeatResponse;
import com.skyflight.businessdao.FlightSearchDao;
import com.skyflight.model.Airline;
import com.skyflight.model.Airport_code;
import com.skyflight.model.Markup;
import com.skyflight.searchCustomModel.AirSearchAvailability;
import com.skyflight.searchCustomModel.CalendarFare;
import com.skyflight.searchCustomModel.CalendarFareRes;
import com.skyflight.searchCustomModel.CalenderfareInputRequest;
import com.skyflight.searchCustomModel.Fare;
import com.skyflight.searchCustomModel.FlightSegments;
import com.skyflight.searchCustomModel.SearchInput;
import com.skyflight.searchCustomModel.SearchInputRequest;
import com.skyflight.searchCustomModel.TripDetails;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.servicedao.AirportDao;
import com.skyflight.servicedao.MarkupDao;
import com.skyflight.taxCustomModel.AirTaxResponse;
import com.skyflight.taxCustomModel.FlightSegmentsTax;
import com.skyflight.taxCustomModel.TaxInput;
import com.skyflight.taxCustomModel.UpdatePriceInputRequest;
@Service("FlightSearchDao")
public class FlightSearchImpl implements FlightSearchDao{
	private static final Logger log = Logger.getLogger(FlightSearchDao.class);
	@Autowired AirportDao airportdao;
	@Autowired MarkupDao markupdao;
	@Autowired AirlineDao AirlineDao;
	@Autowired CommissionService commissionService;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired PackageWiseChargeCommDao PackageWiseChargeCommDao;
	
	@Override
	public List<String> getAirportcodes(String term) {
		List<String> airportlist = new ArrayList<String>();
		
		
		try {
			List<Airport_code> list = airportdao.getCities(term);
			for (Airport_code ac : list) {
				airportlist.add(ac.getCode() + "" + "," + ac.getCity() + "," + "" + "" + ac.getCountry() + "");
			}
	
		
			/*JSONObject obj=new JSONObject(airlist);
			JSONArray SerachResultsJSONArray = obj.getJSONArray("airports");*/
		} catch (Exception e) {
			log.error("getAirportcodes::::::::::::"+e);
		   return airportlist;
		}
		return airportlist;
	}
	
	@Override
	public AirSearchAvailability searchFlight(Map<String, String> request, User userDetails) {
		AirSearchAvailability output=new AirSearchAvailability();
		PackageWiseChargeComm pck = new PackageWiseChargeComm();
		List<PackageWiseChargeComm> lists=new ArrayList<>();
		System.out.println(request);//{class=ECONOMY, source=CCU,India, destination=DEL,India, depart=2019-09-06, adult=1}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			 String depart = request.get("depart").toString();
			 Date date = sdf.parse(depart);
		//{class=ECONOMY, source=CCU,India, destination=DEL,India, depart=2019-09-06, adult=1, return=19-08-07}
		String type=request.get("type").toString();
		String[] source = request.get("source").toString().split(",");
		 String[] destination = request.get("destination").toString().split(",");
		
		 List<TripDetails> TripDetails=new ArrayList<TripDetails>();
		 if(type.equals("R")){
			 type="R"; 
			 String returns = request.get("return").toString();
			 Date date2 = sdf.parse(returns);
			 TripDetails.add(new TripDetails(source[0], destination[0], sdf2.format(date)));
			 TripDetails.add(new TripDetails(destination[0], source[0], sdf2.format(date2)));
			 
		 }else{
			 type="O";
			 TripDetails.add(new TripDetails(source[0], destination[0], sdf2.format(date))); 
		 }
		 String adult="0";
		 if(request.containsKey("adult")){
			 adult=request.get("adult").toString();
		 }
		 String child="0";
		 if(request.containsKey("child")){
			 child=request.get("child").toString();
		 }
		 String infant="0";
		 if(request.containsKey("infant")){
			 infant=request.get("infant").toString();
		 }
		 int totalpassenger=Integer.parseInt(adult)+Integer.parseInt(child)+Integer.parseInt(infant);
		 SearchInput SearchInput=new SearchInput(request.get("class").toString(), Integer.parseInt(adult), Integer.parseInt(child), Integer.parseInt(infant), "", type, TripDetails);
		
		 SearchInputRequest input=new SearchInputRequest(WebServerClient.getAuthentication(), SearchInput);
		 
		 Map<String, Object> param = new HashMap<String, Object>();
			param.put("username","admin");
			List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
		 
			List<Airline> airlinelist=AirlineDao.getAllAirline();
			
			AssignedPackage asp = new AssignedPackage();
			param = new HashMap<String, Object>();
			param.put("user_id", userDetails.getUserId());
			param.put("service_type", "Flight");
			List<AssignedPackage> list = assignedPackage
					.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
			if (list.size() > 0) {
				
				param = new HashMap<String, Object>();
				param.put("package_id", list.get(0).getPackage_id());
				 lists=PackageWiseChargeCommDao.getPackageDetailByNamedQuery("getPackageWiseChargeCommbypackage", param);
				//pck=lists.get(0);
			}
				 output=WebServerClient.getFlightAvalibility(input,adminmarkupllist,usermarkuplist,totalpassenger,airlinelist,request.get("sources"),lists);
			/*else{
				output.setResponseStatus(0);
				output.setErrormessage("Please Assign Package");
			}*/
			
		 
		}catch(Exception e){
			log.warn("searchFlight:::"+e);
		}
		return output;
	}
	
	
	
	@Override
	public AirSearchAvailability fetchcalenderflight(Map<String, Object> request, User userDetails) {
		AirSearchAvailability output=new AirSearchAvailability();
		List<PackageWiseChargeComm> lists=new ArrayList<>();
		PackageWiseChargeComm pck = new PackageWiseChargeComm();
		System.out.println(request);//{date=2020-03-23T11:20:00, type=O, adult=1, child=0, infant=0, class=ECONOMY, destination=DEL,Delhi,India, source=CCU,Kolkata,India}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			
		//{class=ECONOMY, source=CCU,India, destination=DEL,India, depart=2019-09-06, adult=1, return=19-08-07}
		String type=request.get("type").toString();
		String[] source = request.get("source").toString().split(",");
		 String[] destination = request.get("destination").toString().split(",");
		String []  travdt=request.get("depart").toString().split("T");
		
		 String depart = travdt[0];
		 Date date = sdf.parse(depart);
		 List<TripDetails> TripDetails=new ArrayList<TripDetails>();
		/* if(type.equals("R")){
			 type="R"; 
			 String returns = request.get("return").toString();
			 Date date2 = sdf.parse(returns);
			 TripDetails.add(new TripDetails(source[0], destination[0], sdf2.format(date)));
			 TripDetails.add(new TripDetails(destination[0], source[0], sdf2.format(date2)));
			 
		 }else{*/
			 type="O";
			 TripDetails.add(new TripDetails(source[0], destination[0], sdf2.format(date))); 
		// }
		 String adult="0";
		 if(request.containsKey("adult")){
			 adult=request.get("adult").toString();
		 }
		 String child="0";
		 if(request.containsKey("child")){
			 child=request.get("child").toString();
		 }
		 String infant="0";
		 if(request.containsKey("infant")){
			 infant=request.get("infant").toString();
		 }
		 int totalpassenger=Integer.parseInt(adult)+Integer.parseInt(child)+Integer.parseInt(infant);
		 SearchInput SearchInput=new SearchInput(request.get("class").toString(), Integer.parseInt(adult), Integer.parseInt(child), Integer.parseInt(infant), "", type, TripDetails);
		
		 SearchInputRequest input=new SearchInputRequest(WebServerClient.getAuthentication(), SearchInput);
		 
		 Map<String, Object> param = new HashMap<String, Object>();
			param.put("username","admin");
			List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
		 
			List<Airline> airlinelist=AirlineDao.getAllAirline();
			
		  output=WebServerClient.getFlightAvalibility(input,adminmarkupllist,usermarkuplist,totalpassenger,airlinelist,request.get("sources").toString(),lists);
		}catch(Exception e){
			log.warn("searchFlight:::"+e);
		}
		return output;
	}
	
	
	@Override
	public CalendarFareRes fetchcalenderfare(Map<String, String> request, User userDetails) {
		CalendarFareRes output= new CalendarFareRes();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			 String depart= request.get("depart");
			 Date date = sdf.parse(depart);
			System.out.println(request);
			String[] source = request.get("source").split(",");
			 String[] destination = request.get("destination").split(",");
			List<CalendarFare> listCalendarFare=new ArrayList<CalendarFare>();
			CalendarFare cf=new CalendarFare(source[0], destination[0], sdf2.format(date), request.get("class"), request.get("type"));
			
			CalenderfareInputRequest input=new CalenderfareInputRequest();
			input.setAuthentication(WebServerClient.getAuthentication());
			input.setCalendarFare(cf);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username","admin");
			List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
			
			output=WebServerClient.fetchCalenderFare(input,adminmarkupllist,usermarkuplist);
			
			
		}catch(Exception e){
			log.warn("fetchcalenderfare:::"+e);
		}
		return output;
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public Map<String, Object> getTax(Map<String, Object> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		//{requestDetails={class=ECONOMY, source=CCU,Kolkata,India, destination=DEL,Delhi,India, depart=2020-02-20, adult=2, child=1},
		//searchDetails={IsLCC=true, AirlineId=SG, AirlineName=SpiceJet(SG), stops=0.0, ResultIndex=OB10, totalduration=2h 40m, tripindi=0.0,
		//Fare={Currency=INR, BaseFare=6555.0, Tax=3321.0, YQTax=0.0, OtherCharges=5.0, ServiceCharge=0.0, Commission=173.0, GrossAmount=9876.0, TotalAmount=10271.04, MarkUp=131.68}, 
		//FareBreakdown=[{TripIndicator=1.0, PassengerType=1, PassengerCount=2, BaseFare=4370.0, GrossAmount=6584.0, Commission=0.0, YQ=0.0, Tax=2214.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {TripIndicator=1.0, PassengerType=2, PassengerCount=1, BaseFare=2185.0, GrossAmount=3292.0, Commission=0.0, YQ=0.0, Tax=1107.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}],
		//FlightSegments=[{TripIndicator=1.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=SG, FlightNumber=264, Origin=CCU, Destination=DEL, DepartureDateTime=2020-02-20 20:50:00, DepartureDate=Thu-Feb 20,2020, DepartureTime=20:50:00, ArrivalDateTime=2020-02-20 23:30:00, ArrivalDate=Thu-Feb 20,2020, ArrivalTime=23:30:00, Duration=2h40m, FareClass=U, SupplierId=}]}, UserTrackId=TBd89622c9-18fb-4136-84b1-74c90bdcc06a}
		
		//roundtrip
		//{requestDetails={class=ECONOMY, source=CCU,Kolkata,India, destination=BLR,Bangalore,India, depart=2020-02-21, return=2020-02-22, adult=1}, 
		//searchDetails={IsLCC=false, AirlineId=UK, AirlineName=Air Vistara(UK), stops=0.0, ResultIndex=OB1, totalduration=2h 20m, tripindi=0.0, 
		//Fare={Currency=INR, BaseFare=5332.0, Tax=2437.0, YQTax=0.0, OtherCharges=4.0, ServiceCharge=0.0, Commission=0.0, GrossAmount=7770.0, TotalAmount=7770.0, MarkUp=0.0}, 
		//FareBreakdown=[{TripIndicator=1.0, PassengerType=1, PassengerCount=2, BaseFare=5332.0, GrossAmount=7770.0, Commission=0.0, YQ=0.0, Tax=2437.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}],
		//FlightSegments=[{TripIndicator=1.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=UK, FlightNumber=720, Origin=CCU, Destination=DEL, DepartureDateTime=2020-02-19 07:05:00, DepartureDate=Wed-Feb 19,2020, DepartureTime=07:05:00, ArrivalDateTime=2020-02-19 09:25:00, ArrivalDate=Wed-Feb 19,2020, ArrivalTime=09:25:00, Duration=2h20m, FareClass=V, SupplierId=}]}, 
		//returnDetail={IsLCC=true, AirlineId=SG, AirlineName=SpiceJet(SG), stops=0.0, ResultIndex=IB4, totalduration=1h 50m, tripindi=2.0, Fare={Currency=INR, BaseFare=4370.0, Tax=1838.0, YQTax=700.0, OtherCharges=0.0, ServiceCharge=0.0, Commission=0.0, GrossAmount=6208.0, TotalAmount=6456.32, MarkUp=248.32}, FareBreakdown=[{TripIndicator=2.0, PassengerType=1, PassengerCount=2, BaseFare=4370.0, GrossAmount=6208.0, Commission=0.0, YQ=700.0, Tax=1838.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FlightSegments=[{TripIndicator=2.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=SG, FlightNumber=263, Origin=DEL, Destination=CCU, DepartureDateTime=2020-02-21 05:55:00, DepartureDate=Fri-Feb 21,2020, DepartureTime=05:55:00, ArrivalDateTime=2020-02-21 07:45:00, ArrivalDate=Fri-Feb 21,2020, ArrivalTime=07:45:00, Duration=1h50m, FareClass=U, SupplierId=}]}, UserTrackId=TBf53075b8-d0c7-449f-8586-5578813364a1}

		
		
		Map<String, Object> requestdt = (Map<String, Object>) request.get("requestDetails");
		String[] source = requestdt.get("source").toString().split(",");
		 String[] destination = requestdt.get("destination").toString().split(",");
		 System.out.println("destination::"+destination[0]);
		 int adultcount=0;
		 int childcount=0;
		 int infantcount=0;
		 if(requestdt.containsKey("adult")){
			 adultcount=(int)((double)requestdt.get("adult"));
			 System.out.println("adultcount:::"+adultcount);
		 } if(requestdt.containsKey("child")){
			 childcount=(int)((double)requestdt.get("child"));
					
		 } if(requestdt.containsKey("infant")){
			 infantcount=(int)((double)requestdt.get("infant"));
		 }
		 int totalpassenger=adultcount+childcount+infantcount;
			
		Map<String, Object> searchDetails = (Map<String, Object>) request.get("searchDetails");
		System.out.println("searchDetails::"+searchDetails);
		//JSONObject searchDetails=(JSONObject) request.get("searchDetails");
		returnData.put("searchDetails",searchDetails);
		String type="";
		Map<String, Object> er = (Map<String, Object>) searchDetails.get("Fare");
		//Fare er=(Fare) searchDetails.get("Fare");
		double basefare=Double.parseDouble(er.get("BaseFare").toString());
		boolean isDomestic=true;
		if(source[2].equalsIgnoreCase("India") &&destination[2].equalsIgnoreCase("India")){
			isDomestic=true;
		}else{
			isDomestic=false;
		}
		
		Map<String,Object> customreq=new HashMap<String, Object>();
		List<FlightSegmentsTax> FlightSegments=new ArrayList<FlightSegmentsTax>();
		List<FlightSegmentsTax> FlightSegmentre=new ArrayList<FlightSegmentsTax>();
		List<TaxInput> TaxInput=new ArrayList<TaxInput>();
		if(requestdt.containsKey("return")){
			 type="R"; 
			 List<FlightSegments> requestsegment=(List<FlightSegments>) searchDetails.get("FlightSegments");
			 customreq.put("type", "R");
			 customreq.put("comm", er.get("portalcommission").toString());
			 System.out.println("requestsegment::R::"+requestsegment);
			 System.out.println("isDomestic::"+isDomestic);
			 if(isDomestic){
				 customreq.put("isDomestic", true);
			 for(int i=0;i<requestsegment.size();i++){
				 Map<String, Object> mapfs=new HashMap<String, Object>();
				 mapfs.put("fs", requestsegment.get(i));
				 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
				 //FlightSegments fs=requestsegment.get(i);  
				 customreq.put("trip"+mapf.get("TripIndicator").toString(), mapf.get("AirlineCode").toString());
				 FlightSegments.add(new FlightSegmentsTax(searchDetails.get("ResultIndex").toString(), mapf.get("TripIndicator").toString(), mapf.get("SegmentIndicator").toString(), "", mapf.get("FareClass").toString(), mapf.get("AirlineCode").toString(), ""));
			 }
			 TaxInput.add(new TaxInput(FlightSegments, Double.toString(basefare)));
			 
			 Map<String, Object> returnDetail = (Map<String, Object>) request.get("returnDetail");
			 returnData.put("returnDetail",returnDetail);
				Map<String, Object> erturn = (Map<String, Object>) returnDetail.get("Fare");
				customreq.put("comm1", erturn.get("portalcommission").toString());
				double basefarereturn=Double.parseDouble(erturn.get("BaseFare").toString());
				 List<FlightSegments> requestsegmentreturn=(List<FlightSegments>) returnDetail.get("FlightSegments");
				 for(int i=0;i<requestsegmentreturn.size();i++){
					 Map<String, Object> mapfs=new HashMap<String, Object>();
					 mapfs.put("fs", requestsegmentreturn.get(i));
					 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
					 
					 customreq.put("trip"+mapf.get("TripIndicator").toString(), mapf.get("AirlineCode").toString());
					 FlightSegmentre.add(new FlightSegmentsTax(returnDetail.get("ResultIndex").toString(), mapf.get("TripIndicator").toString(), mapf.get("SegmentIndicator").toString(), "", mapf.get("FareClass").toString(), mapf.get("AirlineCode").toString(), ""));
				 }
				 TaxInput.add(new TaxInput(FlightSegmentre, Double.toString(basefarereturn)));
			 }else{
				 customreq.put("isDomestic", false);
				 for(int i=0;i<requestsegment.size();i++){
					 Map<String, Object> mapfs=new HashMap<String, Object>();
					 mapfs.put("fs", requestsegment.get(i));
					 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
					 //FlightSegments fs=requestsegment.get(i);  
					 if(mapf.get("TripIndicator").toString().equals("1")){
						 customreq.put("trip"+mapf.get("TripIndicator").toString(), mapf.get("AirlineCode").toString());
						 FlightSegments.add(new FlightSegmentsTax(searchDetails.get("ResultIndex").toString(), mapf.get("TripIndicator").toString(), mapf.get("SegmentIndicator").toString(), "", mapf.get("FareClass").toString(), mapf.get("AirlineCode").toString(), ""));
						 
					 }else{
						 customreq.put("trip"+mapf.get("TripIndicator").toString(), mapf.get("AirlineCode").toString());
						 FlightSegmentre.add(new FlightSegmentsTax(searchDetails.get("ResultIndex").toString(), mapf.get("TripIndicator").toString(), mapf.get("SegmentIndicator").toString(), "", mapf.get("FareClass").toString(), mapf.get("AirlineCode").toString(), ""));
						 
					 }
					 
				 }
				 TaxInput.add(new TaxInput(FlightSegments, Double.toString(basefare))); 
				 TaxInput.add(new TaxInput(FlightSegmentre, Double.toString(basefare)));
			 }
		 }else{
			 if(isDomestic){
				 customreq.put("isDomestic", true);
			 }else{
				 customreq.put("isDomestic", false);
			 }
			
				 type="O";
				 customreq.put("type", "O");
				 customreq.put("comm", er.get("portalcommission").toString());
				 List<FlightSegments> requestsegment=(List<FlightSegments>) searchDetails.get("FlightSegments");
				 System.out.println(requestsegment);
				 
				 for(int i=0;i<requestsegment.size();i++){
					 Map<String, Object> mapfs=new HashMap<String, Object>();
					 mapfs.put("fs", requestsegment.get(i));
					 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
					 //FlightSegments fs=requestsegment.get(i);
					 customreq.put("trip"+mapf.get("TripIndicator").toString(), mapf.get("AirlineCode").toString());
					 FlightSegments.add(new FlightSegmentsTax(searchDetails.get("ResultIndex").toString(), mapf.get("TripIndicator").toString(), mapf.get("SegmentIndicator").toString(), "", mapf.get("FareClass").toString(), mapf.get("AirlineCode").toString(), ""));
				 }
				
				 TaxInput.add(new TaxInput(FlightSegments, Double.toString(basefare))); 
			 
			
		 }
		UpdatePriceInputRequest input=new UpdatePriceInputRequest(WebServerClient.getAuthentication(),request.get("UserTrackId").toString(),type,isDomestic,TaxInput);
		
		 Map<String, Object> param = new HashMap<String, Object>();
			param.put("username","admin");
			List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
			System.out.println("adminmarkupllist::"+adminmarkupllist.size());
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
		 
		AirTaxResponse output=WebServerClient.getFlightTax(input,adminmarkupllist,usermarkuplist,customreq,totalpassenger);

		returnData.put("status", output.getResponseStatus());	
		returnData.put("requestDetails",requestdt);
		returnData.put("Taxoutput", output);
		 returnData.put("type",type);
		 returnData.put("adultcount",adultcount);
		 returnData.put("childcount",childcount);
		 returnData.put("infantcount",infantcount);
		 returnData.put("isDomestic",isDomestic);
		return returnData;
	}
	
	
	
	@Override
	@SuppressWarnings({"unchecked"})
	public Map<String, Object> getSeatRequest(Map<String, Object> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{System.out.println(request);
			//{requestDetails={class=ECONOMY, source=CCU,Kolkata,India, destination=DEL,Delhi,India, depart=2020-02-21, return=2020-02-23, adult=1, child=1}, returnDetail={IsLCC=false, AirlineId=UK, AirlineName=Air Vistara(UK), stops=1.0, ResultIndex=IB25, totalduration=8h 55m, tripindi=0.0, Fare={Currency=INR, BaseFare=41300.0, Tax=2938.0, YQTax=0.0, OtherCharges=4.0, ServiceCharge=0.0, Commission=0.0, GrossAmount=44238.0, TotalAmount=46007.52, MarkUp=884.76}, FareBreakdown=[{TripIndicator=2.0, PassengerType=1, PassengerCount=1, BaseFare=20650.0, GrossAmount=22119.0, Commission=0.0, YQ=0.0, Tax=1469.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {TripIndicator=2.0, PassengerType=2, PassengerCount=1, BaseFare=20650.0, GrossAmount=22119.0, Commission=0.0, YQ=0.0, Tax=1469.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FlightSegments=[{TripIndicator=2.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=UK, FlightNumber=833, Origin=DEL, Destination=IXZ, DepartureDateTime=2020-02-23 07:20:00, DepartureDate=Sun-Feb 23,2020, DepartureTime=07:20:00, ArrivalDateTime=2020-02-23 13:30:00, ArrivalDate=Sun-Feb 23,2020, ArrivalTime=13:30:00, Duration=6h10m, FareClass=K, SupplierId=}, {TripIndicator=2.0, SegmentIndicator=2.0, FlightId=, AirCraftType=, AirlineCode=UK, FlightNumber=778, Origin=IXZ, Destination=CCU, DepartureDateTime=2020-02-23 13:55:00, DepartureDate=Sun-Feb 23,2020, DepartureTime=13:55:00, ArrivalDateTime=2020-02-23 16:15:00, ArrivalDate=Sun-Feb 23,2020, ArrivalTime=16:15:00, Duration=2h20m, FareClass=K, SupplierId=}]}, searchDetails={IsLCC=true, AirlineId=6E, AirlineName=IndiGo(6E), stops=1.0, ResultIndex=OB11, totalduration=6h 10m, tripindi=0.0, Fare={Currency=INR, BaseFare=7000.0, Tax=3182.0, YQTax=800.0, OtherCharges=472.0, ServiceCharge=0.0, Commission=169.0, GrossAmount=10182.0, TotalAmount=10589.28, MarkUp=203.64}, FareBreakdown=[{TripIndicator=1.0, PassengerType=1, PassengerCount=1, BaseFare=3500.0, GrossAmount=5091.0, Commission=0.0, YQ=400.0, Tax=1591.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {TripIndicator=1.0, PassengerType=2, PassengerCount=1, BaseFare=3500.0, GrossAmount=5091.0, Commission=0.0, YQ=400.0, Tax=1591.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FlightSegments=[{TripIndicator=1.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=6E, FlightNumber=245, Origin=CCU, Destination=RPR, DepartureDateTime=2020-02-21 05:30:00, DepartureDate=Fri-Feb 21,2020, DepartureTime=05:30:00, ArrivalDateTime=2020-02-21 07:10:00, ArrivalDate=Fri-Feb 21,2020, ArrivalTime=07:10:00, Duration=1h40m, FareClass=T, SupplierId=}, {TripIndicator=1.0, SegmentIndicator=2.0, FlightId=, AirCraftType=, AirlineCode=6E, FlightNumber=2486, Origin=RPR, Destination=DEL, DepartureDateTime=2020-02-21 09:40:00, DepartureDate=Fri-Feb 21,2020, DepartureTime=09:40:00, ArrivalDateTime=2020-02-21 11:40:00, ArrivalDate=Fri-Feb 21,2020, ArrivalTime=11:40:00, Duration=2h, FareClass=M, SupplierId=}]}, type=R, status=1.0, Taxoutput={ResponseStatus=1.0, TrackId=TBc4e9773a-a18d-4d77-9987-6aa031269d92, FareBreakdown=[{Currency=INR, PassengerType=1, PassengerCount=1, BaseFare=2185.0, GrossAmount=3292.0, Commission=0.0, YQ=0.0, Tax=1107.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=2, PassengerCount=1, BaseFare=2185.0, GrossAmount=3292.0, Commission=0.0, YQ=0.0, Tax=1107.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=1, PassengerCount=1, BaseFare=2185.0, GrossAmount=2736.0, Commission=0.0, YQ=0.0, Tax=551.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=2, PassengerCount=1, BaseFare=2185.0, GrossAmount=2736.0, Commission=0.0, YQ=0.0, Tax=551.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FareRuledetail=[{Airline=SG, TripIndicator=2, SegmentIndicator=1, Destination=CCU, FareBasisCode=USAVER, FareRule=, Origin=DEL}], adminmarkup=241.12, usermarkup=241.12, totaladult=4370.0, totalchild=4370.0, totalinfant=0.0, totaltax=3316.0, totalgross=12056.0}}
			Map<String, Object> requestdt = (Map<String, Object>) request.get("requestDetails");
			String[] source = requestdt.get("source").toString().split(",");
			 String[] destination = requestdt.get("destination").toString().split(",");
				boolean isDomestic=true;
				if(source[2].equalsIgnoreCase("India")){
					isDomestic=true;
				}else{
					isDomestic=false;
				}
				int totalpassenger=0;
				 String adult="0";
				 if(requestdt.containsKey("adult")){
					 adult=requestdt.get("adult").toString();
					 totalpassenger=Integer.parseInt(adult);
				 }
				 String child="0";
				 if(requestdt.containsKey("child")){
					 child=requestdt.get("child").toString();
					 totalpassenger=totalpassenger+Integer.parseInt(child);
				 }
				 String infant="0";
				 if(requestdt.containsKey("infant")){
					 infant=requestdt.get("infant").toString();
				 }
				 
				 
				 List<com.skyflight.SeatCustomModel.FlightSegments> FlightSegments=new ArrayList<com.skyflight.SeatCustomModel.FlightSegments>();
				
				 List<SSRFlightSegments> SSRFlightSegmentslist=new ArrayList<SSRFlightSegments>();
				 Map<String, Object> searchDetails = (Map<String, Object>) request.get("searchDetails");
				 List<FlightSegments> requestsegment=(List<FlightSegments>) searchDetails.get("FlightSegments");
				 for(int i=0;i<requestsegment.size();i++){
					 Map<String, Object> mapfs=new HashMap<String, Object>();
					 mapfs.put("fs", requestsegment.get(i));
					 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
					 int tripindi=(int)Double.parseDouble(mapf.get("TripIndicator").toString());
					 int segtripindi=(int)Double.parseDouble(mapf.get("SegmentIndicator").toString());
					 com.skyflight.SeatCustomModel.FlightSegments ss=new com.skyflight.SeatCustomModel.FlightSegments(searchDetails.get("ResultIndex").toString(),Integer.toString(tripindi)  ,  Integer.toString(segtripindi), "", mapf.get("AirlineCode").toString(), mapf.get("FareClass").toString(), mapf.get("AirCraftType").toString(),"");
					 FlightSegments.add(ss);
				 }
				 
				 if(request.get("type").toString().equals("R")){
					 Map<String, Object> returnDetail = (Map<String, Object>) request.get("returnDetail");
					 List<FlightSegments> requestreturnDetailsegment=(List<FlightSegments>) returnDetail.get("FlightSegments");
					 for(int i=0;i<requestreturnDetailsegment.size();i++){
						 Map<String, Object> mapfs=new HashMap<String, Object>();
						 mapfs.put("fs", requestreturnDetailsegment.get(i));
						 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
						 int tripindi=(int)Double.parseDouble(mapf.get("TripIndicator").toString());
						 int segtripindi=(int)Double.parseDouble(mapf.get("SegmentIndicator").toString());
						 com.skyflight.SeatCustomModel.FlightSegments ss=new com.skyflight.SeatCustomModel.FlightSegments(returnDetail.get("ResultIndex").toString(),Integer.toString(tripindi)  ,  Integer.toString(segtripindi), "", mapf.get("AirlineCode").toString(), mapf.get("FareClass").toString(), mapf.get("AirCraftType").toString(),"");
						 FlightSegments.add(ss);
					 }
				 }
				 
				 
				 List<SeatPassengerDetails> PassengerDetails=new ArrayList<SeatPassengerDetails>();
				 
				// List<SeatMapInput> SeatMapInput=new ArrayList<SeatMapInput>();
				 SeatMapInput satipy=new SeatMapInput();
				 satipy.setFlightSegments(FlightSegments);
				 satipy.setPassengerDetails(PassengerDetails);
				// SeatMapInput.add(satipy);
				 Map<String, Object> Taxoutput = (Map<String, Object>) request.get("Taxoutput");
				 
				 SeatRequest input=new SeatRequest();
				 input.setAuthentication(WebServerClient.getAuthentication());
				 input.setDomestic(isDomestic);
					input.setTrackId(Taxoutput.get("TrackId").toString());
					input.setTripType(request.get("type").toString());
					input.setSeatMapInput(satipy);
					
					SeatResponse output=WebServerClient.getSeatDetails(input);
					returnData.put("seats", output);
					returnData.put("totalpassenger", totalpassenger);
				/*SSRResponse output=WebServerClient.getMealAndBaggageInfo(input);
				returnData.put("status", output.getResponseStatus());
				returnData.put("meals", output);*/
		}catch(Exception e){
			log.warn("getSeatRequest Exception"+e);
		}
		
		return returnData;
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public Map<String, Object> getMealAndBaggageInfo(Map<String, Object> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			//{requestDetails={class=ECONOMY, source=CCU,Kolkata,India, destination=DEL,Delhi,India, depart=2020-02-21, return=2020-02-23, adult=1, child=1}, returnDetail={IsLCC=false, AirlineId=UK, AirlineName=Air Vistara(UK), stops=1.0, ResultIndex=IB25, totalduration=8h 55m, tripindi=0.0, Fare={Currency=INR, BaseFare=41300.0, Tax=2938.0, YQTax=0.0, OtherCharges=4.0, ServiceCharge=0.0, Commission=0.0, GrossAmount=44238.0, TotalAmount=46007.52, MarkUp=884.76}, FareBreakdown=[{TripIndicator=2.0, PassengerType=1, PassengerCount=1, BaseFare=20650.0, GrossAmount=22119.0, Commission=0.0, YQ=0.0, Tax=1469.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {TripIndicator=2.0, PassengerType=2, PassengerCount=1, BaseFare=20650.0, GrossAmount=22119.0, Commission=0.0, YQ=0.0, Tax=1469.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FlightSegments=[{TripIndicator=2.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=UK, FlightNumber=833, Origin=DEL, Destination=IXZ, DepartureDateTime=2020-02-23 07:20:00, DepartureDate=Sun-Feb 23,2020, DepartureTime=07:20:00, ArrivalDateTime=2020-02-23 13:30:00, ArrivalDate=Sun-Feb 23,2020, ArrivalTime=13:30:00, Duration=6h10m, FareClass=K, SupplierId=}, {TripIndicator=2.0, SegmentIndicator=2.0, FlightId=, AirCraftType=, AirlineCode=UK, FlightNumber=778, Origin=IXZ, Destination=CCU, DepartureDateTime=2020-02-23 13:55:00, DepartureDate=Sun-Feb 23,2020, DepartureTime=13:55:00, ArrivalDateTime=2020-02-23 16:15:00, ArrivalDate=Sun-Feb 23,2020, ArrivalTime=16:15:00, Duration=2h20m, FareClass=K, SupplierId=}]}, searchDetails={IsLCC=true, AirlineId=6E, AirlineName=IndiGo(6E), stops=1.0, ResultIndex=OB11, totalduration=6h 10m, tripindi=0.0, Fare={Currency=INR, BaseFare=7000.0, Tax=3182.0, YQTax=800.0, OtherCharges=472.0, ServiceCharge=0.0, Commission=169.0, GrossAmount=10182.0, TotalAmount=10589.28, MarkUp=203.64}, FareBreakdown=[{TripIndicator=1.0, PassengerType=1, PassengerCount=1, BaseFare=3500.0, GrossAmount=5091.0, Commission=0.0, YQ=400.0, Tax=1591.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {TripIndicator=1.0, PassengerType=2, PassengerCount=1, BaseFare=3500.0, GrossAmount=5091.0, Commission=0.0, YQ=400.0, Tax=1591.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FlightSegments=[{TripIndicator=1.0, SegmentIndicator=1.0, FlightId=, AirCraftType=, AirlineCode=6E, FlightNumber=245, Origin=CCU, Destination=RPR, DepartureDateTime=2020-02-21 05:30:00, DepartureDate=Fri-Feb 21,2020, DepartureTime=05:30:00, ArrivalDateTime=2020-02-21 07:10:00, ArrivalDate=Fri-Feb 21,2020, ArrivalTime=07:10:00, Duration=1h40m, FareClass=T, SupplierId=}, {TripIndicator=1.0, SegmentIndicator=2.0, FlightId=, AirCraftType=, AirlineCode=6E, FlightNumber=2486, Origin=RPR, Destination=DEL, DepartureDateTime=2020-02-21 09:40:00, DepartureDate=Fri-Feb 21,2020, DepartureTime=09:40:00, ArrivalDateTime=2020-02-21 11:40:00, ArrivalDate=Fri-Feb 21,2020, ArrivalTime=11:40:00, Duration=2h, FareClass=M, SupplierId=}]}, type=R, status=1.0, Taxoutput={ResponseStatus=1.0, TrackId=TBc4e9773a-a18d-4d77-9987-6aa031269d92, FareBreakdown=[{Currency=INR, PassengerType=1, PassengerCount=1, BaseFare=2185.0, GrossAmount=3292.0, Commission=0.0, YQ=0.0, Tax=1107.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=2, PassengerCount=1, BaseFare=2185.0, GrossAmount=3292.0, Commission=0.0, YQ=0.0, Tax=1107.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=1, PassengerCount=1, BaseFare=2185.0, GrossAmount=2736.0, Commission=0.0, YQ=0.0, Tax=551.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}, {Currency=INR, PassengerType=2, PassengerCount=1, BaseFare=2185.0, GrossAmount=2736.0, Commission=0.0, YQ=0.0, Tax=551.0, AdditionalTxnFeeOfrd=0.0, AdditionalTxnFeePub=0.0, TransactionFee=0.0}], FareRuledetail=[{Airline=SG, TripIndicator=2, SegmentIndicator=1, Destination=CCU, FareBasisCode=USAVER, FareRule=, Origin=DEL}], adminmarkup=241.12, usermarkup=241.12, totaladult=4370.0, totalchild=4370.0, totalinfant=0.0, totaltax=3316.0, totalgross=12056.0}}
			Map<String, Object> requestdt = (Map<String, Object>) request.get("requestDetails");
			String[] source = requestdt.get("source").toString().split(",");
			 String[] destination = requestdt.get("destination").toString().split(",");
				boolean isDomestic=true;
				if(source[2].equalsIgnoreCase("India")){
					isDomestic=true;
				}else{
					isDomestic=false;
				}
				
				 String adult="0";
				 
				 if(requestdt.containsKey("adult")){
					int adultcount=(int)((double)requestdt.get("adult"));
					 adult=Integer.toString(adultcount);
				 }
				 String child="0";
				 if(requestdt.containsKey("child")){
					 child=Integer.toString((int)((double)requestdt.get("child")));
				 }
				 String infant="0";
				 if(requestdt.containsKey("infant")){
					 infant=Integer.toString((int)((double)requestdt.get("infant")));
				 }
				 
				 List<SSRFlightSegments> SSRFlightSegmentslist=new ArrayList<SSRFlightSegments>();
				 Map<String, Object> searchDetails = (Map<String, Object>) request.get("searchDetails");
				 List<FlightSegments> requestsegment=(List<FlightSegments>) searchDetails.get("FlightSegments");
				 for(int i=0;i<requestsegment.size();i++){
					 Map<String, Object> mapfs=new HashMap<String, Object>();
					 mapfs.put("fs", requestsegment.get(i));
					 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
					 SSRFlightSegments ss=new SSRFlightSegments(searchDetails.get("ResultIndex").toString(), Integer.toString((int)Double.parseDouble(mapf.get("TripIndicator").toString())) , Integer.toString((int)Double.parseDouble(mapf.get("SegmentIndicator").toString())), "", mapf.get("AirlineCode").toString(), mapf.get("FareClass").toString(), i);
					 SSRFlightSegmentslist.add(ss);
				 }
				 
				 if(request.get("type").toString().equals("R")){
					 Map<String, Object> returnDetail = (Map<String, Object>) request.get("returnDetail");
					 List<FlightSegments> requestreturnDetailsegment=(List<FlightSegments>) returnDetail.get("FlightSegments");
					 for(int i=0;i<requestreturnDetailsegment.size();i++){
						 Map<String, Object> mapfs=new HashMap<String, Object>();
						 mapfs.put("fs", requestreturnDetailsegment.get(i));
						 Map<String, Object> mapf=(Map<String, Object>) mapfs.get("fs");
						 SSRFlightSegments ss=new SSRFlightSegments(returnDetail.get("ResultIndex").toString(), Integer.toString((int)Double.parseDouble(mapf.get("TripIndicator").toString())) , Integer.toString((int)Double.parseDouble(mapf.get("SegmentIndicator").toString())), "", mapf.get("AirlineCode").toString(), mapf.get("FareClass").toString(), i);
						 SSRFlightSegmentslist.add(ss);
					 }
				 }
				
				SSRRequest SSRRequestreq=new SSRRequest(SSRFlightSegmentslist, Integer.parseInt(adult), Integer.parseInt(child), Integer.parseInt(infant), "0");
				/*List<SSRRequest> sSRRequest=new ArrayList<SSRRequest>();
				sSRRequest.add(SSRRequestreq);*/
				Map<String, Object> Taxoutput = (Map<String, Object>) request.get("Taxoutput");
				FetchMealBaggageDetails input=new FetchMealBaggageDetails();
				input.setAuthentication(WebServerClient.getAuthentication());
				input.setDomestic(isDomestic);
				input.setTrackId(Taxoutput.get("TrackId").toString());
				input.setTripType(request.get("type").toString());
				input.setSSRRequest(SSRRequestreq);
				
				SSRResponse output=WebServerClient.getMealAndBaggageInfo(input);
				returnData.put("status", output.getResponseStatus());
				returnData.put("meals", output);
		}catch(Exception e){
			log.warn("getMealAndBaggageInfo Exception"+e);
		}
		
		return returnData;
	}




}