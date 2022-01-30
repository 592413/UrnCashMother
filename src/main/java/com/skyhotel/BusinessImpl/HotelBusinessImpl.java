package com.skyhotel.BusinessImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CommissionService;
import com.recharge.model.AssignedPackage;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.PackageWiseChargeCommDao;
import com.recharge.utill.GenerateRandomNumber;
import com.skyflight.model.Airline;
import com.skyflight.model.Authentication;
import com.skyflight.model.Markup;
import com.skyflight.servicedao.MarkupDao;
import com.skyhotel.BusinessDao.HotelBusinessDao;
import com.skyhotel.CustomModel.BookRoomInput;
import com.skyhotel.CustomModel.BookingHotelRoomDetails;
import com.skyhotel.CustomModel.HotelBookResponse;
import com.skyhotel.CustomModel.HotelDetailsInput;
import com.skyhotel.CustomModel.HotelDetailsOutput;
import com.skyhotel.CustomModel.HotelPassenger;
import com.skyhotel.CustomModel.HotelPassengers;
import com.skyhotel.CustomModel.HotelRoomDetails;
import com.skyhotel.CustomModel.HotelRoomPrice;
import com.skyhotel.CustomModel.HotelSearchInput;
import com.skyhotel.CustomModel.RoomBlockInput;
import com.skyhotel.CustomModel.RoomBlockOutput;
import com.skyhotel.CustomModel.SearchResult;
import com.skyhotel.CustomModel.SearchRoomGuests;
import com.skyhotel.ServiceDao.CityDao;
import com.skyhotel.ServiceDao.HotelBookingDao;
import com.skyhotel.ServiceDao.HotelDetailDao;
import com.skyhotel.ServiceDao.HotelLeadPassengerDao;
import com.skyhotel.model.City;
import com.skyhotel.model.HotelBooking;
import com.skyhotel.model.HotelDetail;
import com.skyhotel.model.HotelLeadPassenger;
import com.skyhotel.webserver.HOtelWebserverClient;
import com.skyhotel.webserver.UtilityHotel;
@Service("HotelBusinessDao")
public class HotelBusinessImpl implements HotelBusinessDao{
	private static final Logger log = Logger.getLogger(HotelBusinessDao.class);
	
	@Autowired CityDao citydao;
	@Autowired HotelLeadPassengerDao HotelLeadPassengerDao;
	@Autowired HotelBookingDao HotelBookingDao;
	@Autowired HotelDetailDao HotelDetailDao;
	@Autowired MarkupDao markupdao;
	@Autowired CommissionService commissionService;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired PackageWiseChargeCommDao PackageWiseChargeCommDao;
	
	@Override
	public Map<String, Object> SaveHotelMarkUpData(Map<String, String> request, User user) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, Object> param = null;
		boolean flag = false;
		try {
				
				param = new HashMap<String, Object>();
				param.put("airline_code", "hotel");
				param.put("username", user.getUserId());
				//System.out.println("airline_code:::::::::::::::"+air.getAirline_code());
				List<Markup> list = markupdao.getMarkupByNamedQuery("getMarkupByAirlinecodeAndUsername",param);
			   // System.out.println("MarkupLIST::::::::::"+list.size());
				if (list.size() > 0) {
					   Markup markup1 = list.get(0);
					   markup1.setMarkup_type(request.get("markup_type"));
					   markup1.setMarkup_value(Double.parseDouble(request.get("markup_value")));
					   flag=markupdao.updateMarkup(list.get(0));
				
				} else {
					Markup markup = new Markup(user.getUserId(), "hotel",
					Double.parseDouble(request.get("markup_value")), request.get("markup_type"));
					flag=markupdao.insertMarkup(markup);
					
				}
			
			if(flag){
				returndata.put("status","1");
				returndata.put("message","success");
			}else{
				returndata.put("status","0");
			}
		} catch (Exception e) {
			log.error("saveMarkUpData::::::::::::" + e);
			returndata.put("status","0");
		}
		return returndata;
	}
	
	@Override
	public List<String> getDestinationCities(String term) {
		List<String> cities = new ArrayList<>();
		List<City> list = citydao.getCity(term);
		for (City c : list) {
		/*	if(c.getState().equals(" ")){
				cities.add(c.getCityName()  + "," + " " + c.getCountry() + "");	
			}else{*/
			cities.add(c.getCityName() + "" + "," + "" + c.getCountry() + "");
			//}
		}
		return cities;
	}
	
	
	@Override
	public Map<String, Object> searchHotal(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SearchResult output=new SearchResult();
		try{
			System.out.println(request);
			String destination[]=request.get("destination").split(",");
			List<City> list = citydao.getCity(destination[0]);
			
			String night=UtilityHotel.DifferenceDate(request.get("checkin").toString(), request.get("checkout").toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			 String checkin = request.get("checkin").toString();
			 Date checkindate = sdf.parse(checkin);
			/* String checkout = request.get("checkout").toString();
			 Date checkoutdate = sdf.parse(checkin);*/
			 List<SearchRoomGuests> RoomGuests=new ArrayList<>();
			 //--------room1--------------------------------------
			 SearchRoomGuests sr=new SearchRoomGuests();
			 sr.setAdultNo(request.get("adultno1"));
			 sr.setChildNo(request.get("childno1"));
			 if(!request.get("childno1").equals("0")){
				 String chage="0";
				 for(int i=0;i<Integer.parseInt(request.get("childno1"));i++){
					 if(i==0){
						 chage=request.get("childage11");
					 }if(i==1){
						 chage=chage+","+request.get("childage12");
					 }
				 }
				 sr.setChildAge(chage);
			 }
			 RoomGuests.add(sr);
			 //-----------room2---------------------------------
			 if(!request.get("adultno2").equals("0")){
				 SearchRoomGuests sr1=new SearchRoomGuests();
				 sr1.setAdultNo(request.get("adultno2"));
				 sr1.setChildNo(request.get("childno2"));
				 if(!request.get("childno2").equals("0")){
					 String chage="0";
					 for(int i=0;i<Integer.parseInt(request.get("childno2"));i++){
						 if(i==0){
							 chage=request.get("childage21");
						 }if(i==1){
							 chage=chage+","+request.get("childage22");
						 }
					 }
					 sr1.setChildAge(chage);
				 }
				 RoomGuests.add(sr1);
			 }
			///------------------room3----------------------------
			 if(!request.get("adultno3").equals("0")){
				 SearchRoomGuests sr1=new SearchRoomGuests();
				 sr1.setAdultNo(request.get("adultno3"));
				 sr1.setChildNo(request.get("childno3"));
				 if(!request.get("childno3").equals("0")){
					 String chage="0";
					 for(int i=0;i<Integer.parseInt(request.get("childno3"));i++){
						 if(i==0){
							 chage=request.get("childage31");
						 }if(i==1){
							 chage=chage+","+request.get("childage32");
						 }
					 }
					 sr1.setChildAge(chage);
				 }
				 RoomGuests.add(sr1);
			 }
			///------------------room4----------------------------
			 if(!request.get("adultno4").equals("0")){
				 SearchRoomGuests sr1=new SearchRoomGuests();
				 sr1.setAdultNo(request.get("adultno4"));
				 sr1.setChildNo(request.get("childno4"));
				 if(!request.get("childno4").equals("0")){
					 String chage="0";
					 for(int i=0;i<Integer.parseInt(request.get("childno4"));i++){
						 if(i==0){
							 chage=request.get("childage41");
						 }if(i==1){
							 chage=chage+","+request.get("childage42");
						 }
					 }
					 sr1.setChildAge(chage);
				 }
				 RoomGuests.add(sr1);
			 }
			 
			 
			 HotelSearchInput input=new HotelSearchInput();
			 input.setAuthentication(HOtelWebserverClient.getAuthentication());
			 input.setCheckIn(sdf2.format(checkindate));
			 input.setNights(night);
			 input.setCityId(list.get(0).getCityId());
			 input.setCountryCode(list.get(0).getCountryId());
			 input.setRoomGuests(RoomGuests);
			 int roomno=RoomGuests.size();
			 Map<String, Object> param = new HashMap<String, Object>();
				param = new HashMap<String, Object>();
				param.put("airline_code", "hotel");
				param.put("username", "admin");
				//System.out.println("airline_code:::::::::::::::"+air.getAirline_code());
				List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByAirlinecodeAndUsername",param);
			  
				
				param = new HashMap<String, Object>();
				param.put("username",userDetails.getUserName());
				param.put("airline_code", "hotel");
				List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByAirlinecodeAndUsername",param);
				AssignedPackage asp = new AssignedPackage();
				param = new HashMap<String, Object>();
				param.put("user_id", userDetails.getUserId());
				param.put("service_type", "Hotel");
				List<PackageWiseChargeComm> lists=new ArrayList<>();
				List<AssignedPackage> plist = assignedPackage
						.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
				if (plist.size() > 0) {
					
					param = new HashMap<String, Object>();
					param.put("package_id", plist.get(0).getPackage_id());
					 lists=PackageWiseChargeCommDao.getPackageDetailByNamedQuery("getPackageWiseChargeCommbypackage", param);
					//pck=lists.get(0);
				}
			 
			 output=HOtelWebserverClient.getsearchHotel(input,adminmarkupllist,usermarkuplist,lists,roomno);
			 if(output.getResponseStatus()==1){
					returnData.put("status", "1");
					returnData.put("RoomGuests", RoomGuests);
				}else{
					returnData.put("status", "0");	
				}
				returnData.put("output", output);
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("searchHotelBusinessImpl:::::::::::::"+e);
		}
		return returnData;
	}


	@Override
	public HotelDetailsOutput searchHotalDetails(Map<String, String> request, User userDetails) {
		HotelDetailsOutput output=new HotelDetailsOutput();
		try{
			System.out.println(request);
			HotelDetailsInput input= new HotelDetailsInput();
			input.setAuthentication(HOtelWebserverClient.getAuthentication());
			input.setTrackId(request.get("userTrackId"));
			input.setHotelId(request.get("HotelId"));
			input.setProviderid(request.get("Providerid"));
			input.setResultIndex(request.get("ResultIndex"));
			input.setSearchid(request.get("Searchid"));
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("adminmarkup", request.get("adminmarkup"));
			param.put("usermarkup", request.get("usermarkup"));
			param.put("commission", request.get("commission"));
			output=HOtelWebserverClient.getsearchHotalDetails(input,param);
			if(output.getResponseStatus()==1){
				output.setAdminmarkup(Double.parseDouble(request.get("adminmarkup")));
				output.setUsermarkup(Double.parseDouble(request.get("usermarkup")));
				output.setCommission(Double.parseDouble(request.get("commission")));
			}
		}catch(Exception e){
			log.error("searchHotalDetailsBusinessImpl:::::::::::::"+e);
		}
		
		return output;
	}


	@Override
	public RoomBlockOutput getroomblock(Map<String, Object> request) {
		System.out.println(request);
		RoomBlockOutput output=new RoomBlockOutput(); 
		try{
			Map<String, Object> searchhotelreq=(Map<String, Object>) request.get("searchhotelreq");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
			 Date date = sdf.parse(request.get("checkin").toString());
			RoomBlockInput input=new RoomBlockInput();
			input.setAuthentication(HOtelWebserverClient.getAuthentication());
			input.setTrackId(request.get("userTrackId").toString());
			input.setResultIndex(searchhotelreq.get("ResultIndex").toString());
			input.setSearchid(searchhotelreq.get("Searchid").toString());
			input.setProviderid(searchhotelreq.get("Providerid").toString());
			input.setHotelId(searchhotelreq.get("HotelId").toString());
			input.setHotelName(searchhotelreq.get("HotelName").toString());
			input.setGuestNationality("IN");
			if(request.get("roomno").toString().contains(".0")){
				System.out.println(request.get("roomno").toString());
				String roomy=request.get("roomno").toString();
				input.setNoOfRooms(roomy.substring(0,roomy.indexOf(".")));
			}else{
				input.setNoOfRooms(request.get("roomno").toString());
			}
			
			
			input.setReferenceNo(GenerateRandomNumber.referenceNO());
			input.setCheckIn(sdf2.format(date));
			input.setNights(request.get("nightno").toString());
			List<HotelRoomDetails> roomgobj=new ArrayList<>();
			Map<String, Object> reqroom=(Map<String, Object>) request.get("request");
			List<Map<String, Object>> listroomdt=new ArrayList<>();
			listroomdt=(List<Map<String, Object>>) reqroom.get("HotelRoomsDetail");
			for(int i=0;i<listroomdt.size();i++){
				Map<String, Object> roomsgobj=listroomdt.get(i);
				HotelRoomDetails HotelR=new HotelRoomDetails();
				HotelR.setRoomCode(roomsgobj.get("RoomCode").toString());
				HotelR.setRatePlanCode(roomsgobj.get("RatePlanCode").toString());
				HotelR.setRoomTypeCode(roomsgobj.get("RoomTypeCode").toString());
				HotelR.setRoomTypeName(roomsgobj.get("RoomTypeName").toString());
				
				Map<String, Object> price=(Map<String, Object>) roomsgobj.get("HotelRoomPrice");
				HotelRoomPrice hrp=new HotelRoomPrice(Double.parseDouble(price.get("RoomPrice").toString()),Double.parseDouble(price.get("Tax").toString()),Double.parseDouble(price.get("ExtraGuestCharge").toString()),Double.parseDouble(price.get("ChildCharge").toString()),Double.parseDouble(price.get("OtherCharges").toString()),Double.parseDouble(price.get("Discount").toString()),Double.parseDouble(price.get("ServiceTax").toString()),"INR");
				
				/*hrp.setRoomPrice(Double.parseDouble(price.get("RoomPrice").toString()));
				hrp.setTax(Double.parseDouble(price.get("Tax").toString()));
				hrp.setExtraGuestCharge(Double.parseDouble(price.get("ExtraGuestCharge").toString()));
				hrp.setChildCharge(Double.parseDouble(price.get("ChildCharge").toString()));
				hrp.setOtherCharges(Double.parseDouble(price.get("OtherCharges").toString()));
				hrp.setDiscount(Double.parseDouble(price.get("Discount").toString()));
				hrp.setServiceTax(Double.parseDouble(price.get("ServiceTax").toString()));
				hrp.setCurrencyCode("INR");
				HotelR.setHotelRoomPrice(hrp);*/
				HotelR.setHotelRoomPrice(hrp);
				roomgobj.add(HotelR);
			}
			
			input.setHotelRoomDetails(roomgobj);
			output=HOtelWebserverClient.getroomblock(input);
			
			
		}catch(Exception e){
			log.error("getroomblock:::::::::::::"+e);
		}
		return output;
		
	}


	@Override
	public Map<String, Object> getroombook(Map<String, Object> request,User user) {
		HotelBookResponse output=new HotelBookResponse();
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			System.out.println(request);
			Map<String, Object> searchhotelreq=(Map<String, Object>) request.get("searchhotelreq");
			
			BookRoomInput input=new BookRoomInput();
			input.setAuthentication(HOtelWebserverClient.getAuthentication());
			input.setAvailabilityType("Confirm");
			input.setTrackId(request.get("userTrackId").toString());
			input.setResultIndex(searchhotelreq.get("ResultIndex").toString());
			input.setSearchid(searchhotelreq.get("Searchid").toString());
			input.setProviderid(searchhotelreq.get("Providerid").toString());
			input.setHotelId(searchhotelreq.get("HotelId").toString());
			input.setHotelName(searchhotelreq.get("HotelName").toString());
			input.setGuestNationality("IN");
			input.setReferenceNo(GenerateRandomNumber.referenceNO());
			List<Map<String, Object>> guest=(List<Map<String, Object>>) request.get("guest");
			input.setNoOfRooms(Integer.toString(guest.size()));
			Map<String, Object> hotelblockresult=(Map<String, Object>) request.get("hotelblockresult");
			System.out.println(hotelblockresult);
			 List<HotelRoomDetails> HotelRoomDetails=new ArrayList<>();
			
			 List<String> roomcd=new ArrayList<>();
			 
			List<Map<String, Object>> listroomdt=new ArrayList<>();
			listroomdt=(List<Map<String, Object>>) hotelblockresult.get("HotelRoomsDetail");
			for(int i=0;i<listroomdt.size();i++){
				Map<String, Object> roomsgobj=listroomdt.get(i);
				HotelRoomDetails HotelR=new HotelRoomDetails();
				HotelR.setRoomCode(roomsgobj.get("RoomCode").toString());
				roomcd.add(roomsgobj.get("RoomCode").toString());
				HotelR.setRatePlanCode(roomsgobj.get("RatePlanCode").toString());
				HotelR.setRoomTypeCode(roomsgobj.get("RoomTypeCode").toString());
				HotelR.setRoomTypeName(roomsgobj.get("RoomTypeName").toString());
				
				Map<String, Object> price=(Map<String, Object>) roomsgobj.get("HotelRoomPrice");
				HotelRoomPrice hrp=new HotelRoomPrice(Double.parseDouble(price.get("RoomPrice").toString()),Double.parseDouble(price.get("Tax").toString()),Double.parseDouble(price.get("ExtraGuestCharge").toString()),Double.parseDouble(price.get("ChildCharge").toString()),Double.parseDouble(price.get("OtherCharges").toString()),Double.parseDouble(price.get("Discount").toString()),Double.parseDouble(price.get("ServiceTax").toString()),"INR");
				
				HotelR.setHotelRoomPrice(hrp);
				
				HotelRoomDetails.add(HotelR);
			}
			input.setHotelRoomDetails(HotelRoomDetails);
			
			List<HotelPassenger> HotelPassenger=new ArrayList<>();
			Map<String, Object> passengerdt=(Map<String, Object>) request.get("request");
			List<Map<String, Object>> listguest=(List<Map<String, Object>>) request.get("guest");
			System.out.println(listguest);
			for(int i=0;i<listguest.size();i++){
				Map<String, Object> listguestobj=listguest.get(i);
				if(i==0){
					
					HotelPassenger HotelPassengerobj=new HotelPassenger();
					HotelPassengerobj.setRoomCode(roomcd.get(i));
					HotelPassengerobj.setTitle(passengerdt.get("adulttitel11").toString());
					HotelPassengerobj.setFirstName(passengerdt.get("adultfname11").toString());
					HotelPassengerobj.setLastName(passengerdt.get("adultlname11").toString());
					HotelPassengerobj.setAge("30");
					HotelPassengerobj.setPaxType(1);
					HotelPassengerobj.setLeadPassenger(true);
					HotelPassengerobj.setEmail(passengerdt.get("email").toString());
					HotelPassengerobj.setPhoneno(passengerdt.get("mobile").toString());
					HotelPassenger.add(HotelPassengerobj);
					if(listguestobj.get("AdultNo").equals("2")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("adulttitel12").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("adultfname12").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("adultlname12").toString());
						HotelPassengerobj2.setAge("30");
						HotelPassengerobj2.setPaxType(1);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
					}
					
					
					if(!listguestobj.get("ChildNo").equals("0")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("childtitel11").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("childfname11").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("childlname11").toString());
						if(listguestobj.get("ChildAge").toString().contains(",")){
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj2.setAge(age[0]);
						}else{
							HotelPassengerobj2.setAge(listguestobj.get("ChildAge").toString());
						}
						
						HotelPassengerobj2.setPaxType(2);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
						
						if(listguestobj.get("ChildNo").equals("2")){
							HotelPassenger HotelPassengerobj3=new HotelPassenger();
							HotelPassengerobj3.setRoomCode(roomcd.get(i));
							HotelPassengerobj3.setTitle(passengerdt.get("childtitel12").toString());
							HotelPassengerobj3.setFirstName(passengerdt.get("childfname12").toString());
							HotelPassengerobj3.setLastName(passengerdt.get("childlname12").toString());
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj3.setAge(age[1]);
							HotelPassengerobj3.setPaxType(2);
							HotelPassengerobj3.setLeadPassenger(false);
							HotelPassengerobj3.setEmail(passengerdt.get("email").toString());
							HotelPassengerobj3.setPhoneno(passengerdt.get("mobile").toString());
							HotelPassenger.add(HotelPassengerobj3);
						}
					}
					
				}
				if(i==1){
					
					HotelPassenger HotelPassengerobj=new HotelPassenger();
					HotelPassengerobj.setRoomCode(roomcd.get(i));
					HotelPassengerobj.setTitle(passengerdt.get("adulttitel21").toString());
					HotelPassengerobj.setFirstName(passengerdt.get("adultfname21").toString());
					HotelPassengerobj.setLastName(passengerdt.get("adultlname21").toString());
					HotelPassengerobj.setAge("30");
					HotelPassengerobj.setPaxType(1);
					HotelPassengerobj.setLeadPassenger(true);
					HotelPassengerobj.setEmail(passengerdt.get("email").toString());
					HotelPassengerobj.setPhoneno(passengerdt.get("mobile").toString());
					HotelPassenger.add(HotelPassengerobj);
					if(listguestobj.get("AdultNo").equals("2")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("adulttitel22").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("adultfname22").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("adultlname22").toString());
						HotelPassengerobj2.setAge("30");
						HotelPassengerobj2.setPaxType(1);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
					}
					
					
					if(!listguestobj.get("ChildNo").equals("0")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("childtitel21").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("childfname21").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("childlname21").toString());
						if(listguestobj.get("ChildAge").toString().contains(",")){
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj2.setAge(age[0]);
						}else{
							HotelPassengerobj2.setAge(listguestobj.get("ChildAge").toString());
						}
						
						HotelPassengerobj2.setPaxType(2);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
						
						if(listguestobj.get("ChildNo").equals("2")){
							HotelPassenger HotelPassengerobj3=new HotelPassenger();
							HotelPassengerobj3.setRoomCode(roomcd.get(i));
							HotelPassengerobj3.setTitle(passengerdt.get("childtitel22").toString());
							HotelPassengerobj3.setFirstName(passengerdt.get("childfname22").toString());
							HotelPassengerobj3.setLastName(passengerdt.get("childlname22").toString());
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj3.setAge(age[1]);
							HotelPassengerobj3.setPaxType(2);
							HotelPassengerobj3.setLeadPassenger(false);
							HotelPassengerobj3.setEmail(passengerdt.get("email").toString());
							HotelPassengerobj3.setPhoneno(passengerdt.get("mobile").toString());
							HotelPassenger.add(HotelPassengerobj3);
						}
					}
					
				}
				if(i==3){
					
					HotelPassenger HotelPassengerobj=new HotelPassenger();
					HotelPassengerobj.setRoomCode(roomcd.get(i));
					HotelPassengerobj.setTitle(passengerdt.get("adulttitel31").toString());
					HotelPassengerobj.setFirstName(passengerdt.get("adultfname31").toString());
					HotelPassengerobj.setLastName(passengerdt.get("adultlname31").toString());
					HotelPassengerobj.setAge("30");
					HotelPassengerobj.setPaxType(1);
					HotelPassengerobj.setLeadPassenger(true);
					HotelPassengerobj.setEmail(passengerdt.get("email").toString());
					HotelPassengerobj.setPhoneno(passengerdt.get("mobile").toString());
					HotelPassenger.add(HotelPassengerobj);
					if(listguestobj.get("AdultNo").equals("2")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("adulttitel32").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("adultfname32").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("adultlname32").toString());
						HotelPassengerobj2.setAge("30");
						HotelPassengerobj2.setPaxType(1);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
					}
					
					
					if(!listguestobj.get("ChildNo").equals("0")){
						HotelPassenger HotelPassengerobj2=new HotelPassenger();
						HotelPassengerobj2.setRoomCode(roomcd.get(i));
						HotelPassengerobj2.setTitle(passengerdt.get("childtitel31").toString());
						HotelPassengerobj2.setFirstName(passengerdt.get("childfname31").toString());
						HotelPassengerobj2.setLastName(passengerdt.get("childlname31").toString());
						if(listguestobj.get("ChildAge").toString().contains(",")){
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj2.setAge(age[0]);
						}else{
							HotelPassengerobj2.setAge(listguestobj.get("ChildAge").toString());
						}
						
						HotelPassengerobj2.setPaxType(2);
						HotelPassengerobj2.setLeadPassenger(false);
						HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
						HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
						HotelPassenger.add(HotelPassengerobj2);
						
						if(listguestobj.get("ChildNo").equals("2")){
							HotelPassenger HotelPassengerobj3=new HotelPassenger();
							HotelPassengerobj3.setRoomCode(roomcd.get(i));
							HotelPassengerobj3.setTitle(passengerdt.get("childtitel32").toString());
							HotelPassengerobj3.setFirstName(passengerdt.get("childfname32").toString());
							HotelPassengerobj3.setLastName(passengerdt.get("childlname32").toString());
							String age[]=listguestobj.get("ChildAge").toString().split(",");
							HotelPassengerobj3.setAge(age[1]);
							HotelPassengerobj3.setPaxType(2);
							HotelPassengerobj3.setLeadPassenger(false);
							HotelPassengerobj3.setEmail(passengerdt.get("email").toString());
							HotelPassengerobj3.setPhoneno(passengerdt.get("mobile").toString());
							HotelPassenger.add(HotelPassengerobj3);
						}
					}
					
				}
if(i==4){
	
	HotelPassenger HotelPassengerobj=new HotelPassenger();
	HotelPassengerobj.setRoomCode(roomcd.get(i));
	HotelPassengerobj.setTitle(passengerdt.get("adulttitel41").toString());
	HotelPassengerobj.setFirstName(passengerdt.get("adultfname41").toString());
	HotelPassengerobj.setLastName(passengerdt.get("adultlname41").toString());
	HotelPassengerobj.setAge("30");
	HotelPassengerobj.setPaxType(1);
	HotelPassengerobj.setLeadPassenger(true);
	HotelPassengerobj.setEmail(passengerdt.get("email").toString());
	HotelPassengerobj.setPhoneno(passengerdt.get("mobile").toString());
	HotelPassenger.add(HotelPassengerobj);
	if(listguestobj.get("AdultNo").equals("2")){
		HotelPassenger HotelPassengerobj2=new HotelPassenger();
		HotelPassengerobj2.setRoomCode(roomcd.get(i));
		HotelPassengerobj2.setTitle(passengerdt.get("adulttitel42").toString());
		HotelPassengerobj2.setFirstName(passengerdt.get("adultfname42").toString());
		HotelPassengerobj2.setLastName(passengerdt.get("adultlname42").toString());
		HotelPassengerobj2.setAge("30");
		HotelPassengerobj2.setPaxType(1);
		HotelPassengerobj2.setLeadPassenger(false);
		HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
		HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
		HotelPassenger.add(HotelPassengerobj2);
	}
	
	
	if(!listguestobj.get("ChildNo").equals("0")){
		HotelPassenger HotelPassengerobj2=new HotelPassenger();
		HotelPassengerobj2.setRoomCode(roomcd.get(i));
		HotelPassengerobj2.setTitle(passengerdt.get("childtitel41").toString());
		HotelPassengerobj2.setFirstName(passengerdt.get("childfname41").toString());
		HotelPassengerobj2.setLastName(passengerdt.get("childlname41").toString());
		if(listguestobj.get("ChildAge").toString().contains(",")){
			String age[]=listguestobj.get("ChildAge").toString().split(",");
			HotelPassengerobj2.setAge(age[0]);
		}else{
			HotelPassengerobj2.setAge(listguestobj.get("ChildAge").toString());
		}
		
		HotelPassengerobj2.setPaxType(2);
		HotelPassengerobj2.setLeadPassenger(false);
		HotelPassengerobj2.setEmail(passengerdt.get("email").toString());
		HotelPassengerobj2.setPhoneno(passengerdt.get("mobile").toString());
		HotelPassenger.add(HotelPassengerobj2);
		
		if(listguestobj.get("ChildNo").equals("2")){
			HotelPassenger HotelPassengerobj3=new HotelPassenger();
			HotelPassengerobj3.setRoomCode(roomcd.get(i));
			HotelPassengerobj3.setTitle(passengerdt.get("childtitel42").toString());
			HotelPassengerobj3.setFirstName(passengerdt.get("childfname42").toString());
			HotelPassengerobj3.setLastName(passengerdt.get("childlname42").toString());
			String age[]=listguestobj.get("ChildAge").toString().split(",");
			HotelPassengerobj3.setAge(age[1]);
			HotelPassengerobj3.setPaxType(2);
			HotelPassengerobj3.setLeadPassenger(false);
			HotelPassengerobj3.setEmail(passengerdt.get("email").toString());
			HotelPassengerobj3.setPhoneno(passengerdt.get("mobile").toString());
			HotelPassenger.add(HotelPassengerobj3);
		}
	}
	
}
				
			}
			input.setHotelPassenger(HotelPassenger);
			
			
			output=HOtelWebserverClient.getroombook(input);
			System.out.println(output.getResponseStatus());
			if(output.getResponseStatus()==1){
				System.out.println("output::::::::::::"+output);
				HotelDetail  HotelDetail=new HotelDetail(output.getHotelName(), output.getCity(), "-", "-", output.getAddress1(), "-", output.getStarRating(), "-", output.getBookingId());
				HotelDetailDao.insertHotelDetail(HotelDetail);
				String bookdt[]=output.getBookingDate().split(" ");
				int roomno=output.getBookingHotelRoomDetails().size();
				HotelBooking HotelBooking=new HotelBooking(output.getBookingId(),output.getInvoiceNumber() ,"BOOKED", roomno, output.getBookingHotelRoomDetails().get(0).getRoomTypeName(), output.getCheckIn(), output.getCheckOut(), bookdt[0], bookdt[1], user.getUserId(), output.getHotelName(), output.getTrackId(), 5, output.getConfirmationNo(), output.getBookingRefNo(),"WEB");
				HotelBookingDao.insertHotelBooking(HotelBooking);
				List<BookingHotelRoomDetails> BookingHotelRoomDetails=output.getBookingHotelRoomDetails();
				for(int i=0;i<BookingHotelRoomDetails.size();i++){
					BookingHotelRoomDetails BookingHotelRoomDet=BookingHotelRoomDetails.get(i);
					List<HotelPassengers> HotelPassengerslist=BookingHotelRoomDet.getHotelPassengers();
					for(int j=0;j<HotelPassengerslist.size();j++){
						HotelPassengers HotelPassengers=HotelPassengerslist.get(j);
						String name= HotelPassengers.getTitle()+" "+HotelPassengers.getFirstName()+" "+HotelPassengers.getLastName();
						HotelLeadPassenger HotelLeadPassenger=new HotelLeadPassenger(output.getBookingId(),name, HotelPassengers.getPhoneno(), HotelPassengers.getEmail(), BookingHotelRoomDet.getRoomCode(), HotelPassengers.isLeadPassenger(), Integer.parseInt(HotelPassengers.getAge()), HotelPassengers.getPaxType());
						HotelLeadPassengerDao.insertHotelLeadPassenger(HotelLeadPassenger);
					}
					
				}
				
				returnData.put("status", "1");
				returnData.put("output", output);
			}else{
				returnData.put("status", "0");
			}
			
		}catch(Exception e){
			log.error("getroombook:::::::::::::"+e);
			returnData.put("status", "0");
		}
		return returnData;
	}

	
	@Override
	public Map<String, Object> viewHotelBooikngReport(User userDetails,Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			
		}catch(Exception e){
			log.error("viewHotelBooikngReport:::::::::::::"+e);	
		}
		return returnData;
	}
}
