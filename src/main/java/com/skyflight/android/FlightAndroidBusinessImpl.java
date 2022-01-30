package com.skyflight.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.User;
import com.recharge.utill.UtilityClass;
import com.skyflight.BookingCustomModel.AirBookResponse;
import com.skyflight.BookingCustomModel.BookingRequest;
import com.skyflight.BookingCustomModel.BookingSegmentDetail;
import com.skyflight.BookingCustomModel.Fare;
import com.skyflight.BookingCustomModel.PassengerDetails;
import com.skyflight.businessdao.FlightBookingImplDao;
import com.skyflight.businessdao.FlightSearchDao;
import com.skyflight.model.Airport_code;
import com.skyflight.model.FlightFare;
import com.skyflight.model.Markup;
import com.skyflight.searchCustomModel.AirSearchAvailability;
import com.skyflight.searchCustomModel.CalendarFareRes;
import com.skyflight.searchCustomModel.FlightSegments;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.servicedao.AirportDao;
import com.skyflight.servicedao.FlightBookingDao;
import com.skyflight.servicedao.FlightFareDao;
import com.skyflight.servicedao.MarkupDao;
import com.skyflight.taxCustomModel.AirTaxResponse;
import com.skyflight.taxCustomModel.FlightSegmentsTax;
import com.skyflight.taxCustomModel.TaxInput;
import com.skyflight.taxCustomModel.UpdatePriceInputRequest;
import com.skyflight.webserver.WebServerClient;
@Service("FlightBusinessDao")
public class FlightAndroidBusinessImpl implements FlightBusinessDao{
	private static final Logger logger_log = Logger.getLogger(FlightBusinessDao.class);
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired FlightSearchDao flightSearchDao;
	@Autowired FlightBookingImplDao flightBookingDao;
	@Autowired AirportDao AirportDao;
	@Autowired MarkupDao markupdao;
	@Autowired FlightFareDao flightFareDao;

	@Override
	public Map<String, Object> flightsearchAndroid(String request) {
		AirSearchAvailability output=new AirSearchAvailability();
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
							if (requestObj.isNull("SearchInput")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid SearchInput Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("SearchInput"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								inputData.put("sources", "APP");
								output=flightSearchDao.searchFlight(inputData, userDetails);
								 if(output.getResponseStatus()!=0){
									    returnData.put("status", output.getResponseStatus());	
										returnData.put("UserTrackId",output.getTrackId());
										returnData.put("minval", output.getMinvalue());	
										returnData.put("maxval",output.getMaxvalue());
										returnData.put("report",output.getSerachResults());
										returnData.put("requestDetails",inputData);
										returnData.put("type",inputData.get("type"));
											
									    }else{
									     returnData.put("status",output.getResponseStatus());
									     returnData.put("message",output.getErrormessage());
									    }
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			logger_log.error("flightsearchAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}

	@Override
	public Map<String, Object> flightairport(String request) {
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
					List<Airport_code> list=AirportDao.getAllAirport_code();
					returnData.put("airport", list);
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
	public Map<String, Object> flightTaxAndroid(String request) {
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
							if (requestObj.isNull("TaxInput")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid TaxInput Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("TaxInput"));
								System.out.println(inputData);
								User userDetails = globalCommandCenter.getUserByUserId(username);

								Map<String,Object> customreq=new HashMap<String, Object>();
								customreq.put("type", inputData.get("TripType"));
								customreq.put("isDomestic", Boolean.valueOf(inputData.get("isDomestic")));
								List<TaxInput> TaxInput=new ArrayList<TaxInput>();
								String taxst=inputData.get("TaxInputs");
								JSONArray taxjsonArray = new JSONArray(taxst); 
								for(int k=0;k<taxjsonArray.length();k++){
									JSONObject jod=taxjsonArray.getJSONObject(k);
									
									TaxInput  tx=new TaxInput();
									tx.setBaseFare(jod.getString("BaseFare"));
									JSONArray flisegarray=jod.getJSONArray("FlightSegments");
									List<FlightSegmentsTax> FlightSegments=new ArrayList<>();
									for(int i=0;i<flisegarray.length();i++){
										JSONObject jodseg=flisegarray.getJSONObject(i);
										FlightSegmentsTax fst=new FlightSegmentsTax();
										fst.setResultIndex(jodseg.getString("ResultIndex"));
										fst.setAirlineCode(jodseg.getString("AirlineCode"));
										fst.setClassCode(jodseg.getString("ClassCode"));
										fst.setFlightId(jodseg.getString("FlightId"));
										fst.setTripIndicator(jodseg.getString("TripIndicator"));
										fst.setSegmentIndicator(jodseg.getString("SegmentIndicator"));
										fst.setSupplierId(jodseg.getString("SupplierId"));
										
										customreq.put("trip"+jodseg.getString("TripIndicator").toString(), jodseg.getString("AirlineCode").toString());
										FlightSegments.add(fst);
									}
									tx.setFlightSegments(FlightSegments);
									TaxInput.add(tx);
								}
								
								UpdatePriceInputRequest input=new UpdatePriceInputRequest(WebServerClient.getAuthentication(),inputData.get("UserTrackId").toString(),inputData.get("TripType"),Boolean.valueOf(inputData.get("isDomestic")),TaxInput);
							
								 Map<String, Object> param = new HashMap<String, Object>();
									param.put("username","admin");
									List<Markup> adminmarkupllist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
									param = new HashMap<String, Object>();
									param.put("username",userDetails.getUserName());
									List<Markup> usermarkuplist = markupdao.getMarkupByNamedQuery("getMarkupByUsername",param);
								
								AirTaxResponse output=WebServerClient.getFlightTax(input,adminmarkupllist,usermarkuplist,customreq,Integer.parseInt(inputData.get("totalpassenger").toString()));
								 if(output.getResponseStatus()!=0){
									    returnData.put("status", output.getResponseStatus());	
										returnData.put("Taxoutput", output);
										 returnData.put("type",inputData.get("TripType"));
										
											
									    }else{
									     returnData.put("status",output.getResponseStatus());
									     returnData.put("message",output.getErrormessage());
									    }
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			logger_log.error("flightTaxAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}
	
	
	
	

	@Override
	public Map<String, Object> flightCalenderSearchAndroid(String request) {
		CalendarFareRes output=new CalendarFareRes();
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
							if (requestObj.isNull("CalenderSearch")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid CalenderSearch Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("CalenderSearch"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								//output=flightSearchDao.searchFlight(inputData, userDetails);
								inputData.put("sources", "APP");
								 output = flightSearchDao.fetchcalenderfare(inputData,userDetails);
								 if(output.getResponseStatus()!=0){
									    returnData.put("status", output.getResponseStatus());	
										returnData.put("UserTrackId",output.getTrackId());
										returnData.put("CalendarFareResult", output.getCalendarFareResults());
										returnData.put("type",inputData.get("type"));
											
									    }else{
										     returnData.put("status",output.getResponseStatus());
										     returnData.put("message","OOPS!! No Data Found");
										    }
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			logger_log.error("flightCalenderSearchAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}

	
	@Override
	public Map<String, Object> flightBookTicketAndroid(String request) {
		AirSearchAvailability output=new AirSearchAvailability();
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
							if (requestObj.isNull("BookTicket")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BookTicket Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("BookTicket"));
								System.out.println(inputData);
								User userDetails = globalCommandCenter.getUserByUserId(username);
								BookingRequest input=new BookingRequest();
								input.setAuthentication(WebServerClient.getAuthentication());
								input.setTrackId(inputData.get("TrackId").toString());
								input.setTripType(inputData.get("TripType").toString());
								
								input.setDomestic(Boolean.valueOf(inputData.get("isDomestic")));
								boolean isDomestic=Boolean.valueOf(inputData.get("isDomestic"));
								//---------paqssenger----------------
								 List<PassengerDetails> listpassen=new ArrayList<PassengerDetails>();
								 String fileseg=inputData.get("PassengerDetails");
									JSONArray PassengerDetailsjsonArray = new JSONArray(fileseg); 
									System.out.println(PassengerDetailsjsonArray);
									for(int i=0;i<PassengerDetailsjsonArray.length();i++){
										JSONObject passenobj=(JSONObject) PassengerDetailsjsonArray.get(i);
										PassengerDetails psn=new PassengerDetails();
										psn.setPassengerType(passenobj.getString("PassengerType"));
										//System.out.println(passenobj.getString("PassengerType"));
										psn.setTitle(passenobj.getString("Title"));
										psn.setIsLeadPax(Boolean.valueOf(passenobj.get("IsLeadPax").toString()));
										psn.setFirstName(passenobj.getString("FirstName"));
										psn.setLastName(passenobj.getString("LastName"));
										psn.setGender(passenobj.getString("Gender"));
										psn.setEmail(passenobj.getString("Email"));
										psn.setCity(passenobj.getString("City"));
										psn.setMobile(passenobj.getString("Mobile"));
										if(passenobj.has("PaxNumber")){
										//System.out.println(passenobj.get("PaxNumber"));
										psn.setPaxNumber(passenobj.get("PaxNumber").toString());
										}
										if(!isDomestic){
											psn.setPassportNo(passenobj.get("PassportNo").toString());	
										}
										psn.setDateofBirth(passenobj.getString("DateofBirth"));
										listpassen.add(psn);
									}
									input.setPassengerDetails(listpassen);
									 List<BookingSegmentDetail> listbookseg=new ArrayList<BookingSegmentDetail>();
									 String fileseg1=inputData.get("BookingSegmentDetail");
										JSONArray BookingSegmentDetailjsonArray = new JSONArray(fileseg1); 
										for(int i=0;i<BookingSegmentDetailjsonArray.length();i++){
											JSONObject BookingSegmentobj=(JSONObject) BookingSegmentDetailjsonArray.get(i);
											BookingSegmentDetail bsg=new BookingSegmentDetail();
											bsg.setIsLCC(Boolean.parseBoolean(BookingSegmentobj.get("IsLCC").toString()));
											bsg.setResultIndex(BookingSegmentobj.getString("ResultIndex"));
											bsg.setTripIndicator(BookingSegmentobj.getString("TripIndicator"));	
											bsg.setSegmentIndicator(BookingSegmentobj.getString("SegmentIndicator"));
											bsg.setAirlineCode(BookingSegmentobj.getString("AirlineCode"));
											bsg.setOrigin(BookingSegmentobj.getString("Origin"));
											bsg.setDestination(BookingSegmentobj.getString("Destination"));
											bsg.setFlightNumber(BookingSegmentobj.getString("FlightNumber"));
											bsg.setClassCode(BookingSegmentobj.getString("ClassCode"));
											bsg.setFlightId(BookingSegmentobj.getString("FlightId"));
											bsg.setSupplierId(BookingSegmentobj.getString("SupplierId"));
											
											listbookseg.add(bsg);
										
										}
										input.setBookingSegmentDetail(listbookseg);
										 double TotalGrossAmount =0;
										List<Fare> listFare=new ArrayList<Fare>();
										String filesegFare=inputData.get("Fare");
										JSONArray FarejsonArray = new JSONArray(filesegFare); 	
										for(int i=0;i<FarejsonArray.length();i++){
											JSONObject Fareobj=(JSONObject) FarejsonArray.get(i);
											Fare Fare=new Fare();
											Fare.setTripIndicator(Fareobj.getString("TripIndicator"));
											Fare.setPassengerType(Fareobj.getString("PassengerType"));
											Fare.setBaseFare(Fareobj.getDouble("BaseFare"));
											Fare.setGrossAmount(Fareobj.getDouble("GrossAmount"));
											TotalGrossAmount=TotalGrossAmount+Double.parseDouble(Fareobj.get("GrossAmount").toString());
											Fare.setTax(Fareobj.getDouble("Tax"));
											Fare.setTransactionFee(Fareobj.getDouble("TransactionFee"));
											Fare.setYQTax(Fareobj.getDouble("YQTax"));
											Fare.setAdditionalTxnFeeOfrd(Fareobj.getDouble("AdditionalTxnFeeOfrd"));
											Fare.setAdditionalTxnFeePub(Fareobj.getDouble("AdditionalTxnFeePub"));
											Fare.setAirTransFee(Fareobj.getDouble("AirTransFee"));
											Fare.setOtherCharges(Fareobj.getDouble("OtherCharges"));
											
											listFare.add(Fare);
										}
										input.setFare(listFare);
										double total=TotalGrossAmount+Double.parseDouble(inputData.get("adminmarkup").toString())-Double.parseDouble(inputData.get("portalcommission").toString());
										returnData=flightBookingDao.Flightbookrequestandroid(userDetails,input,total);
										if(returnData.get("status").equals("1")){
											//FlightFare FlightFare=new FlightFare(output.getTrackId(), TotalGrossAmount+mealamount+baggageamount, Double.parseDouble(Taxoutput.get("usermarkup").toString()), Double.parseDouble(Taxoutput.get("adminmarkup").toString()));

											FlightFare FlightFare=new FlightFare(output.getTrackId(), TotalGrossAmount, Double.parseDouble(inputData.get("usermarkup").toString()), Double.parseDouble(inputData.get("adminmarkup").toString()),Double.parseDouble(inputData.get("portalcommission").toString()),0.0,0.0);
											flightFareDao.insertFlightFare(FlightFare);
										}
								
							}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
		}catch(Exception e){
			logger_log.error("flightBookTicketAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "OPPS!Somthing went wrong!");
		}
		
		return returnData;
	}

	@Override
	public Map<String, Object> flightBookTicketReportAndroid(String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		try{System.out.println("request::::"+request);
			JSONObject requestObj = new JSONObject(request);
			
			if (requestObj.isNull("Authentication")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty Authentication Passed.");
			} else {System.out.println(":::::::::::");
				System.out.println("request::::"+requestObj.getJSONObject("Authentication"));
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
							if (requestObj.isNull("BookTicketReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BookTicketReport Object");
							} else {
								System.out.println("BookTicketReport::"+requestObj.getJSONObject("BookTicketReport"));
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("BookTicketReport"));
								System.out.println("inputData::"+inputData);
								User userDetails = globalCommandCenter.getUserByUserId(username);
								returnData=flightBookingDao.viewBooikngReport(userDetails,inputData);
					}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
			
		}catch(Exception e){
			logger_log.error("flightBookTicketReportAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "OPPS!Somthing went wrong");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> flightTicketCancelAndroid(String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();
		try{System.out.println(request);
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
							if (requestObj.isNull("TicketCancel")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid TicketCancel Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestObj.getJSONObject("TicketCancel"));
								System.out.println(inputData);
								User userDetails = globalCommandCenter.getUserByUserId(username);
								returnData=flightBookingDao.cancelTicketapp(inputData,userDetails);
					}
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					
					
				}
				
				
			}
			
		}catch(Exception e){
			logger_log.error("flightTicketCancelAndroid ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "OPPS!Somthing went wrong");
		}
		return null;
	}


}
