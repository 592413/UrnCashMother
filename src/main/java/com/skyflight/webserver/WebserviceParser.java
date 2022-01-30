package com.skyflight.webserver;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonArray;
import com.recharge.businessdao.CommissionService;
import com.recharge.model.PackageWiseChargeComm;
import com.skyflight.BookingCustomModel.AdditionalSSRDetails;
import com.skyflight.BookingCustomModel.AirBookResponse;
import com.skyflight.BookingCustomModel.BookedSegments;
import com.skyflight.BookingCustomModel.CancelRequest;
import com.skyflight.BookingCustomModel.CancelResponse;
import com.skyflight.BookingCustomModel.CancelResponsedetails;
import com.skyflight.BookingCustomModel.CustomerDetail;
import com.skyflight.BookingCustomModel.Passenger;
import com.skyflight.BookingCustomModel.TaxDetails;
import com.skyflight.BookingCustomModel.TicketDetails;
import com.skyflight.MealBaggagesCustomModel.Baggages;
import com.skyflight.MealBaggagesCustomModel.Meals;
import com.skyflight.MealBaggagesCustomModel.SSRResponse;
import com.skyflight.MealBaggagesCustomModel.SSRSegments;
import com.skyflight.SeatCustomModel.Seat;
import com.skyflight.SeatCustomModel.SeatFlightSegments;
import com.skyflight.SeatCustomModel.SeatLayoutDetail;
import com.skyflight.SeatCustomModel.SeatResponse;
import com.skyflight.model.Airline;
import com.skyflight.model.CancellationDetail;
import com.skyflight.model.Markup;
import com.skyflight.searchCustomModel.AirSearchAvailability;
import com.skyflight.searchCustomModel.CalendarFareRes;
import com.skyflight.searchCustomModel.CalendarFareResults;
import com.skyflight.searchCustomModel.Fare;
import com.skyflight.searchCustomModel.FareBreakdown;
import com.skyflight.searchCustomModel.FlightSegments;
import com.skyflight.searchCustomModel.SearchInput;
import com.skyflight.searchCustomModel.SearchInputRequest;
import com.skyflight.searchCustomModel.SerachResults;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.taxCustomModel.AirTaxResponse;
import com.skyflight.taxCustomModel.FareBreakdownTax;
import com.skyflight.taxCustomModel.FareRuledetail;

public class WebserviceParser {
	
	private static final Logger log = Logger.getLogger(WebserviceParser.class);

	
	
	
	public static AirSearchAvailability getFlightAvalibilityParser(String response, int totalpassenger,List<Markup> adminmarkupllist,List<Markup> usermarkuplist,List<Airline> airlinelist,List<PackageWiseChargeComm> pck) {
		
		AirSearchAvailability output=new AirSearchAvailability();
		try{
			double minval=0;
			double maxvalue=0;
			int trip=0;
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONObject jsonObj=jsonObj2.getJSONObject("AirSearchAvailability");
		output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
		if(jsonObj.getInt("ResponseStatus")==1){
			
			output.setTrackId(jsonObj.getString("TrackId"));
			output.setOrigin(jsonObj.getString("Origin"));
			output.setDestination(jsonObj.getString("Destination"));
			output.setDomestic(jsonObj.getBoolean("isDomestic"));
			List<SerachResults> searchlist=new ArrayList<SerachResults>();
			JSONArray SerachResultsJSONArray = jsonObj.getJSONArray("SerachResults");
			SerachResults SerachResults=null;
			
			for (int i = 0; i <SerachResultsJSONArray.length(); i++) {
				SerachResults=new SerachResults();
				JSONObject AvailableSegmentsobj = SerachResultsJSONArray.getJSONObject(i);
				SerachResults.setResultIndex(AvailableSegmentsobj.getString("ResultIndex"));
				SerachResults.setAirlineId(AvailableSegmentsobj.getString("AirlineId"));
				//pckret = commissionService.
				System.out.println("airlinelist.size():"+airlinelist.size());
				int id=0;
				
				for(Airline mr : airlinelist){
					
						if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
							SerachResults.setAirlineName(mr.getAirline_name());
							id=mr.getId();
						}	
				}	
				System.out.println(id);
				
				SerachResults.setIsLCC(AvailableSegmentsobj.getBoolean("IsLCC"));
				JSONObject faresobj = AvailableSegmentsobj.getJSONObject("Fare");
				Fare outfair=new Fare();
				
				outfair.setBaseFare(faresobj.getInt("BaseFare"));
				outfair.setCurrency(faresobj.getString("Currency"));
				outfair.setCommission(faresobj.getInt("Commission"));
				outfair.setTax(faresobj.getInt("Tax"));
				outfair.setYQTax(faresobj.getInt("YQTax"));
				outfair.setOtherCharges(faresobj.getInt("OtherCharges"));
				outfair.setServiceCharge(faresobj.getInt("ServiceCharge"));
				outfair.setGrossAmount(faresobj.getInt("GrossAmount"));
				double adminmarkup = 0.0;
				double usermarkup = 0.0;
				System.out.println("adminmarkupllist.size():"+adminmarkupllist.size());
				if(adminmarkupllist.size()>0){
					
					for(Markup mr : adminmarkupllist){
						if(jsonObj.getBoolean("isDomestic")){
						if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
							if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								adminmarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
							}else{
								adminmarkup =mr.getMarkup_value();
							}
						}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									adminmarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									adminmarkup =mr.getMarkup_value();
								}
							}
						}
					}
					
				}
				adminmarkup=adminmarkup*totalpassenger;
				System.out.println("adminmarkupllist.size():"+usermarkuplist.size());
				if(usermarkuplist.size()>0){
					for(Markup mr : usermarkuplist){
						if(jsonObj.getBoolean("isDomestic")){
						if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
							if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								usermarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
							}else{
								usermarkup =mr.getMarkup_value();
							}
						}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									usermarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									usermarkup =mr.getMarkup_value();
								}
							}
						}
					}
				}
				usermarkup=usermarkup*totalpassenger;
				double totalamount=faresobj.getInt("GrossAmount")+adminmarkup+usermarkup;
				double charge=0.0;
				double portalcomm=0.0;
				System.out.println("pck::"+pck);
				for(PackageWiseChargeComm mrk : pck){
					System.out.println("mrk.getOperator_id()::"+mrk.getOperator_id());
					if(mrk.getOperator_id()==id){
						System.out.println("pck::"+id);
						if (mrk.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
							portalcomm=((faresobj.getInt("GrossAmount")+adminmarkup)*mrk.getComm())/100;
						}else{
							portalcomm=mrk.getComm();
						}
						if (mrk.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
							charge=((faresobj.getInt("GrossAmount")+adminmarkup)*mrk.getCharge())/100;
						}else{
							charge=mrk.getCharge();
						}
						
					}
				}
				charge=charge*totalpassenger;
				portalcomm=portalcomm*portalcomm;
				outfair.setPortalcharge(charge);
				outfair.setPortalcommission(portalcomm);
				
				DecimalFormat df = new DecimalFormat("0.00");
				outfair.setTotalAmount(Double.parseDouble(df.format(totalamount)));
				//double totalmarkup=(adminmarkup+usermarkup)/totalpassenger;
				outfair.setMarkUp(Double.parseDouble(df.format(adminmarkup)));
				outfair.setUSerMarkUp(Double.parseDouble(df.format(usermarkup)));
				SerachResults.setFare(outfair);
				//-------------
				if(i==0){
					minval=Double.parseDouble(df.format(totalamount));
				}
				if(minval>Double.parseDouble(df.format(totalamount))){
					minval=Double.parseDouble(df.format(totalamount));
				}
				if(maxvalue<Double.parseDouble(df.format(totalamount))){
					maxvalue=Double.parseDouble(df.format(totalamount));
				}
				//============
				
				JSONArray FareBreakdownJSONArray = AvailableSegmentsobj.getJSONArray("FareBreakdown");
				//System.out.println("FareBreakdownJSONArray::"+FareBreakdownJSONArray.length());
				FareBreakdown FareBreakdown=null;
				List<FareBreakdown> ongoingflightSegmentsList = new ArrayList<FareBreakdown>();
				for (int j = 0; j < FareBreakdownJSONArray.length(); j++) {
					JSONObject FareBreakdownobj = FareBreakdownJSONArray.getJSONObject(j);
					FareBreakdown=new FareBreakdown();
					
					FareBreakdown.setTripIndicator(Integer.toString(FareBreakdownobj.getInt("TripIndicator")));
					if(j==0){
						if(FareBreakdownobj.getInt("TripIndicator")==2){
							if(trip==0){
								trip=2;
								SerachResults.setTripindi(2);
							}else{
								SerachResults.setTripindi(0);
							}
						}else{
							SerachResults.setTripindi(0);
						}	
					}
					
					if(FareBreakdownobj.has("PassengerCount")){
					FareBreakdown.setPassengerCount(FareBreakdownobj.getString("PassengerCount"));
				}
					FareBreakdown.setPassengerType(FareBreakdownobj.getString("PassengerType"));
					FareBreakdown.setBaseFare(FareBreakdownobj.getInt("BaseFare"));
					FareBreakdown.setGrossAmount(FareBreakdownobj.getInt("GrossAmount"));
					FareBreakdown.setCommission(FareBreakdownobj.getInt("Commission"));
					FareBreakdown.setYQ(FareBreakdownobj.getInt("YQ"));
					FareBreakdown.setTax(FareBreakdownobj.getInt("Tax"));
					FareBreakdown.setAdditionalTxnFeeOfrd(FareBreakdownobj.getInt("AdditionalTxnFeeOfrd"));
					FareBreakdown.setAdditionalTxnFeePub(FareBreakdownobj.getInt("AdditionalTxnFeePub"));
					FareBreakdown.setTransactionFee(FareBreakdownobj.getInt("TransactionFee"));
					
					ongoingflightSegmentsList.add(FareBreakdown);
				}
				
				
				
				
				SerachResults.setFareBreakdown(ongoingflightSegmentsList);
				
				List<FlightSegments> FlightSegmentslist=new ArrayList<FlightSegments>();
				FlightSegments FlightSegments=null;
				JSONArray FlightSegmentsJSONArray = AvailableSegmentsobj.getJSONArray("FlightSegments");
				String startdate="";
					String endDate="";
					String startdate1="";
					String endDate1="";
					if(jsonObj.getBoolean("isDomestic")==true){
						SerachResults.setStops(FlightSegmentsJSONArray.length()-1);
					}else{
						SerachResults.setStops((FlightSegmentsJSONArray.length()-1)/2);
						SerachResults.setInternationalroundstops(FlightSegmentsJSONArray.length()-1);
					}
					
					
				for (int j = 0; j < FlightSegmentsJSONArray.length(); j++) {
					JSONObject FlightSegmentsobj = FlightSegmentsJSONArray.getJSONObject(j);
					FlightSegments=new FlightSegments();
					FlightSegments.setTripIndicator(Integer.toString(FlightSegmentsobj.getInt("TripIndicator")));
					FlightSegments.setSegmentIndicator(Integer.toString(FlightSegmentsobj.getInt("SegmentIndicator")));
					FlightSegments.setFlightId(FlightSegmentsobj.getString("FlightId"));
					FlightSegments.setAirCraftType(FlightSegmentsobj.getString("AirCraftType"));
					FlightSegments.setAirlineCode(FlightSegmentsobj.getString("AirlineCode"));
					FlightSegments.setFlightNumber(FlightSegmentsobj.getString("FlightNumber"));
					FlightSegments.setOrigin(FlightSegmentsobj.getString("Origin"));
					FlightSegments.setDestination(FlightSegmentsobj.getString("Destination"));
					FlightSegments.setDepartureDateTime(FlightSegmentsobj.getString("DepartureDateTime"));
					
					String datetime[]=FlightSegmentsobj.getString("DepartureDateTime").split(" ");
					
					     Date dt=new SimpleDateFormat("yyyy-MM-dd").parse(datetime[0].toString());
					     DateFormat destDf = new SimpleDateFormat("EEE-MMM d,yyyy");
					     
					FlightSegments.setDepartureDate(destDf.format(dt));
					FlightSegments.setDepartureTime(datetime[1]);
					FlightSegments.setArrivalDateTime(FlightSegmentsobj.getString("ArrivalDateTime"));
					String ardatetime[]=FlightSegmentsobj.getString("ArrivalDateTime").split(" ");
					 Date dt1= new SimpleDateFormat("yyyy-MM-dd").parse(ardatetime[0].toString());
					 DateFormat destDf1 = new SimpleDateFormat("EEE-MMM d,yyyy");
				      
					FlightSegments.setArrivalDate(destDf1.format(dt1));
					FlightSegments.setArrivalTime(ardatetime[1]);
					FlightSegments.setDuration(FlightSegmentsobj.getString("Duration"));
					FlightSegments.setFareClass(FlightSegmentsobj.getString("FareClass"));
					FlightSegments.setSupplierId(FlightSegmentsobj.getString("SupplierId"));
					FlightSegmentslist.add(FlightSegments);
					if(jsonObj.getBoolean("isDomestic")==true){
						if(j==0){
							startdate=FlightSegmentsobj.getString("DepartureDateTime");
						}
						if(j==FlightSegmentsJSONArray.length()-1){
							endDate=FlightSegmentsobj.getString("ArrivalDateTime");
						}
						}else{
							if(j==0){
								startdate=FlightSegmentsobj.getString("DepartureDateTime");
							}
							if(j==(FlightSegmentsJSONArray.length()-1)/2){
								endDate=FlightSegmentsobj.getString("ArrivalDateTime");
							}
							if(j==((FlightSegmentsJSONArray.length()-1)/2)+1){
								startdate1=FlightSegmentsobj.getString("DepartureDateTime");
							}
							if(j==(FlightSegmentsJSONArray.length()-1)){
								endDate1=FlightSegmentsobj.getString("ArrivalDateTime");
							}
						}
				}
				if(jsonObj.getBoolean("isDomestic")==true){
					SerachResults.setTotalduration(UtillNumber.DifferenceDuration(startdate,endDate));//count total duration 
				}else{
					SerachResults.setTotalduration(UtillNumber.DifferenceDuration(startdate,endDate));
					SerachResults.setTotaldurationinternational(UtillNumber.DifferenceDuration(startdate1,endDate1));
				}
				//SerachResults.setTotalduration(UtillNumber.DifferenceDuration(startdate,endDate));//count total duration 
				SerachResults.setFlightSegments(FlightSegmentslist);
				searchlist.add(SerachResults);
			}
			output.setSerachResults(searchlist);
			output.setMinvalue(minval);
			output.setMaxvalue(maxvalue);
		}else{
			JSONObject jsonObj1 = jsonObj.getJSONObject("ErrorDetails");
			if(jsonObj1.get("ErrorCode") instanceof String){
				output.setErrorcode(jsonObj1.getString("ErrorCode"));
			}else{
				output.setErrorcode(Integer.toString(jsonObj1.getInt("ErrorCode")));
			}
			
			output.setErrormessage(jsonObj1.getString("ErrorMessage"));
		}
		}catch(Exception e){
			log.warn("getFlightAvalibilityParser:::"+e);
		}
		return output;
	}
	
	
	public static AirSearchAvailability getFlightAvalibilityParserandroid(String response, int totalpassenger,List<Markup> adminmarkupllist,List<Markup> usermarkuplist,List<Airline> airlinelist,List<PackageWiseChargeComm> pck) {
		AirSearchAvailability output=new AirSearchAvailability();
		try{
			double minval=0;
			double maxvalue=0;
			int trip=0;
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONObject jsonObj=jsonObj2.getJSONObject("AirSearchAvailability");
		output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
		if(jsonObj.getInt("ResponseStatus")==1){
			
			output.setTrackId(jsonObj.getString("TrackId"));
			output.setOrigin(jsonObj.getString("Origin"));
			output.setDestination(jsonObj.getString("Destination"));
			output.setDomestic(jsonObj.getBoolean("isDomestic"));
			List<SerachResults> searchlist=new ArrayList<SerachResults>();
			JSONArray SerachResultsJSONArray = jsonObj.getJSONArray("SerachResults");
			SerachResults SerachResults=null;
			
			for (int i = 0; i <SerachResultsJSONArray.length(); i++) {
				SerachResults=new SerachResults();
				JSONObject AvailableSegmentsobj = SerachResultsJSONArray.getJSONObject(i);
				SerachResults.setResultIndex(AvailableSegmentsobj.getString("ResultIndex"));
				SerachResults.setAirlineId(AvailableSegmentsobj.getString("AirlineId"));
				int id=0;
				for(Airline mr : airlinelist){
					if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
						SerachResults.setAirlineName(mr.getAirline_name());
						id=mr.getId();
					}
				}
				
				
				SerachResults.setIsLCC(AvailableSegmentsobj.getBoolean("IsLCC"));
				JSONObject faresobj = AvailableSegmentsobj.getJSONObject("Fare");
				Fare outfair=new Fare();
				
				outfair.setBaseFare(faresobj.getInt("BaseFare"));
				outfair.setCurrency(faresobj.getString("Currency"));
				outfair.setCommission(faresobj.getInt("Commission"));
				outfair.setTax(faresobj.getInt("Tax"));
				outfair.setYQTax(faresobj.getInt("YQTax"));
				outfair.setOtherCharges(faresobj.getInt("OtherCharges"));
				outfair.setServiceCharge(faresobj.getInt("ServiceCharge"));
				outfair.setGrossAmount(faresobj.getInt("GrossAmount"));
				double adminmarkup = 0.0;
				double usermarkup = 0.0;
				if(adminmarkupllist.size()>0){
					for(Markup mr : adminmarkupllist){
						if(jsonObj.getBoolean("isDomestic")){
						if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
							if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								adminmarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
							}else{
								adminmarkup =mr.getMarkup_value();
							}
						}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									adminmarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									adminmarkup =mr.getMarkup_value();
								}
							}
						}
					}
				}
				adminmarkup=adminmarkup*totalpassenger;
				if(usermarkuplist.size()>0){
					for(Markup mr : usermarkuplist){
						if(jsonObj.getBoolean("isDomestic")){
						if(mr.getAirline_code().equals(AvailableSegmentsobj.getString("AirlineId"))){
							if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								usermarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
							}else{
								usermarkup =mr.getMarkup_value();
							}
						}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									usermarkup = faresobj.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									usermarkup =mr.getMarkup_value();
								}
							}
						}
					}
				}
				usermarkup=usermarkup*totalpassenger;
				double totalamount=faresobj.getInt("GrossAmount")+adminmarkup+usermarkup;
				
				double charge=0.0;
				double portalcomm=0.0;
				System.out.println("pck::"+pck);
				for(PackageWiseChargeComm mrk : pck){
					System.out.println("mrk.getOperator_id()::"+mrk.getOperator_id());
					if(mrk.getOperator_id()==id){
						System.out.println("pck::"+id);
						if (mrk.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
							portalcomm=((faresobj.getInt("GrossAmount")+adminmarkup)*mrk.getComm())/100;
						}else{
							portalcomm=mrk.getComm();
						}
						if (mrk.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
							charge=((faresobj.getInt("GrossAmount")+adminmarkup)*mrk.getCharge())/100;
						}else{
							charge=mrk.getCharge();
						}
						
					}
				}
				outfair.setPortalcharge(charge);
				outfair.setPortalcommission(portalcomm);
				
				DecimalFormat df = new DecimalFormat("0.00");
				outfair.setTotalAmount(Double.parseDouble(df.format(totalamount)));
				//double totalmarkup=(adminmarkup+usermarkup)/totalpassenger;
				outfair.setMarkUp(Double.parseDouble(df.format(adminmarkup)));
				outfair.setUSerMarkUp(Double.parseDouble(df.format(usermarkup)));
				SerachResults.setFare(outfair);
				//-------------
				if(i==0){
					minval=Double.parseDouble(df.format(totalamount));
				}
				if(minval>Double.parseDouble(df.format(totalamount))){
					minval=Double.parseDouble(df.format(totalamount));
				}
				if(maxvalue<Double.parseDouble(df.format(totalamount))){
					maxvalue=Double.parseDouble(df.format(totalamount));
				}
				//============
				
				JSONArray FareBreakdownJSONArray = AvailableSegmentsobj.getJSONArray("FareBreakdown");
				//System.out.println("FareBreakdownJSONArray::"+FareBreakdownJSONArray.length());
				FareBreakdown FareBreakdown=null;
				List<FareBreakdown> ongoingflightSegmentsList = new ArrayList<FareBreakdown>();
				for (int j = 0; j < FareBreakdownJSONArray.length(); j++) {
					JSONObject FareBreakdownobj = FareBreakdownJSONArray.getJSONObject(j);
					FareBreakdown=new FareBreakdown();
					
					FareBreakdown.setTripIndicator(Integer.toString(FareBreakdownobj.getInt("TripIndicator")));
					if(j==0){
						if(FareBreakdownobj.getInt("TripIndicator")==2){
						
								SerachResults.setTripindi(2);
							
						}else{
							SerachResults.setTripindi(0);
						}	
					}
					
					if(FareBreakdownobj.has("PassengerCount")){
					FareBreakdown.setPassengerCount(FareBreakdownobj.getString("PassengerCount"));
				}
					FareBreakdown.setPassengerType(FareBreakdownobj.getString("PassengerType"));
					FareBreakdown.setBaseFare(FareBreakdownobj.getInt("BaseFare"));
					FareBreakdown.setGrossAmount(FareBreakdownobj.getInt("GrossAmount"));
					FareBreakdown.setCommission(FareBreakdownobj.getInt("Commission"));
					FareBreakdown.setYQ(FareBreakdownobj.getInt("YQ"));
					FareBreakdown.setTax(FareBreakdownobj.getInt("Tax"));
					FareBreakdown.setAdditionalTxnFeeOfrd(FareBreakdownobj.getInt("AdditionalTxnFeeOfrd"));
					FareBreakdown.setAdditionalTxnFeePub(FareBreakdownobj.getInt("AdditionalTxnFeePub"));
					FareBreakdown.setTransactionFee(FareBreakdownobj.getInt("TransactionFee"));
					
					ongoingflightSegmentsList.add(FareBreakdown);
				}
				
				
				
				
				SerachResults.setFareBreakdown(ongoingflightSegmentsList);
				
				List<FlightSegments> FlightSegmentslist=new ArrayList<FlightSegments>();
				FlightSegments FlightSegments=null;
				JSONArray FlightSegmentsJSONArray = AvailableSegmentsobj.getJSONArray("FlightSegments");
				String startdate="";
					String endDate="";
					String startdate1="";
					String endDate1="";
					if(jsonObj.getBoolean("isDomestic")==true){
						SerachResults.setStops(FlightSegmentsJSONArray.length()-1);
					}else{
						SerachResults.setStops((FlightSegmentsJSONArray.length()-1)/2);
					}
					
				for (int j = 0; j < FlightSegmentsJSONArray.length(); j++) {
					JSONObject FlightSegmentsobj = FlightSegmentsJSONArray.getJSONObject(j);
					FlightSegments=new FlightSegments();
					FlightSegments.setTripIndicator(Integer.toString(FlightSegmentsobj.getInt("TripIndicator")));
					FlightSegments.setSegmentIndicator(Integer.toString(FlightSegmentsobj.getInt("SegmentIndicator")));
					FlightSegments.setFlightId(FlightSegmentsobj.getString("FlightId"));
					FlightSegments.setAirCraftType(FlightSegmentsobj.getString("AirCraftType"));
					FlightSegments.setAirlineCode(FlightSegmentsobj.getString("AirlineCode"));
					FlightSegments.setFlightNumber(FlightSegmentsobj.getString("FlightNumber"));
					FlightSegments.setOrigin(FlightSegmentsobj.getString("Origin"));
					FlightSegments.setDestination(FlightSegmentsobj.getString("Destination"));
					FlightSegments.setDepartureDateTime(FlightSegmentsobj.getString("DepartureDateTime"));
					
					String datetime[]=FlightSegmentsobj.getString("DepartureDateTime").split(" ");
					
					     Date dt=new SimpleDateFormat("yyyy-MM-dd").parse(datetime[0].toString());
					     DateFormat destDf = new SimpleDateFormat("EEE-MMM d,yyyy");
					     
					FlightSegments.setDepartureDate(destDf.format(dt));
					FlightSegments.setDepartureTime(datetime[1]);
					FlightSegments.setArrivalDateTime(FlightSegmentsobj.getString("ArrivalDateTime"));
					String ardatetime[]=FlightSegmentsobj.getString("ArrivalDateTime").split(" ");
					 Date dt1= new SimpleDateFormat("yyyy-MM-dd").parse(ardatetime[0].toString());
					 DateFormat destDf1 = new SimpleDateFormat("EEE-MMM d,yyyy");
				      
					FlightSegments.setArrivalDate(destDf1.format(dt1));
					FlightSegments.setArrivalTime(ardatetime[1]);
					FlightSegments.setDuration(FlightSegmentsobj.getString("Duration"));
					FlightSegments.setFareClass(FlightSegmentsobj.getString("FareClass"));
					FlightSegments.setSupplierId(FlightSegmentsobj.getString("SupplierId"));
					FlightSegmentslist.add(FlightSegments);
					
					if(jsonObj.getBoolean("isDomestic")==true){
					if(j==0){
						startdate=FlightSegmentsobj.getString("DepartureDateTime");
					}
					if(j==FlightSegmentsJSONArray.length()-1){
						endDate=FlightSegmentsobj.getString("ArrivalDateTime");
					}
					}else{
						if(j==0){
							startdate=FlightSegmentsobj.getString("DepartureDateTime");
						}
						if(j==(FlightSegmentsJSONArray.length()-1)/2){
							endDate=FlightSegmentsobj.getString("ArrivalDateTime");
						}
						if(j==((FlightSegmentsJSONArray.length()-1)/2)+1){
							startdate1=FlightSegmentsobj.getString("DepartureDateTime");
						}
						if(j==(FlightSegmentsJSONArray.length()-1)){
							endDate1=FlightSegmentsobj.getString("ArrivalDateTime");
						}
					}
				}
				if(jsonObj.getBoolean("isDomestic")==true){
					SerachResults.setTotalduration(UtillNumber.DifferenceDuration(startdate,endDate));//count total duration 
				}else{
					SerachResults.setTotalduration(UtillNumber.DifferenceDuration(startdate,endDate));
					SerachResults.setTotaldurationinternational(UtillNumber.DifferenceDuration(startdate1,endDate1));
				}
				
				SerachResults.setFlightSegments(FlightSegmentslist);
				searchlist.add(SerachResults);
			}
			output.setSerachResults(searchlist);
			output.setMinvalue(minval);
			output.setMaxvalue(maxvalue);
		}else{
			JSONObject jsonObj1 = jsonObj.getJSONObject("ErrorDetails");
			if(jsonObj1.get("ErrorCode") instanceof String){
				output.setErrorcode(jsonObj1.getString("ErrorCode"));
			}else{
				output.setErrorcode(Integer.toString(jsonObj1.getInt("ErrorCode")));
			}
			
			output.setErrormessage(jsonObj1.getString("ErrorMessage"));
		}
		}catch(Exception e){
			log.warn("getFlightAvalibilityParser:::"+e);
		}
		return output;
	}



	public static AirTaxResponse getFlightTaxParser(String response, List<Markup> adminmarkupllist,
			List<Markup> usermarkuplist,Map<String, Object> requestdt,int totalpassenger) {
		AirTaxResponse output=new AirTaxResponse();
		try{
			System.out.println("requestdt::"+requestdt);
			System.out.println("totalpassenger::"+totalpassenger);
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONObject jsonObj=jsonObj2.getJSONObject("AirTaxResponse");
		output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
		if(jsonObj.getInt("ResponseStatus")==1){
			output.setTrackId(jsonObj.getString("TrackId"));
			
			List<FareRuledetail> FareRuledetaillist=new ArrayList<FareRuledetail>();
			if(jsonObj.has("FareRuledetail")){
			JSONArray FareRuledetailJSONArray=jsonObj.getJSONArray("FareRuledetail");
			if(FareRuledetailJSONArray.length()>0){
				for(int j=0;j<FareRuledetailJSONArray.length();j++){
					JSONObject FareRuledetail=FareRuledetailJSONArray.getJSONObject(j);
					FareRuledetail fareru=new FareRuledetail();
					fareru.setAirline(FareRuledetail.getString("Airline"));
					fareru.setTripIndicator(FareRuledetail.getString("TripIndicator"));
					fareru.setSegmentIndicator(FareRuledetail.getString("SegmentIndicator"));
					fareru.setDestination(FareRuledetail.getString("Destination"));
					fareru.setFareBasisCode(FareRuledetail.getString("FareBasisCode"));
					fareru.setFareRule(FareRuledetail.getString("FareRule"));
					fareru.setOrigin(FareRuledetail.getString("Origin"));
					
					FareRuledetaillist.add(fareru);
				}
			}
		
			}
			output.setFareRuledetail(FareRuledetaillist);
		
			double totaladminmarkup=0.0;
			double totalusermarkup=0.0;
			double totaladult=0.0;
			double totalchild=0.0;
			double totalinfant=0.0; 
			double totaltax=0.0;
			double totalgross=0.0;
			double adminmarkup=0.0;
			double usermarkup=0.0;
			JSONArray FareBreakdownJSONArray=jsonObj.getJSONArray("FareBreakdown");
			List<FareBreakdownTax> FareBreakdownTaxlist=new ArrayList<FareBreakdownTax>();
			for(int i=0;i<FareBreakdownJSONArray.length();i++){
				JSONObject  FareBreakdown = FareBreakdownJSONArray.getJSONObject(i);
				FareBreakdownTax fTax=new FareBreakdownTax();
				fTax.setCurrency(FareBreakdown.getString("Currency"));
				fTax.setTripIndicator(FareBreakdown.getString("TripIndicator"));
				fTax.setPassengerType(FareBreakdown.getString("PassengerType"));
				fTax.setPassengerCount(FareBreakdown.getString("PassengerCount"));
				fTax.setBaseFare(FareBreakdown.getDouble("BaseFare"));
				fTax.setGrossAmount(FareBreakdown.getDouble("GrossAmount"));
				fTax.setCommission(FareBreakdown.getDouble("Commission"));
				fTax.setYQ(FareBreakdown.getDouble("YQ"));
				fTax.setTax(FareBreakdown.getDouble("Tax"));
				fTax.setAdditionalTxnFeeOfrd(FareBreakdown.getDouble("AdditionalTxnFeeOfrd"));
				fTax.setAdditionalTxnFeePub(FareBreakdown.getDouble("AdditionalTxnFeePub"));
				fTax.setTransactionFee(FareBreakdown.getDouble("TransactionFee"));
				FareBreakdownTaxlist.add(fTax);
				
				String airlinecd="";
				
				if(FareBreakdown.getString("TripIndicator").equals("1")){
					fTax.setPortalcomm(Double.parseDouble(requestdt.get("comm").toString()));	
					airlinecd=(String) requestdt.get("trip"+FareBreakdown.getString("TripIndicator"));
				}else{
					fTax.setPortalcomm(Double.parseDouble(requestdt.get("comm1").toString()));
					airlinecd=(String) requestdt.get("trip"+FareBreakdown.getString("TripIndicator"));
				}
				if(adminmarkupllist.size()>0){
					for(Markup mr : adminmarkupllist){
						if((boolean) requestdt.get("isDomestic")){
							if(mr.getAirline_code().equals(airlinecd)){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									adminmarkup = FareBreakdown.getDouble("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									adminmarkup =mr.getMarkup_value();
								}
							}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									adminmarkup = FareBreakdown.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									adminmarkup =mr.getMarkup_value();
								}
							}
						}
						
					}
					//totaladminmarkup=totaladminmarkup+(adminmarkup*totalpassenger);
					totaladminmarkup=totaladminmarkup+(adminmarkup*Integer.parseInt(FareBreakdown.getString("PassengerCount")));
				}
				if(usermarkuplist.size()>0){
					for(Markup mr : usermarkuplist){
						if((boolean) requestdt.get("isDomestic")){
						if(mr.getAirline_code().equals(airlinecd)){
							if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								usermarkup = FareBreakdown.getDouble("GrossAmount")*mr.getMarkup_value()/100;
							}else{
								usermarkup =mr.getMarkup_value();
							}
						}
						}else{
							if(mr.getAirline_code().equals("INT-ALL")){
								if(mr.getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
									usermarkup = FareBreakdown.getInt("GrossAmount")*mr.getMarkup_value()/100;
								}else{
									usermarkup =mr.getMarkup_value();
								}
							}
						}
					}
					//totalusermarkup=totalusermarkup+(usermarkup*totalpassenger);
					totalusermarkup=totalusermarkup+(usermarkup*Integer.parseInt(FareBreakdown.getString("PassengerCount")));
				}
				
				if(Integer.parseInt(FareBreakdown.getString("PassengerType"))==1){
					totaladult=totaladult+FareBreakdown.getDouble("BaseFare");
				}
				if(Integer.parseInt(FareBreakdown.getString("PassengerType"))==2){
					totalchild=totalchild+FareBreakdown.getDouble("BaseFare");		
								}
				if(Integer.parseInt(FareBreakdown.getString("PassengerType"))==3){
					totalinfant=totalinfant+FareBreakdown.getDouble("BaseFare");
				}
				totaltax=totaltax+FareBreakdown.getDouble("Tax");
				totalgross=totalgross+FareBreakdown.getDouble("GrossAmount");
			}
			output.setFareBreakdown(FareBreakdownTaxlist);
			output.setAdminmarkup(totaladminmarkup);
			output.setUsermarkup(totalusermarkup);
			output.setTotaladult(totaladult);
			output.setTotalchild(totalchild);
			output.setTotalinfant(totalinfant);
			output.setTotalgross(totalgross);
			output.setTotaltax(totaltax);
			
			
		}else{
			JSONObject jsonObj1 = jsonObj.getJSONObject("ErrorDetails");
			output.setErrorcode(Integer.toString(jsonObj1.getInt("ErrorCode")));
			output.setErrormessage(jsonObj1.getString("ErrorMessage"));
		}
		}catch(Exception e){
			log.warn("getFlightAvalibilityParser:::"+e);
		}
		return output;
	}


	public static SSRResponse getgetMealAndBaggageInfoParser(String response) {
		SSRResponse output= new SSRResponse();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("SSRResponse");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			output.setTrackId(jsonObj.getString("TrackId"));
			if(jsonObj.getInt("ResponseStatus")==1){
				List<SSRSegments> SSRSegments=new ArrayList<SSRSegments>();
				JSONArray SSRSegmentsarray=jsonObj.getJSONArray("SSRSegments");
				for(int i=0;i<SSRSegmentsarray.length();i++){
					JSONObject SSRSegmentsdetail=(JSONObject) SSRSegmentsarray.get(i);
					SSRSegments ss=new SSRSegments();
					ss.setTripIndicator(Integer.toString(SSRSegmentsdetail.getInt("TripIndicator")));
					ss.setSegmentIndicator(Integer.toString(SSRSegmentsdetail.getInt("SegmentIndicator")));
					ss.setFlightId(SSRSegmentsdetail.getString("FlightId"));
					ss.setAirlineCode(SSRSegmentsdetail.getString("AirlineCode"));
					if(SSRSegmentsdetail.has("Meals")){
					List<Meals> Meals=new ArrayList<Meals>();
					JSONArray Mealsarray=SSRSegmentsdetail.getJSONArray("Meals");
					for(int j=0;j<Mealsarray.length();j++){
						JSONObject Mealsobj=(JSONObject) Mealsarray.get(j);
						
						Meals Meal=new Meals();
						if(Mealsobj.has("WayType")){
							Meal.setWayType(Mealsobj.getString("WayType"));
						}
						
						Meal.setCode(Mealsobj.getString("Code"));
						if(Mealsobj.has("Description")){
						Meal.setDescription(Mealsobj.getString("Description"));
						}else{
							Meal.setDescription(Mealsobj.getString("AirlineDescription"));
						}
						Meal.setAmount(Mealsobj.getInt("Amount"));
						if(Mealsobj.has("Origin")){
						Meal.setOrigin(Mealsobj.getString("Origin"));
						}
						if(Mealsobj.has("Destination")){
						Meal.setDestination(Mealsobj.getString("Destination"));
						}
						if(Mealsobj.has("FlightNumber")){
						Meal.setFlightNumber(Mealsobj.getString("FlightNumber"));
						}
						if(Mealsobj.has("AirlineDescription")){
						Meal.setAirlineDescription(Mealsobj.getString("AirlineDescription"));
						}else{
							Meal.setAirlineDescription(Mealsobj.getString("Description"));
						}
						Meals.add(Meal);
					}
					ss.setMeals(Meals);
					}
					if(SSRSegmentsdetail.has("Baggages")){
					List<Baggages> listbaggages=new ArrayList<Baggages>();
					JSONArray Baggagesarray=SSRSegmentsdetail.getJSONArray("Baggages");
					for(int j=0;j<Baggagesarray.length();j++){
						JSONObject Baggageobj=(JSONObject) Baggagesarray.get(j);
						Baggages bag=new Baggages();
						if(Baggageobj.has("WayType")){
						bag.setWayType(Baggageobj.getString("WayType"));
						}
						if(Baggageobj.has("code")){
						bag.setCode(Baggageobj.getString("code"));
						}
						bag.setDescription(Baggageobj.getString("description"));
						bag.setAmount(Baggageobj.getInt("amount"));
						listbaggages.add(bag);
					}
					
					ss.setBaggages(listbaggages);
					}
					SSRSegments.add(ss);
				}
				output.setSSRSegments(SSRSegments);
				
			}else{
				JSONObject jsonObj1 = jsonObj.getJSONObject("ErrorDetails");
				output.setErrorcode(Integer.toString(jsonObj1.getInt("ErrorCode")));
				output.setErrormessage(jsonObj1.getString("ErrorMessage"));
			}
			
		}catch(Exception e){
			log.warn("getgetMealAndBaggageInfoParser:::"+e);
		}
		
		return output;
	}


	public static AirBookResponse getFlightBookingParser(String response,List<Airline> air) {
		AirBookResponse output=new AirBookResponse();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("AirBookResponse");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			
			if(jsonObj.getInt("ResponseStatus")==1){
				output.setTrackId(jsonObj.getString("TrackId"));
				System.out.println("ResponseStatus::"+jsonObj.getInt("ResponseStatus"));
				JSONArray TicketDetailsarray=jsonObj.getJSONArray("TicketDetails");
				
				List<TicketDetails> listTicketDetails=new ArrayList<TicketDetails>();
				for(int i=0;i<TicketDetailsarray.length();i++){
					JSONObject ticketdtobj=(JSONObject) TicketDetailsarray.get(i);
					TicketDetails TicketDetails=new TicketDetails();
					TicketDetails.setSkypointPNR(ticketdtobj.getString("SkypointPNR"));
					TicketDetails.setBookingId(ticketdtobj.getString("BookingId"));
					if(ticketdtobj.get("TotalAmount") instanceof Integer){
						TicketDetails.setTotalAmount(Integer.toString(ticketdtobj.getInt("TotalAmount")));
					}
					if(ticketdtobj.get("TotalAmount") instanceof Double){
						TicketDetails.setTotalAmount(Double.toString(ticketdtobj.getDouble("TotalAmount")));
					}
					
					TicketDetails.setAirlinePNR(ticketdtobj.getString("AirlinePNR"));
					TicketDetails.setCRSPNR(ticketdtobj.getString("CRSPNR"));
					if(ticketdtobj.get("OtherCharges") instanceof Integer){
						TicketDetails.setOtherCharges(Integer.toString(ticketdtobj.getInt("OtherCharges")));
					}
					if(ticketdtobj.get("OtherCharges") instanceof Double){
						TicketDetails.setOtherCharges(Double.toString(ticketdtobj.getDouble("OtherCharges")));
					}
					TicketDetails.setAdultCount(Integer.toString(ticketdtobj.getInt("AdultCount")));
					TicketDetails.setChildCount(Integer.toString(ticketdtobj.getInt("ChildCount")));
					TicketDetails.setInfantCount(Integer.toString(ticketdtobj.getInt("InfantCount")));
					TicketDetails.setOrigin(ticketdtobj.getString("Origin"));
					TicketDetails.setDestination(ticketdtobj.getString("Destination"));
					TicketDetails.setAirlineCode(ticketdtobj.getString("AirlineCode"));
					for(Airline mr : air){
						if(mr.getAirline_code().equals(ticketdtobj.getString("AirlineCode"))){
							TicketDetails.setAirlineNAme(mr.getAirline_name());
						}
					}
					TicketDetails.setTravelType(ticketdtobj.getString("TravelType"));
					TicketDetails.setIssueDateTime(ticketdtobj.getString("IssueDateTime"));
					TicketDetails.setTotalSegments(ticketdtobj.getInt("TotalSegments"));
					TicketDetails.setBookingType(ticketdtobj.getString("BookingType"));
					JSONObject custdtobj=ticketdtobj.getJSONObject("CustomerDetail");
					CustomerDetail cust=new CustomerDetail();
					cust.setTitle(custdtobj.getString("Title"));
					cust.setName(custdtobj.getString("Name"));
					cust.setAddress(custdtobj.getString("Address"));
					cust.setCity(custdtobj.getString("City"));
					cust.setCountryId(custdtobj.getString("CountryId"));
					cust.setContactNumber(custdtobj.getString("ContactNumber"));
					cust.setEmailId(custdtobj.getString("EmailId"));
					TicketDetails.setCustomerDetail(cust);
					JSONArray Passengerarray=ticketdtobj.getJSONArray("Passenger");
					List<Passenger> listpassenger=new ArrayList<Passenger>();
					for(int j=0;j<Passengerarray.length();j++){
						JSONObject passengerobg=(JSONObject) Passengerarray.get(j);
						Passenger passenger=new Passenger();
						System.out.println("TicketNumber::"+passengerobg.getString("TicketId"));
						passenger.setTicketId(passengerobg.getString("TicketId"));
						passenger.setTicketNumber(passengerobg.getString("TicketNumber"));
						passenger.setPaxType(passengerobg.getString("PaxType"));
						passenger.setTitle(passengerobg.getString("Title"));
						passenger.setFirstName(passengerobg.getString("FirstName"));
						passenger.setLastName(passengerobg.getString("LastName"));
						passenger.setGender(passengerobg.getString("gender"));
						JSONArray booksegmentarray=passengerobg.getJSONArray("BookedSegments");
						List<BookedSegments> listBookedSegments=new ArrayList<BookedSegments>();
						for(int k=0;k<booksegmentarray.length();k++){
							JSONObject booksegobj=(JSONObject) booksegmentarray.get(k);
							BookedSegments bs=new BookedSegments();
							bs.setTripIndicator(booksegobj.getString("TripIndicator"));
							bs.setSegmentIndicator(booksegobj.getString("SegmentIndicator"));
							bs.setFlightNumber(booksegobj.getString("FlightNumber"));
							bs.setAirCraftType(booksegobj.getString("AirCraftType"));
							bs.setOrigin(booksegobj.getString("Origin"));
							bs.setDuration(booksegobj.getInt("Duration"));
							bs.setOriginAirport(booksegobj.getString("OriginAirport"));
							bs.setDestination(booksegobj.getString("Destination"));
							bs.setDestinationAirport(booksegobj.getString("DestinationAirport"));
							bs.setArrivaldatetime(booksegobj.getString("Arrivaldatetime"));
							bs.setAirlineCode(booksegobj.getString("AirlineCode"));
							bs.setDepartureDateTime(booksegobj.getString("DepartureDateTime"));
							bs.setClassCode(booksegobj.getString("ClassCode"));
							bs.setClassType(booksegobj.getString("ClassType"));
							if(booksegobj.has("CurrencyCode")){
								bs.setCurrencyCode(booksegobj.getString("CurrencyCode"));
							}
							
							if(booksegobj.get("BaseAmount") instanceof Integer){
								System.out.println("getInt:::"+booksegobj.getInt("BaseAmount"));
								bs.setBaseAmount(Integer.toString(booksegobj.getInt("BaseAmount")));
							}
							if(booksegobj.get("BaseAmount") instanceof Double){
								System.out.println("booksegobj.getDouble('BaseAmount'):::"+booksegobj.getDouble("BaseAmount"));
								bs.setBaseAmount(Double.toString(booksegobj.getDouble("BaseAmount")));
							}
							if(booksegobj.get("TaxAmount") instanceof Integer){
								bs.setTaxAmount(Integer.toString(booksegobj.getInt("TaxAmount")));
							}
							if(booksegobj.get("TaxAmount") instanceof Double){
								bs.setTaxAmount(Double.toString(booksegobj.getDouble("TaxAmount")));
							}
							if(booksegobj.get("TransactionFee") instanceof Integer){
								bs.setTransactionFee(Integer.toString(booksegobj.getInt("TransactionFee")));
							}
							if(booksegobj.get("TransactionFee") instanceof Double){
								bs.setTransactionFee(Double.toString(booksegobj.getDouble("TransactionFee")));
							}
							if(booksegobj.get("ServiceCharge") instanceof Integer){
								bs.setServiceCharge(Integer.toString(booksegobj.getInt("ServiceCharge")));
							}
							if(booksegobj.get("ServiceCharge") instanceof Double){
								bs.setServiceCharge(Double.toString(booksegobj.getDouble("ServiceCharge")));
							}
							
							if(booksegobj.get("GrossAmount") instanceof Integer){
								bs.setGrossAmount(Integer.toString(booksegobj.getInt("GrossAmount")));
							}
							if(booksegobj.get("GrossAmount") instanceof Double){
								bs.setGrossAmount(Double.toString(booksegobj.getDouble("GrossAmount")));
							}
							if(booksegobj.has("TaxDetails")){
								List<TaxDetails> listTaxDetails=new ArrayList<TaxDetails>();
								JSONArray arraytax=booksegobj.getJSONArray("TaxDetails");
								for(int l=0;l<arraytax.length();l++){
									JSONObject taxdobj=(JSONObject) arraytax.get(l);
									TaxDetails td=new TaxDetails();
									
									if(taxdobj.get("Amount") instanceof Integer){
										td.setAmount(Integer.toString(taxdobj.getInt("Amount")));
									}
									if(taxdobj.get("Amount") instanceof Double){
										td.setAmount(Double.toString(taxdobj.getDouble("Amount")));
									}
									td.setDescription(taxdobj.getString("Description"));
									listTaxDetails.add(td);
								}
								bs.setTaxDetails(listTaxDetails);
							}
							
							JSONArray arrayadditionl=booksegobj.getJSONArray("AdditionalSSRDetails");
							System.out.println("arrayadditionl.length()::"+arrayadditionl.length());
							List<AdditionalSSRDetails> listAdditionalSSRDetails=new ArrayList<AdditionalSSRDetails>();
							if(arrayadditionl.length()!=0){
								for(int s=0;s<arrayadditionl.length();s++){
									JSONObject AdditionalSSRDetaiobj=(JSONObject) arrayadditionl.get(s);
									AdditionalSSRDetails ad=new AdditionalSSRDetails();
									ad.setName(AdditionalSSRDetaiobj.getString("Name"));
									ad.setDescription(AdditionalSSRDetaiobj.getString("Description"));
									ad.setWeight(Integer.toString(AdditionalSSRDetaiobj.getInt("weight")));
									ad.setAmount(Integer.toString(AdditionalSSRDetaiobj.getInt("Amount")));
									listAdditionalSSRDetails.add(ad);
								}
							}
							bs.setAdditionalSSRDetails(listAdditionalSSRDetails);
							listBookedSegments.add(bs);
							System.out.println("listAdditionalSSRDetails.length()::"+listAdditionalSSRDetails.size());
						}
						System.out.println("listTicketDetails::"+listTicketDetails.size());
						System.out.println("passenger::"+listpassenger.size());
						passenger.setBookedSegments(listBookedSegments);
						listpassenger.add(passenger);
					}
					System.out.println("passenger::"+listpassenger.size());
					TicketDetails.setPassenger(listpassenger);
					listTicketDetails.add(TicketDetails);
				}
				output.setTicketDetails(listTicketDetails);
				System.out.println("listTicketDetails::"+listTicketDetails.size());
			}else{
				JSONObject jsonObj1 = jsonObj.getJSONObject("ErrorDetails");
				output.setErrorCode(Integer.toString(jsonObj1.getInt("ErrorCode")));
				output.setErrorMessage(jsonObj1.getString("ErrorMessage"));
			}
			
		}catch(Exception e){
			log.warn("getFlightBookingParser:EX:"+e);
		}
		return output;
	}


	public static CancelResponse getcancelTicketParser(CancelRequest requests,String response) {
		CancelResponse output=new CancelResponse();
		List<CancelResponsedetails> outlist=new ArrayList<CancelResponsedetails>();
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONArray jsonObj=jsonObj2.getJSONArray("CancelDetail");
		List<CancelResponse> listCancelResponse=new ArrayList<CancelResponse>();
		for(int i=0;i<jsonObj.length();i++){
			JSONObject fdobj=(JSONObject) jsonObj.get(i);
			CancelResponsedetails outputs=new CancelResponsedetails();
			outputs.setResponseStatus(fdobj.getInt("ResponseStatus"));
			if(fdobj.getInt("ResponseStatus")==1){
				//CancellationDetail CancellationDetail=new com.skyflight.model.CancellationDetail(requests.getBookingId(), requests.getPNR(), requests.getTrackId(), jsonObj.getString("TicketId"), jsonObj.getString("ChangeRequestId"), jsonObj.getString("RefundedAmount"), jsonObj.getString("CancellationCharge"), jsonObj.getString("status"));
				outputs.setChangeRequestId(fdobj.getString("ChangeRequestId"));
				outputs.setTicketId(fdobj.getString("TicketId"));
				outputs.setRefundedAmount(fdobj.getString("RefundedAmount"));
				outputs.setCancellationCharge(fdobj.getString("CancellationCharge"));
				//output.setServiceTaxOnRAF(jsonObj.getString("ServiceTaxOnRAF"));
				outputs.setStatus(fdobj.getString("status"));
			}else{
				outputs.setRemarks(fdobj.getString("Remarks"));
			}
			outlist.add(outputs);
		}
		output.setCancellationDetail(outlist);
		//JSONObject jsonObj=jsonObj2.getJSONObject("CancelDetail");
		
		return output;
	}


	public static SeatResponse getSeatDetailsParser(String response) {
		SeatResponse output=new SeatResponse();
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONObject jsonObj=jsonObj2.getJSONObject("SeatResponse");
		output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
		output.setTrackId(jsonObj.getString("TrackId"));
		JSONObject SeatMapOutput=jsonObj.getJSONObject("SeatMapOutput");
		JSONArray FlightSegments=(JSONArray) SeatMapOutput.get("FlightSegments");
		List<SeatFlightSegments> listSeatFlightSegments=new ArrayList<SeatFlightSegments>();
		for(int i=0;i<FlightSegments.length();i++){
			JSONObject fdobj=(JSONObject) FlightSegments.get(i);
			SeatFlightSegments ssf=new SeatFlightSegments();
			ssf.setAirCraftType(fdobj.getString("AirCraftType"));
			ssf.setAirlineCode(fdobj.getString("AirlineCode"));
			ssf.setFlightNumber(fdobj.getString("FlightNumber"));
			ssf.setOrigin(fdobj.getString("Origin"));
			ssf.setDestination(fdobj.getString("Destination"));
			List<SeatLayoutDetail> listSeatLayoutDetail=new ArrayList<SeatLayoutDetail>();
			
			JSONObject SeatLayoutDetail=fdobj.getJSONObject("SeatLayoutDetail");
			JSONObject SeatLayout=SeatLayoutDetail.getJSONObject("SeatLayout");
			JSONArray SeatRows=SeatLayout.getJSONArray("SeatRows");
			for(int j=0;j<SeatRows.length();j++){
				SeatLayoutDetail SeatL=new SeatLayoutDetail();
				JSONObject seatrowobj=(JSONObject) SeatRows.get(j);
				JSONArray Seatsarray=seatrowobj.getJSONArray("Seats");
				List<Seat> listseat=new ArrayList<Seat>();
				for(int k=0;k<Seatsarray.length();k++){
					JSONObject Seatsobj=(JSONObject) Seatsarray.get(k);
					Seat seat=new Seat();
					seat.setSeatNo(Seatsobj.getString("SeatNo"));
					seat.setSeatCode(Seatsobj.getString("SeatCode"));
					seat.setStatus(Seatsobj.getString("status"));
					seat.setSeatAmount(Seatsobj.getInt("SeatAmount"));
					seat.setDeck(Seatsobj.getString("Deck"));
					seat.setCompartment(Seatsobj.getString("Compartment"));
					seat.setWayType(Seatsobj.getString("WayType"));
					seat.setFlightNumber(Seatsobj.getString("FlightNumber"));
					seat.setAirlineCode(Seatsobj.getString("AirlineCode"));
					seat.setCraftType(Seatsobj.getString("CraftType"));
					seat.setOrigin(Seatsobj.getString("Origin"));
					seat.setDestination(Seatsobj.getString("Destination"));
					
					listseat.add(seat);
				}
				SeatL.setSeat(listseat);
				listSeatLayoutDetail.add(SeatL);
			}
			ssf.setSeatLayoutDetail(listSeatLayoutDetail);
			listSeatFlightSegments.add(ssf);
		}
		output.setFlightSegments(listSeatFlightSegments);
		return output;
	}


	public static CalendarFareRes getCalenderFareParser(String response, List<Markup> adminmarkupllist,
			List<Markup> usermarkuplist) {
		CalendarFareRes output=new CalendarFareRes();
		JSONObject jsonObj2 = new JSONObject(response);
		System.out.println("jsonObj2::"+jsonObj2);
		JSONObject jsonObj=jsonObj2.getJSONObject("CalendarFareRes");
		output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
		output.setTrackId(jsonObj.getString("TrackId"));
		output.setOrigin(jsonObj.getString("Origin"));
		output.setDestination(jsonObj.getString("Destination"));
		List<CalendarFareResults> listCalendarFareResults=new ArrayList<CalendarFareResults>();
		JSONArray arrayfareres=jsonObj.getJSONArray("CalendarFareResults");
		for(int i=0;i<arrayfareres.length();i++){
			JSONObject jobj=(JSONObject) arrayfareres.get(i);
			CalendarFareResults cr=new CalendarFareResults();
			cr.setAirlineCode(jobj.getString("AirlineCode"));
			cr.setAirlineName(jobj.getString("AirlineName"));
			String[] date=jobj.getString("DepartureDate").split("T");
			cr.setDepartureDate(date[0]);
			cr.setIsLowestFareOfMonth(jobj.getBoolean("IsLowestFareOfMonth"));
			cr.setFare(Double.toString(jobj.getDouble("Fare")));
			cr.setBaseFare(Double.toString(jobj.getDouble("BaseFare")));
			cr.setTax(Double.toString(jobj.getDouble("Tax")));
			cr.setOtherCharges(Double.toString(jobj.getDouble("OtherCharges")));
			cr.setFuelSurcharge(Double.toString(jobj.getDouble("FuelSurcharge")));
			listCalendarFareResults.add(cr);
		}
		output.setCalendarFareResults(listCalendarFareResults);
		return output;
	}


	

}
