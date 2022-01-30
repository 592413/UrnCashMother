package com.redbus.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.redbus.model.AvailableTrips;
import com.redbus.model.BoardingDroppingTimes;
import com.redbus.model.CancellationData;
import com.redbus.model.CommonData;
import com.redbus.model.Entry;
import com.redbus.model.FareDetails;
import com.redbus.model.InventoryItem;
import com.redbus.model.Passenger;
import com.redbus.model.ReschedulingPolicy;
import com.redbus.model.Ticket;

public class WebServiceResponseParser {
	private static final Logger log = Logger.getLogger(WebServiceResponseParser.class);
	Gson gson = new Gson();
	
	public static List<AvailableTrips>  getAvailableTripsParser(String response){
		List<AvailableTrips>  availableTrips = new ArrayList<AvailableTrips>(); 
		JSONObject jsonObj = new JSONObject(response);
		JSONArray availableTripsArray = (JSONArray) jsonObj.get("availableTrips");
		for (int i = 0; i <availableTripsArray.length(); i++) {
			AvailableTrips availableTripsObject=new AvailableTrips();
			JSONObject availableTripObj = availableTripsArray.getJSONObject(i);
			
			//boardingTimes part start
			if(availableTripObj.has("boardingTimes")){
				List<BoardingDroppingTimes> boardingTimes = new ArrayList<BoardingDroppingTimes>();
				Object boardingTimesObj = availableTripObj.get("boardingTimes");
				if (boardingTimesObj instanceof JSONArray) {
					JSONArray boardingTimesDataArray = availableTripObj.getJSONArray("boardingTimes");
					for (int bt = 0; bt <boardingTimesDataArray.length(); bt++) {
						JSONObject droppingTimesData = boardingTimesDataArray.getJSONObject(bt);
						BoardingDroppingTimes boardingTimesObject = new BoardingDroppingTimes();
						boardingTimesObject.setAddress(droppingTimesData.getString("address"));
						boardingTimesObject.setBpId(droppingTimesData.getLong("bpId"));
						boardingTimesObject.setBpName(droppingTimesData.getString("bpName"));
						boardingTimesObject.setContactNumber(droppingTimesData.getString("contactNumber"));
						boardingTimesObject.setLandmark(droppingTimesData.getString("landmark"));
						boardingTimesObject.setLocation(droppingTimesData.getString("location"));
						boardingTimesObject.setPrime(droppingTimesData.getString("prime"));
						boardingTimesObject.setTime(droppingTimesData.getLong("time"));
						boardingTimes.add(boardingTimesObject);
					}
				}else{
					JSONObject boardingTimesData = availableTripObj.getJSONObject("boardingTimes");
					BoardingDroppingTimes boardingTimesObject = new BoardingDroppingTimes();
					boardingTimesObject.setAddress(boardingTimesData.getString("address"));
					boardingTimesObject.setBpId(boardingTimesData.getLong("bpId"));
					boardingTimesObject.setBpName(boardingTimesData.getString("bpName"));
					boardingTimesObject.setContactNumber(boardingTimesData.getString("contactNumber"));
					boardingTimesObject.setLandmark(boardingTimesData.getString("landmark"));
					boardingTimesObject.setLocation(boardingTimesData.getString("location"));
					boardingTimesObject.setPrime(boardingTimesData.getString("prime"));
					boardingTimesObject.setTime(boardingTimesData.getLong("time"));
					boardingTimes.add(boardingTimesObject);
				}
				availableTripsObject.setBoardingTimes(boardingTimes);
			}
			//boardingTimes part end
			
			//droppingTimes part start
			if(availableTripObj.has("droppingTimes")){
				List<BoardingDroppingTimes> droppingTimes = new ArrayList<BoardingDroppingTimes>();
				Object droppingTimesObj = availableTripObj.get("droppingTimes");
				if (droppingTimesObj instanceof JSONArray) {
					JSONArray droppingTimesDataArray = availableTripObj.getJSONArray("droppingTimes");
					for (int dt = 0; dt <droppingTimesDataArray.length(); dt++) {
						JSONObject droppingTimesData = droppingTimesDataArray.getJSONObject(dt);
						BoardingDroppingTimes droppingTimesObject = new BoardingDroppingTimes();
						droppingTimesObject.setAddress(droppingTimesData.getString("address"));
						droppingTimesObject.setBpId(droppingTimesData.getLong("bpId"));
						droppingTimesObject.setBpName(droppingTimesData.getString("bpName"));
						droppingTimesObject.setContactNumber(droppingTimesData.getString("contactNumber"));
						droppingTimesObject.setLandmark(droppingTimesData.getString("landmark"));
						droppingTimesObject.setLocation(droppingTimesData.getString("location"));
						droppingTimesObject.setPrime(droppingTimesData.getString("prime"));
						droppingTimesObject.setTime(droppingTimesData.getLong("time"));
						droppingTimes.add(droppingTimesObject);
					}
				}else{
					JSONObject droppingTimesData = availableTripObj.getJSONObject("droppingTimes");
					BoardingDroppingTimes droppingTimesObject = new BoardingDroppingTimes();
					droppingTimesObject.setAddress(droppingTimesData.getString("address"));
					droppingTimesObject.setBpId(droppingTimesData.getLong("bpId"));
					droppingTimesObject.setBpName(droppingTimesData.getString("bpName"));
					droppingTimesObject.setContactNumber(droppingTimesData.getString("contactNumber"));
					droppingTimesObject.setLandmark(droppingTimesData.getString("landmark"));
					droppingTimesObject.setLocation(droppingTimesData.getString("location"));
					droppingTimesObject.setPrime(droppingTimesData.getString("prime"));
					droppingTimesObject.setTime(droppingTimesData.getLong("time"));
					droppingTimes.add(droppingTimesObject);
				}				
				availableTripsObject.setDroppingTimes(droppingTimes);
			}
			//droppingTimes part end
		 
			//fareDetails part start
			if(availableTripObj.has("fareDetails")){
				List<FareDetails> fareDetails = new ArrayList<FareDetails>();
				Object fareDetailsObj = availableTripObj.get("fareDetails");
				if (fareDetailsObj instanceof JSONArray) {
					JSONArray fareDetailArray = availableTripObj.getJSONArray("fareDetails");
					for (int fd = 0; fd <fareDetailArray.length(); fd++) {
						JSONObject fareDetailsData = fareDetailArray.getJSONObject(fd);
						FareDetails fareDetailsObject = new FareDetails();
						fareDetailsObject.setBankTrexAmt(fareDetailsData.getDouble("bankTrexAmt"));
						fareDetailsObject.setBaseFare(fareDetailsData.getDouble("baseFare"));
						fareDetailsObject.setBookingFee(fareDetailsData.getDouble("bookingFee"));
						fareDetailsObject.setChildFare(fareDetailsData.getDouble("childFare"));					
						fareDetailsObject.setGst(fareDetailsData.getDouble("gst"));
						fareDetailsObject.setLevyFare(fareDetailsData.getDouble("levyFare"));
						fareDetailsObject.setMarkupFareAbsolute(fareDetailsData.getDouble("markupFareAbsolute"));
						fareDetailsObject.setMarkupFarePercentage(fareDetailsData.getDouble("markupFarePercentage"));
						fareDetailsObject.setOperatorServiceChargeAbsolute(fareDetailsData.getDouble("operatorServiceChargeAbsolute"));
						fareDetailsObject.setOperatorServiceChargePercentage(fareDetailsData.getDouble("operatorServiceChargePercentage"));
						fareDetailsObject.setOpFare(fareDetailsData.getDouble("opFare"));																	
						fareDetailsObject.setServiceCharge(fareDetailsData.getDouble("serviceCharge"));
						fareDetailsObject.setServiceTaxAbsolute(fareDetailsData.getDouble("serviceTaxAbsolute"));
						fareDetailsObject.setServiceTaxPercentage(fareDetailsData.getDouble("serviceTaxPercentage"));
						fareDetailsObject.setSrtFee(fareDetailsData.getDouble("srtFee"));
						fareDetailsObject.setTollFee(fareDetailsData.getDouble("tollFee"));
						fareDetailsObject.setTotalFare(fareDetailsData.getDouble("totalFare"));
						fareDetails.add(fareDetailsObject);
						
					}
				}else{
						JSONObject fareDetailsData = availableTripObj.getJSONObject("fareDetails");
						FareDetails fareDetailsObject = new FareDetails();
						fareDetailsObject.setBankTrexAmt(fareDetailsData.getDouble("bankTrexAmt"));
						fareDetailsObject.setBaseFare(fareDetailsData.getDouble("baseFare"));
						fareDetailsObject.setBookingFee(fareDetailsData.getDouble("bookingFee"));
						fareDetailsObject.setChildFare(fareDetailsData.getDouble("childFare"));					
						fareDetailsObject.setGst(fareDetailsData.getDouble("gst"));
						fareDetailsObject.setLevyFare(fareDetailsData.getDouble("levyFare"));
						fareDetailsObject.setMarkupFareAbsolute(fareDetailsData.getDouble("markupFareAbsolute"));
						fareDetailsObject.setMarkupFarePercentage(fareDetailsData.getDouble("markupFarePercentage"));
						fareDetailsObject.setOperatorServiceChargeAbsolute(fareDetailsData.getDouble("operatorServiceChargeAbsolute"));
						fareDetailsObject.setOperatorServiceChargePercentage(fareDetailsData.getDouble("operatorServiceChargePercentage"));
						fareDetailsObject.setOpFare(fareDetailsData.getDouble("opFare"));																	
						fareDetailsObject.setServiceCharge(fareDetailsData.getDouble("serviceCharge"));
						fareDetailsObject.setServiceTaxAbsolute(fareDetailsData.getDouble("serviceTaxAbsolute"));
						fareDetailsObject.setServiceTaxPercentage(fareDetailsData.getDouble("serviceTaxPercentage"));
						fareDetailsObject.setSrtFee(fareDetailsData.getDouble("srtFee"));
						fareDetailsObject.setTollFee(fareDetailsData.getDouble("tollFee"));
						fareDetailsObject.setTotalFare(fareDetailsData.getDouble("totalFare"));
						fareDetails.add(fareDetailsObject);
				}
				availableTripsObject.setFareDetails(fareDetails);
			}
			//fareDetails part end
		   
			//fares part start
			if(availableTripObj.has("fares")){
				List<Double> fares = new ArrayList<Double>();
				Object fareObject = availableTripObj.get("fares");
				if (fareObject instanceof JSONArray) {
					JSONArray faresArray = availableTripObj.getJSONArray("fares");
					for (int fr = 0; fr <faresArray.length(); fr++) {
						fares.add(Double.parseDouble(faresArray.get(fr).toString()));
						}
				}else{
					fares.add(availableTripObj.getDouble("fares"));
				}
				 availableTripsObject.setFares(fares);
			}
			//fares part end
			
			
			
			
			if(availableTripObj.has("AC"))
				availableTripsObject.setIsAC(availableTripObj.getString("AC"));			
			if(availableTripObj.has("isAgentAccount"))
				availableTripsObject.setIsAgentAccount(availableTripObj.getString("isAgentAccount"));
			if(availableTripObj.has("id"))
				availableTripsObject.setId(availableTripObj.getLong("id"));
			if(availableTripObj.has("arrivalTime"))
				availableTripsObject.setArrivalTime(availableTripObj.getLong("arrivalTime"));
			if(availableTripObj.has("availableSeats"))
				availableTripsObject.setAvailableSeats(availableTripObj.getLong("availableSeats"));
			if(availableTripObj.has("bookable"))
				availableTripsObject.setBookable(availableTripObj.getString("bookable"));
			if(availableTripObj.has("avlWindowSeats"))
				availableTripsObject.setAvlWindowSeats(availableTripObj.getLong("avlWindowSeats"));
			if(availableTripObj.has("bpDpSeatLayout"))
				availableTripsObject.setBpDpSeatLayout(availableTripObj.getString("bpDpSeatLayout"));
			if(availableTripObj.has("busImageCount"))
				availableTripsObject.setBusImageCount(availableTripObj.getLong("busImageCount"));
			if(availableTripObj.has("busTypeId"))
				availableTripsObject.setBusTypeId(availableTripObj.getLong("busTypeId"));
			if(availableTripObj.has("busType"))
				availableTripsObject.setBusType(availableTripObj.getString("busType"));
			if(availableTripObj.has("busRoutes"))
				availableTripsObject.setBusRoutes(availableTripObj.getString("busRoutes"));
			if(availableTripObj.has("cancellationPolicy"))
				availableTripsObject.setCancellationPolicy(availableTripObj.getString("cancellationPolicy"));
			if(availableTripObj.has("departureTime"))
				availableTripsObject.setDepartureTime(availableTripObj.getLong("departureTime"));
			if(availableTripObj.has("dropPointMandatory"))
				availableTripsObject.setDropPointMandatory(availableTripObj.getString("dropPointMandatory"));
			if(availableTripObj.has("operator"))
				availableTripsObject.setOperator(availableTripObj.getLong("operator"));
			if(availableTripObj.has("otgEnabled"))
				availableTripsObject.setOtgEnabled(availableTripObj.getString("otgEnabled"));
			if(availableTripObj.has("partialCancellationAllowed"))
				availableTripsObject.setPartialCancellationAllowed(availableTripObj.getString("partialCancellationAllowed"));
			if(availableTripObj.has("rtc"))
				availableTripsObject.setRtc(availableTripObj.getString("rtc"));
			if(availableTripObj.has("seater"))
				availableTripsObject.setSeater(availableTripObj.getString("seater"));
			if(availableTripObj.has("maxSeatsPerTicket"))
				availableTripsObject.setMaxSeatsPerTicket(availableTripObj.getLong("maxSeatsPerTicket")) ;
			if(availableTripObj.has("zeroCancellationTime"))
				availableTripsObject.setZeroCancellationTime(availableTripObj.getDouble("zeroCancellationTime"));
			if(availableTripObj.has("mTicketEnabled"))
				availableTripsObject.setmTicketEnabled(availableTripObj.getString("mTicketEnabled")) ;
			if(availableTripObj.has("travels"))
				availableTripsObject.setTravels(availableTripObj.getString("travels")) ;
			if(availableTripObj.has("availSrCitizen"))
				availableTripsObject.setAvailSrCitizen(availableTripObj.getString("availSrCitizen")) ;
			if(availableTripObj.has("availCatCard"))
				availableTripsObject.setAvailCatCard(availableTripObj.getString("availCatCard")) ;
			if(availableTripObj.has("idProofRequired"))
				availableTripsObject.setIdProofRequired(availableTripObj.getString("idProofRequired")) ;
			if(availableTripObj.has("liveTrackingAvailable"))
				availableTripsObject.setLiveTrackingAvailable(availableTripObj.getString("liveTrackingAvailable"));
			if(availableTripObj.has("nonAC"))
				availableTripsObject.setNonAC(availableTripObj.getString("nonAC") ) ;
			if(availableTripObj.has("primaryPaxCancellable"))
				availableTripsObject.setPrimaryPaxCancellable(availableTripObj.getString("primaryPaxCancellable"));
			if(availableTripObj.has("routeId"))
				availableTripsObject.setRouteId(availableTripObj.getLong("routeId")) ;
			if(availableTripObj.has("singleLadies"))
				availableTripsObject.setSingleLadies(availableTripObj.getString("singleLadies"));
			if(availableTripObj.has("sleeper"))
				availableTripsObject.setSleeper(availableTripObj.getString("sleeper")) ;
			if(availableTripObj.has("vehicleType"))
				availableTripsObject.setVehicleType(availableTripObj.getString("vehicleType")) ;
			if(availableTripObj.has("additionalCommission"))
				availableTripsObject.setAdditionalCommission(availableTripObj.getString("additionalCommission")) ;
			if(availableTripObj.has("agentServiceCharge"))
				availableTripsObject.setAgentServiceCharge(availableTripObj.getDouble("agentServiceCharge")) ;
			if(availableTripObj.has("agentServiceChargeAllowed"))
				availableTripsObject.setAgentServiceChargeAllowed(availableTripObj.getString("agentServiceChargeAllowed")) ;
			if(availableTripObj.has("boCommission"))
				availableTripsObject.setBoCommission(availableTripObj.getDouble("boCommission"));
			if(availableTripObj.has("busServiceId"))
				availableTripsObject.setBusServiceId(availableTripObj.getString("busServiceId")) ;
			if(availableTripObj.has("cpId"))
				availableTripsObject.setCpId(availableTripObj.getString("cpId")) ;
			if(availableTripObj.has("destination"))
				availableTripsObject.setDestination(availableTripObj.getString("destination")) ;
			if(availableTripObj.has("doj"))
				availableTripsObject.setDoj(availableTripObj.getString("doj"));
			if(availableTripObj.has("duration"))
				availableTripsObject.setDuration(availableTripObj.getString("duration")) ;
			if(availableTripObj.has("exactSearch"))
				availableTripsObject.setExactSearch(availableTripObj.getString("exactSearch")) ;
			if(availableTripObj.has("flatComApplicable"))
				availableTripsObject.setFlatComApplicable(availableTripObj.getString("flatComApplicable")) ;
			if(availableTripObj.has("flatSSComApplicable"))
				availableTripsObject.setFlatSSComApplicable(availableTripObj.getString("flatSSComApplicable")) ;
			if(availableTripObj.has("gdsCommission"))
				availableTripsObject.setGdsCommission(availableTripObj.getString("gdsCommission"));
			if(availableTripObj.has("nextDay"))
				availableTripsObject.setNextDay(availableTripObj.getString("nextDay")) ;
			if(availableTripObj.has("otgPolicy"))
				availableTripsObject.setOtgPolicy(availableTripObj.getString("otgPolicy")) ;
			if(availableTripObj.has("partnerBaseCommission"))
				availableTripsObject.setPartnerBaseCommission(availableTripObj.getDouble("partnerBaseCommission")) ;
			if(availableTripObj.has("selfInventory"))
				availableTripsObject.setSelfInventory(availableTripObj.getString("selfInventory")) ;
			if(availableTripObj.has("socialDistancing"))
				availableTripsObject.setSocialDistancing(availableTripObj.getString("socialDistancing")) ;
			if(availableTripObj.has("source"))
				availableTripsObject.setSource(availableTripObj.getString("source") ) ;
			if(availableTripObj.has("tatkalTime"))
				availableTripsObject.setTatkalTime(availableTripObj.getString("tatkalTime")) ;
			if(availableTripObj.has("unAvailable"))
				availableTripsObject.setUnAvailable(availableTripObj.getString("unAvailable")) ;
			if(availableTripObj.has("viaRoutes"))
				availableTripsObject.setViaRoutes(availableTripObj.getString("viaRoutes")) ;
			
			availableTrips.add(availableTripsObject);
		
		}
		
		return availableTrips;
	}

	public static Ticket getTicketDetailsParser(String responseString) {
		Ticket  availableTicket = new Ticket(); 
		JSONObject jsonObj = new JSONObject(responseString);
		List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		if(jsonObj.has("inventoryItems")){
			Object inventoryObject = jsonObj.get("inventoryItems");
			if (inventoryObject instanceof JSONArray) {
				JSONArray invDetailsArray = jsonObj.getJSONArray("inventoryItems");
				for (int inv = 0; inv <invDetailsArray.length(); inv++) {
					JSONObject invDetailsData = invDetailsArray.getJSONObject(inv);
					JSONObject passengerObj = new JSONObject();
					InventoryItem invObject = new InventoryItem();
					Passenger passenger =new Passenger();
					
					if(invDetailsData.has("fare"))
						invObject.setFare(invDetailsData.getDouble("fare"));
					if(invDetailsData.has("ladiesSeat"))
						invObject.setLadiesSeat(invDetailsData.getString("ladiesSeat"));
					if(invDetailsData.has("seatName"))
						invObject.setSeatName(invDetailsData.getString("seatName"));
					if(invDetailsData.has("passenger"))
						 passengerObj = invDetailsData.getJSONObject("passenger");
					     if(passengerObj.has("passenger"))
					    	 if(passengerObj.has("address"))
					    		 passenger.setAddress(passengerObj.getString("address"));
					     	 if(passengerObj.has("age"))
					     		 passenger.setAge(passengerObj.getLong("age"));
					     	 if(passengerObj.has("email"))
					     		 passenger.setEmail(passengerObj.getString("email"));
					     	 if(passengerObj.has("gender"))
					     		 passenger.setGender(passengerObj.getString("gender"));
					     	 if(passengerObj.has("idNumber"))
					     		 passenger.setIdNumber(passengerObj.getString("idNumber"));
					     	 if(passengerObj.has("idType"))
					     		 passenger.setIdType(passengerObj.getString("idType"));
					     	 if(passengerObj.has("mobile"))
					     		 passenger.setMobile(passengerObj.getLong("mobile"));
					     	 if(passengerObj.has("name"))
					     		 passenger.setName(passengerObj.getString("name"));
					     	 if(passengerObj.has("primary"))
					     		 passenger.setPrimary(passengerObj.getString("primary"));
					     	 if(passengerObj.has("title"))
					     		 passenger.setTitle(passengerObj.getString("title"));
					     	 
					     	invObject.setPassenger(passenger);      
					     	inventoryItems.add(invObject);
				}
			}else{
				
				JSONObject invDetailsData = jsonObj.getJSONObject("inventoryItems");
				InventoryItem invObject = new InventoryItem();
				Passenger passenger =new Passenger();
				JSONObject passengerObj = new JSONObject();
				
				if(invDetailsData.has("fare"))
					invObject.setFare(invDetailsData.getDouble("fare"));
				if(invDetailsData.has("ladiesSeat"))
					invObject.setLadiesSeat(invDetailsData.getString("ladiesSeat"));
				if(invDetailsData.has("seatName"))
					invObject.setSeatName(invDetailsData.getString("seatName"));
				if(invDetailsData.has("passenger"))
					 passengerObj = invDetailsData.getJSONObject("passenger");
				     if(passengerObj.has("passenger"))
				    	 if(passengerObj.has("address"))
				    		 passenger.setAddress(passengerObj.getString("address"));
				     	 if(passengerObj.has("age"))
				     		 passenger.setAge(passengerObj.getLong("age"));
				     	 if(passengerObj.has("email"))
				     		 passenger.setEmail(passengerObj.getString("email"));
				     	 if(passengerObj.has("gender"))
				     		 passenger.setGender(passengerObj.getString("gender"));
				     	 if(passengerObj.has("idNumber"))
				     		 passenger.setIdNumber(passengerObj.getString("idNumber"));
				     	 if(passengerObj.has("idType"))
				     		 passenger.setIdType(passengerObj.getString("idType"));
				     	 if(passengerObj.has("mobile"))
				     		 passenger.setMobile(passengerObj.getLong("mobile"));
				     	 if(passengerObj.has("name"))
				     		 passenger.setName(passengerObj.getString("name"));
				     	 if(passengerObj.has("primary"))
				     		 passenger.setPrimary(passengerObj.getString("primary"));
				     	 if(passengerObj.has("title"))
				     		 passenger.setTitle(passengerObj.getString("title"));
				     	 
				     	invObject.setPassenger(passenger);      
				     	inventoryItems.add(invObject);
				
				}
			
		}
		availableTicket.setInventoryItems(inventoryItems);
		
		if(jsonObj.has("reschedulingPolicy")){
			JSONObject reschedulingPolicyData = jsonObj.getJSONObject("reschedulingPolicy");
			ReschedulingPolicy reschedulingPolicy = new ReschedulingPolicy();
			if(reschedulingPolicyData.has("reschedulingCharge"))
				reschedulingPolicy.setReschedulingCharge(reschedulingPolicyData.getDouble("reschedulingCharge"));
			if(reschedulingPolicyData.has("windowTime"))
				reschedulingPolicy.setWindowTime(reschedulingPolicyData.getLong("windowTime"));			
			availableTicket.setReschedulingPolicy(reschedulingPolicy);
		}
						
		
		if(jsonObj.has("bookingFee"))
			availableTicket.setBookingFee(jsonObj.getDouble("bookingFee"));
		if(jsonObj.has("busType"))
			availableTicket.setBusType(jsonObj.getString("busType"));
		if(jsonObj.has("cancellationPolicy"))
			availableTicket.setCancellationPolicy(jsonObj.getString("cancellationPolicy"));
		if(jsonObj.has("dateOfIssue"))
			availableTicket.setDateOfIssue(jsonObj.getString("dateOfIssue") );
		if(jsonObj.has("destinationCityId"))
			availableTicket.setDestinationCityId(jsonObj.getLong("destinationCityId"));
		if(jsonObj.has("doj"))
			availableTicket.setDoj( jsonObj.getString("doj"));
		if(jsonObj.has("inventoryId"))
			availableTicket.setInventoryId(jsonObj.getLong("inventoryId") );
		if(jsonObj.has("dropLocationId"))
			availableTicket.setDropLocationId(jsonObj.getLong("dropLocationId") );
		if(jsonObj.has("dropLocation"))
			availableTicket.setDropLocation(jsonObj.getString("dropLocation"));
		if(jsonObj.has("dropLocationAddress"))
			availableTicket.setDropLocationAddress(jsonObj.getString("dropLocationAddress"));
		if(jsonObj.has("dropLocationLandmark"))
			availableTicket.setDropLocationLandmark(jsonObj.getString("dropLocationLandmark"));
		if(jsonObj.has("dropTime"))
			availableTicket.setDropTime(jsonObj.getLong("dropTime"));
		if(jsonObj.has("hasRTCBreakup"))
			availableTicket.setHasRTCBreakup(jsonObj.getString("hasRTCBreakup") );
		if(jsonObj.has("hasSpecialTemplate"))
			availableTicket.setHasSpecialTemplate(jsonObj.getString("hasSpecialTemplate"));
		if(jsonObj.has("MTicketEnabled"))
			availableTicket.setmTicketEnabled(jsonObj.getString("MTicketEnabled")) ;
		if(jsonObj.has("partialCancellationAllowed"))
			availableTicket.setPartialCancellationAllowed(jsonObj.getString("partialCancellationAllowed"));
		if(jsonObj.has("pickUpContactNo"))
			availableTicket.setPickUpContactNo(jsonObj.getString("pickUpContactNo") );
		if(jsonObj.has("pickUpLocationAddress"))
			availableTicket.setPickUpLocationAddress(jsonObj.getString("pickUpLocationAddress"));
		if(jsonObj.has("pickupLocationId"))
			availableTicket.setPickupLocationId(jsonObj.getLong("pickupLocationId"));
		if(jsonObj.has("pickupLocation"))
			availableTicket.setPickupLocation(jsonObj.getString("pickupLocation"));
		if(jsonObj.has("pickupLocationLandmark"))
			availableTicket.setPickupLocationLandmark( jsonObj.getString("pickupLocationLandmark"));
		if(jsonObj.has("pickupTime"))
			availableTicket.setPickupTime(jsonObj.getLong("pickupTime"));
		if(jsonObj.has("pnr"))
			availableTicket.setPnr(jsonObj.getString("pnr"));
		if(jsonObj.has("primeDepartureTime"))
			availableTicket.setPrimeDepartureTime(jsonObj.getLong("primeDepartureTime"));		
		if(jsonObj.has("serviceCharge"))
			availableTicket.setServiceCharge(jsonObj.getDouble("serviceCharge") );
		if(jsonObj.has("sourceCity"))
			availableTicket.setSourceCity(jsonObj.getString("sourceCity"));
		if(jsonObj.has("sourceCityId"))
			availableTicket.setSourceCityId(jsonObj.getLong("sourceCityId") );
		if(jsonObj.has("status"))
			availableTicket.setStatus(jsonObj.getString("status"));
		if(jsonObj.has("tin"))
			availableTicket.setTin(jsonObj.getString("tin"));
		if(jsonObj.has("travels"))
			availableTicket.setTravels(jsonObj.getString("travels") );
		if(jsonObj.has("refundAmount"))
			availableTicket.setRefundAmount(jsonObj.getDouble("refundAmount"));
		if(jsonObj.has("cancellationCharges"))
			availableTicket.setCancellationCharges( jsonObj.getDouble("cancellationCharges"));
		if(jsonObj.has("dateOfCancellation"))
			availableTicket.setDateOfCancellation(jsonObj.getString("dateOfCancellation"));
		if(jsonObj.has("destinationCity"))
			availableTicket.setDestinationCity(jsonObj.getString("destinationCity"));
		if(jsonObj.has("tripCode"))
			availableTicket.setTripCode(jsonObj.getString("tripCode"));
		if(jsonObj.has("uidNumber"))
			availableTicket.setUidNumber(jsonObj.getString("uidNumber"));
		if(jsonObj.has("asnFare"))
			availableTicket.setAsnFare(jsonObj.getDouble("asnFare"));
		if(jsonObj.has("serviceStartTime"))
			availableTicket.setServiceStartTime(jsonObj.getString("serviceStartTime"));
		if(jsonObj.has("arrivalTime"))
			availableTicket.setArrivalTime(jsonObj.getString("arrivalTime"));
		if(jsonObj.has("departureTime"))
			availableTicket.setDepartureTime(jsonObj.getString("departureTime"));
		if(jsonObj.has("serviceNo"))
			availableTicket.setServiceNo(jsonObj.getString("serviceNo"));
		if(jsonObj.has("routeNo"))
			availableTicket.setRouteNo(jsonObj.getString("routeNo"));
		if(jsonObj.has("corpCode"))
			availableTicket.setCorpCode(jsonObj.getString("corpCode"));
		if(jsonObj.has("otgPolicy"))
			availableTicket.setOtgPolicy(jsonObj.getString("otgPolicy"));
		
		return availableTicket;
	}

	public static CancellationData getCancellationDataParser(String responseString) {
		CancellationData cancellationData = new CancellationData();
		JSONObject jsonObj = new JSONObject(responseString);
		
		
		if(jsonObj.has("cancellationCharges")){
			
			List<Entry> entryList = new ArrayList<Entry>();
			CommonData cancellationCharges = new CommonData();
			JSONObject cancellationChargesObject = jsonObj.getJSONObject("cancellationCharges");
			if(cancellationChargesObject.has("entry")){
				Object entryObject = cancellationChargesObject.get("entry");
				if (entryObject instanceof JSONArray) {
					JSONArray entryArray = cancellationChargesObject.getJSONArray("entry");
					for (int ent = 0; ent <entryArray.length(); ent++) {
						JSONObject entryDetailsData = entryArray.getJSONObject(ent);
						Entry entry = new Entry();
						if(entryDetailsData.has("key"))
							entry.setKey(entryDetailsData.getString("key"));
						if(entryDetailsData.has("value"))
							entry.setValue(entryDetailsData.getDouble("value"));
						entryList.add(entry);
					}
			
			    }else{
			    	JSONObject entryDetailsData = cancellationChargesObject.getJSONObject("entry");
			    	Entry entry = new Entry();
			    	if(entryDetailsData.has("key"))
						entry.setKey(entryDetailsData.getString("key"));
					if(entryDetailsData.has("value"))
						entry.setValue(entryDetailsData.getDouble("value"));
					entryList.add(entry);
			    }
		
		    }
			cancellationCharges.setEntry(entryList);
			cancellationData.setCancellationCharges(cancellationCharges);
		}
		
		if(jsonObj.has("fares")){
			List<Entry> entryList = new ArrayList<Entry>();
			CommonData fares = new CommonData();
			JSONObject faresObject = jsonObj.getJSONObject("fares");
			if(faresObject.has("entry")){
				Object entryObject = faresObject.get("entry");
				if (entryObject instanceof JSONArray) {
					JSONArray entryArray = faresObject.getJSONArray("entry");
					for (int ent = 0; ent <entryArray.length(); ent++) {
						JSONObject entryDetailsData = entryArray.getJSONObject(ent);
						Entry entry = new Entry();
						if(entryDetailsData.has("key"))
							entry.setKey(entryDetailsData.getString("key"));
						if(entryDetailsData.has("value"))
							entry.setValue(entryDetailsData.getDouble("value"));
						entryList.add(entry);
					}
			
			    }else{
			    	JSONObject entryDetailsData = faresObject.getJSONObject("entry");
			    	Entry entry = new Entry();
			    	if(entryDetailsData.has("key"))
						entry.setKey(entryDetailsData.getString("key"));
					if(entryDetailsData.has("value"))
						entry.setValue(entryDetailsData.getDouble("value"));
					entryList.add(entry);
			    }
		
		    }
			fares.setEntry(entryList);
			cancellationData.setFares(fares);
		 }
		if(jsonObj.has("cancellable"))
			cancellationData.setCancellable(jsonObj.getString("cancellable"));
		if(jsonObj.has("freeCancellationTime"))
			cancellationData.setFreeCancellationTime(jsonObj.getString("freeCancellationTime"));
		if(jsonObj.has("partiallyCancellable"))
			cancellationData.setPartiallyCancellable(jsonObj.getString("partiallyCancellable"));
		if(jsonObj.has("serviceCharge"))
			cancellationData.setServiceCharge(jsonObj.getDouble("serviceCharge"));
		if(jsonObj.has("tatkalTime"))
			cancellationData.setTatkalTime(jsonObj.getString("tatkalTime"));
		
		return cancellationData;
	 
	}

	public static BoardingDroppingTimes getBoardingPointDetailsParser(String responseString) {
		BoardingDroppingTimes  boardingDetails = new BoardingDroppingTimes();
		JSONObject jsonObj = new JSONObject(responseString);
		
		if(jsonObj.has("address"))
			boardingDetails.setAddress(jsonObj.getString("address"));
		if(jsonObj.has("contactnumber"))
			boardingDetails.setContactNumber(jsonObj.getString("contactnumber"));
		if(jsonObj.has("id"))
			boardingDetails.setBpId(jsonObj.getLong("id"));
		if(jsonObj.has("landmark"))
			boardingDetails.setLandmark(jsonObj.getString("landmark"));
		if(jsonObj.has("locationName"))
			boardingDetails.setLocation(jsonObj.getString("locationName"));
		if(jsonObj.has("name"))
			boardingDetails.setBpName(jsonObj.getString("name"));
		if(jsonObj.has("rbMasterId"))
			boardingDetails.setRbMasterId(jsonObj.getString("rbMasterId"));												
		
		return boardingDetails;
	}
	
	
	
}
