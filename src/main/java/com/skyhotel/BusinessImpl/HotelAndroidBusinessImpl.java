package com.skyhotel.BusinessImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skyhotel.BusinessDao.HotelBusinessDao;
import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AssignedPackage;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.PackageWiseChargeCommDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.UtilityClass;
import com.skyflight.model.Airport_code;
import com.skyflight.model.Markup;
import com.skyflight.servicedao.MarkupDao;
import com.skyhotel.BusinessDao.Hotelandroidbustnessdao;
import com.skyhotel.CustomModel.BookRoomInput;
import com.skyhotel.CustomModel.BookingHotelRoomDetails;
import com.skyhotel.CustomModel.HotelBookResponse;
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
@Service("Hotelandroidbustnessdao")
public class HotelAndroidBusinessImpl implements Hotelandroidbustnessdao{
	private static final Logger log = Logger.getLogger(Hotelandroidbustnessdao.class);
	
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired CityDao citydao;
	@Autowired HotelBusinessDao  hotelBusinessDao;
	@Autowired HotelLeadPassengerDao HotelLeadPassengerDao;
	@Autowired HotelBookingDao HotelBookingDao;
	@Autowired HotelDetailDao HotelDetailDao;
	@Autowired MarkupDao markupdao;
	@Autowired CommissionService commissionService;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired PackageWiseChargeCommDao PackageWiseChargeCommDao;
	
	@Override
	public Map<String, Object> hotelcity(String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		JSONObject requestObj = new JSONObject(request);
		try{
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {
				JSONObject authJson = requestObj.getJSONObject("Authentication");
				param1 = new HashMap<String, String>();
				param1.put("userId", authJson.get("LoginId").toString());
				param1.put("password", authJson.get("Password").toString());
				String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param1);
				if (username != null) {
					List<City> list=citydao.getAllCity();
					returnData.put("city", list);
					returnData.put("status", "1");
				}
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			returnData.put("message", e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> searchHotalapps(String request) {
		SearchResult output=new SearchResult();
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		Map<String, Object> param3 = new HashMap<String, Object>();
		try{
			JSONObject requestObj = new JSONObject(request);
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {
				JSONObject authJson = requestObj.getJSONObject("Authentication");
				if (authJson.isNull("LoginId") || authJson.isNull("Password")) {
					returnData.put("status", "0");
					returnData.put("message", "LoginId and Password Cannot be null.");
				} else {
					
						param1 = new HashMap<String, String>();
						param1.put("userId", authJson.get("LoginId").toString());
						param1.put("password", authJson.get("Password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param1);
						if (username != null) {
							if (requestObj.isNull("searchHotal")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid searchHotal Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("searchHotal"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								inputData.put("source", "APP");
								
								System.out.println(inputData);
								
								 List<SearchRoomGuests> RoomGuests=new ArrayList<>();
								 
								String RoomGueststr=inputData.get("RoomGuests");
								JSONArray jsonArray = new JSONArray(RoomGueststr); 
								for(int i=0;i<jsonArray.length();i++){
									JSONObject jobj=jsonArray.getJSONObject(i);
									SearchRoomGuests sr=new SearchRoomGuests();
									sr.setAdultNo(jobj.getString("AdultNo"));
									sr.setChildNo(jobj.getString("ChildNo"));
									if(!jobj.getString("ChildNo").equals("0")){
										sr.setChildAge(jobj.getString("ChildAge"));
									}
									
									RoomGuests.add(sr);
								}
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								 String checkin = inputData.get("CheckIn");
								 Date checkindate = sdf.parse(checkin);
								
								 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
								HotelSearchInput input=new HotelSearchInput();
								 input.setAuthentication(HOtelWebserverClient.getAuthentication());
								 input.setCheckIn(sdf2.format(checkindate));
								 input.setNights(inputData.get("Nights"));
								 input.setCityId(inputData.get("CityId"));
								 input.setCountryCode(inputData.get("CountryCode"));
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
								}else{
									returnData.put("status", "0");	
								}
								returnData.put("SearchOutput", output);
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			log.error("searchHotalapps ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}

	@Override
	public Map<String, Object> HOtelDetails(String request) {
		HotelDetailsOutput output=new HotelDetailsOutput();
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		Map<String, Object> param3 = new HashMap<String, Object>();
		try{
			JSONObject requestObj = new JSONObject(request);
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {
				JSONObject authJson = requestObj.getJSONObject("Authentication");
				if (authJson.isNull("LoginId") || authJson.isNull("Password")) {
					returnData.put("status", "0");
					returnData.put("message", "LoginId and Password Cannot be null.");
				} else {
					
						param1 = new HashMap<String, String>();
						param1.put("userId", authJson.get("LoginId").toString());
						param1.put("password", authJson.get("Password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param1);
						if (username != null) {
							if (requestObj.isNull("HOtelDetails")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid HOtelDetails Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("HOtelDetails"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								inputData.put("source", "APP");
								
								System.out.println(inputData);
								 output=hotelBusinessDao.searchHotalDetails(inputData,userDetails);
								if(output.getResponseStatus()==1){
									returnData.put("status", "1");
								}else{
									returnData.put("status", "0");	
								}
								returnData.put("HOtelDetailsOutput", output);
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			log.error("HOtelDetails ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}

	@Override
	public Map<String, Object> HOtelBlockRoom(String request) {
		RoomBlockOutput output=new RoomBlockOutput();
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		Map<String, Object> param3 = new HashMap<String, Object>();
		try{
			JSONObject requestObj = new JSONObject(request);
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {
				JSONObject authJson = requestObj.getJSONObject("Authentication");
				if (authJson.isNull("LoginId") || authJson.isNull("Password")) {
					returnData.put("status", "0");
					returnData.put("message", "LoginId and Password Cannot be null.");
				} else {
					
						param1 = new HashMap<String, String>();
						param1.put("userId", authJson.get("LoginId").toString());
						param1.put("password", authJson.get("Password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param1);
						if (username != null) {
							if (requestObj.isNull("BlockRoom")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BlockRoom Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("BlockRoom"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								inputData.put("source", "APP");
								
								RoomBlockInput input=new RoomBlockInput();
								input.setAuthentication(HOtelWebserverClient.getAuthentication());
								input.setTrackId(inputData.get("TrackId").toString());
								input.setResultIndex(inputData.get("ResultIndex").toString());
								input.setSearchid(inputData.get("Searchid").toString());
								input.setProviderid(inputData.get("Providerid").toString());
								input.setHotelId(inputData.get("HotelId").toString());
								input.setHotelName(inputData.get("HotelName").toString());
								input.setGuestNationality(inputData.get("GuestNationality").toString());
								input.setNoOfRooms(inputData.get("NoOfRooms").toString());
								input.setReferenceNo(inputData.get("ReferenceNo").toString());
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
								 Date date = sdf.parse(inputData.get("CheckIn").toString());
								input.setCheckIn(sdf2.format(date));
								input.setNights(inputData.get("Nights").toString());
								
								List<HotelRoomDetails> roomgobj=new ArrayList<>();
								String fileseg1=inputData.get("HotelRoomDetails");
								JSONArray BHotelRoomDetailsArray = new JSONArray(fileseg1); 
								for(int i=0;i<BHotelRoomDetailsArray.length();i++){
									JSONObject roomsgobj=(JSONObject) BHotelRoomDetailsArray.get(i);
									HotelRoomDetails HotelR=new HotelRoomDetails();
									HotelR.setRoomCode(roomsgobj.get("RoomCode").toString());
									HotelR.setRatePlanCode(roomsgobj.get("RatePlanCode").toString());
									HotelR.setRoomTypeCode(roomsgobj.get("RoomTypeCode").toString());
									HotelR.setRoomTypeName(roomsgobj.get("RoomTypeName").toString());
									JSONObject price=roomsgobj.getJSONObject("HotelRoomPrice");
									HotelRoomPrice hrp=new HotelRoomPrice(Double.parseDouble(price.get("RoomPrice").toString()),Double.parseDouble(price.get("Tax").toString()),Double.parseDouble(price.get("ExtraGuestCharge").toString()),Double.parseDouble(price.get("ChildCharge").toString()),Double.parseDouble(price.get("OtherCharges").toString()),Double.parseDouble(price.get("Discount").toString()),Double.parseDouble(price.get("ServiceTax").toString()),"INR");
								
									HotelR.setHotelRoomPrice(hrp);
									roomgobj.add(HotelR);
								}
								
								input.setHotelRoomDetails(roomgobj);
								output=HOtelWebserverClient.getroomblock(input);

								
								if(output.getResponseStatus()==1){
									returnData.put("status", "1");
								}else{
									returnData.put("status", "0");	
								}
								returnData.put("RoomBlockOutput", output);
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			log.error("HOtelBlockRoom ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}

	@Override
	public Map<String, Object> HOtelBookRoom(String request) {
		HotelBookResponse output=new HotelBookResponse();
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		Map<String, Object> param3 = new HashMap<String, Object>();
		try{
			JSONObject requestObj = new JSONObject(request);
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {
				JSONObject authJson = requestObj.getJSONObject("Authentication");
				if (authJson.isNull("LoginId") || authJson.isNull("Password")) {
					returnData.put("status", "0");
					returnData.put("message", "LoginId and Password Cannot be null.");
				} else {
					
						param1 = new HashMap<String, String>();
						param1.put("userId", authJson.get("LoginId").toString());
						param1.put("password", authJson.get("Password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param1);
						if (username != null) {
							if (requestObj.isNull("BookRoom")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BookRoom Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("BookRoom"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								inputData.put("source", "APP");
								BookRoomInput input=new BookRoomInput();
								input.setAuthentication(HOtelWebserverClient.getAuthentication());
								input.setAvailabilityType("AvailabilityType");
								input.setTrackId(inputData.get("TrackId").toString());
								input.setResultIndex(inputData.get("ResultIndex").toString());
								input.setSearchid(inputData.get("Searchid").toString());
								input.setProviderid(inputData.get("Providerid").toString());
								input.setHotelId(inputData.get("HotelId").toString());
								input.setHotelName(inputData.get("HotelName").toString());
								input.setGuestNationality(inputData.get("GuestNationality").toString());
								input.setReferenceNo(inputData.get("ReferenceNo").toString());
								input.setNoOfRooms(inputData.get("NoOfRooms").toString());
								
								List<HotelRoomDetails> roomgobj=new ArrayList<>();
								String fileseg1=inputData.get("HotelRoomDetails");
								JSONArray BHotelRoomDetailsArray = new JSONArray(fileseg1); 
								for(int i=0;i<BHotelRoomDetailsArray.length();i++){
									JSONObject roomsgobj=(JSONObject) BHotelRoomDetailsArray.get(i);
									HotelRoomDetails HotelR=new HotelRoomDetails();
									HotelR.setRoomCode(roomsgobj.get("RoomCode").toString());
									HotelR.setRatePlanCode(roomsgobj.get("RatePlanCode").toString());
									HotelR.setRoomTypeCode(roomsgobj.get("RoomTypeCode").toString());
									HotelR.setRoomTypeName(roomsgobj.get("RoomTypeName").toString());
									JSONObject price=roomsgobj.getJSONObject("HotelRoomPrice");
									HotelRoomPrice hrp=new HotelRoomPrice(Double.parseDouble(price.get("RoomPrice").toString()),Double.parseDouble(price.get("Tax").toString()),Double.parseDouble(price.get("ExtraGuestCharge").toString()),Double.parseDouble(price.get("ChildCharge").toString()),Double.parseDouble(price.get("OtherCharges").toString()),Double.parseDouble(price.get("Discount").toString()),Double.parseDouble(price.get("ServiceTax").toString()),price.get("CurrencyCode").toString());
								
									HotelR.setHotelRoomPrice(hrp);
									roomgobj.add(HotelR);
								}
								input.setHotelRoomDetails(roomgobj);
								
								List<HotelPassenger> passenobj=new ArrayList<>();
								String HotelPassengerseg1=inputData.get("HotelPassenger");
								JSONArray HotelPassengerArray = new JSONArray(HotelPassengerseg1);
								for(int i=0;i<HotelPassengerArray.length();i++){
									JSONObject passengerdt=(JSONObject) HotelPassengerArray.get(i);
									HotelPassenger HotelPassengerobj=new HotelPassenger();
									HotelPassengerobj.setRoomCode(passengerdt.get("RoomCode").toString());
									HotelPassengerobj.setTitle(passengerdt.get("Title").toString());
									HotelPassengerobj.setFirstName(passengerdt.get("FirstName").toString());
									HotelPassengerobj.setLastName(passengerdt.get("LastName").toString());
									HotelPassengerobj.setAge(passengerdt.get("Age").toString());
									HotelPassengerobj.setPaxType(Integer.parseInt(passengerdt.get("PaxType").toString()));
									HotelPassengerobj.setLeadPassenger(Boolean.valueOf(passengerdt.get("LeadPassenger").toString()));
									HotelPassengerobj.setEmail(passengerdt.get("Email").toString());
									HotelPassengerobj.setPhoneno(passengerdt.get("Phoneno").toString());
									passenobj.add(HotelPassengerobj);
								}
								input.setHotelPassenger(passenobj);
								
								 output=HOtelWebserverClient.getroombook(input);
								if(output.getResponseStatus()==1){
									System.out.println("output::::::::::::"+output);
									HotelDetail  HotelDetail=new HotelDetail(output.getHotelName(), output.getCity(), "-", "-", output.getAddress1(), "-", output.getStarRating(), "-", output.getBookingId());
									HotelDetailDao.insertHotelDetail(HotelDetail);
									String bookdt[]=output.getBookingDate().split(" ");
									int roomno=output.getBookingHotelRoomDetails().size();
									HotelBooking HotelBooking=new HotelBooking(output.getBookingId(),output.getInvoiceNumber() ,"BOOKED", roomno, output.getBookingHotelRoomDetails().get(0).getRoomTypeName(), output.getCheckIn(), output.getCheckOut(), bookdt[0], bookdt[1], userDetails.getUserId(), output.getHotelName(), output.getTrackId(), 5, output.getConfirmationNo(), output.getBookingRefNo(),"APP");
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
									returnData.put("Bookingoutput", output);
								}else{
									returnData.put("status", "0");	
								}
								returnData.put("HOtelDetailsOutput", output);
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			log.error("HOtelBlockRoom ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}
	
	
}
