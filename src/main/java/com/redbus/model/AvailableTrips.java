package com.redbus.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableTrips implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("AC")
	private String isAC;
	@JsonProperty("SSAgentAccount")
	private String isAgentAccount;
	private long id;
	private long arrivalTime;
	private long availableSeats;
	private String bookable;
	private long avlWindowSeats;
	private List<BoardingDroppingTimes> boardingTimes;
    private List<BoardingDroppingTimes> droppingTimes; 
    private List<FareDetails> fareDetails; 
    private String bpDpSeatLayout; 
    private long busImageCount;	
    private long busTypeId;
    private String busType;
    private String busRoutes;	
    private String cancellationPolicy;	
    private long  departureTime;
    private String dropPointMandatory;	
    private List<Double> fares;		
    private long operator;	
    private String otgEnabled;	
    private String partialCancellationAllowed;	
    private String rtc;	
    private String seater;	
    private long maxSeatsPerTicket;		
    private double zeroCancellationTime;		
    private String mTicketEnabled;	
    private String travels;	
    private String availSrCitizen;	
    private String availCatCard;	
    private String idProofRequired;	
    private String liveTrackingAvailable;	
    private String nonAC;	
    private String primaryPaxCancellable;		
    private long routeId;		
    private String singleLadies;	
    private String sleeper;		
    private String vehicleType;
    private String additionalCommission;
    private double agentServiceCharge;
    private String agentServiceChargeAllowed;
    private double boCommission;
    private String busServiceId;
    private String cpId;
    private String destination;
    private String doj;
    private String duration;
    private String exactSearch;
    private String flatComApplicable;
    private String flatSSComApplicable;
    private String gdsCommission;
    private String nextDay;
    private String otgPolicy;
    private double partnerBaseCommission;
    private String selfInventory;
    private String socialDistancing;
    private String source;
    private String tatkalTime;
    private String unAvailable;
    private String viaRoutes;
	public String getIsAC() {
		return isAC;
	}
	public String getIsAgentAccount() {
		return isAgentAccount;
	}
	public long getId() {
		return id;
	}
	public long getArrivalTime() {
		return arrivalTime;
	}
	public long getAvailableSeats() {
		return availableSeats;
	}
	public String getBookable() {
		return bookable;
	}
	public long getAvlWindowSeats() {
		return avlWindowSeats;
	}
	public List<BoardingDroppingTimes> getBoardingTimes() {
		return boardingTimes;
	}
	public List<BoardingDroppingTimes> getDroppingTimes() {
		return droppingTimes;
	}
	public List<FareDetails> getFareDetails() {
		return fareDetails;
	}
	public String getBpDpSeatLayout() {
		return bpDpSeatLayout;
	}
	public long getBusImageCount() {
		return busImageCount;
	}
	public long getBusTypeId() {
		return busTypeId;
	}
	public String getBusType() {
		return busType;
	}
	public String getBusRoutes() {
		return busRoutes;
	}
	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
	public long getDepartureTime() {
		return departureTime;
	}
	public String getDropPointMandatory() {
		return dropPointMandatory;
	}
	public List<Double> getFares() {
		return fares;
	}
	public long getOperator() {
		return operator;
	}
	public String getOtgEnabled() {
		return otgEnabled;
	}
	public String getPartialCancellationAllowed() {
		return partialCancellationAllowed;
	}
	public String getRtc() {
		return rtc;
	}
	public String getSeater() {
		return seater;
	}
	public long getMaxSeatsPerTicket() {
		return maxSeatsPerTicket;
	}
	public double getZeroCancellationTime() {
		return zeroCancellationTime;
	}
	public String getmTicketEnabled() {
		return mTicketEnabled;
	}
	public String getTravels() {
		return travels;
	}
	public String getAvailSrCitizen() {
		return availSrCitizen;
	}
	public String getAvailCatCard() {
		return availCatCard;
	}
	public String getIdProofRequired() {
		return idProofRequired;
	}
	public String getLiveTrackingAvailable() {
		return liveTrackingAvailable;
	}
	public String getNonAC() {
		return nonAC;
	}
	public String getPrimaryPaxCancellable() {
		return primaryPaxCancellable;
	}
	public long getRouteId() {
		return routeId;
	}
	public String getSingleLadies() {
		return singleLadies;
	}
	public String getSleeper() {
		return sleeper;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public String getAdditionalCommission() {
		return additionalCommission;
	}
	public double getAgentServiceCharge() {
		return agentServiceCharge;
	}
	public String getAgentServiceChargeAllowed() {
		return agentServiceChargeAllowed;
	}
	public double getBoCommission() {
		return boCommission;
	}
	public String getBusServiceId() {
		return busServiceId;
	}
	public String getCpId() {
		return cpId;
	}
	public String getDestination() {
		return destination;
	}
	public String getDoj() {
		return doj;
	}
	public String getDuration() {
		return duration;
	}
	public String getExactSearch() {
		return exactSearch;
	}
	public String getFlatComApplicable() {
		return flatComApplicable;
	}
	public String getFlatSSComApplicable() {
		return flatSSComApplicable;
	}
	public String getGdsCommission() {
		return gdsCommission;
	}
	public String getNextDay() {
		return nextDay;
	}
	public String getOtgPolicy() {
		return otgPolicy;
	}
	public double getPartnerBaseCommission() {
		return partnerBaseCommission;
	}
	public String getSelfInventory() {
		return selfInventory;
	}
	public String getSocialDistancing() {
		return socialDistancing;
	}
	public String getSource() {
		return source;
	}
	public String getTatkalTime() {
		return tatkalTime;
	}
	public String getUnAvailable() {
		return unAvailable;
	}
	public String getViaRoutes() {
		return viaRoutes;
	}
	public void setIsAC(String isAC) {
		this.isAC = isAC;
	}
	public void setIsAgentAccount(String isAgentAccount) {
		this.isAgentAccount = isAgentAccount;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setAvailableSeats(long availableSeats) {
		this.availableSeats = availableSeats;
	}
	public void setBookable(String bookable) {
		this.bookable = bookable;
	}
	public void setAvlWindowSeats(long avlWindowSeats) {
		this.avlWindowSeats = avlWindowSeats;
	}
	public void setBoardingTimes(List<BoardingDroppingTimes> boardingTimes) {
		this.boardingTimes = boardingTimes;
	}
	public void setDroppingTimes(List<BoardingDroppingTimes> droppingTimes) {
		this.droppingTimes = droppingTimes;
	}
	public void setFareDetails(List<FareDetails> fareDetails) {
		this.fareDetails = fareDetails;
	}
	public void setBpDpSeatLayout(String bpDpSeatLayout) {
		this.bpDpSeatLayout = bpDpSeatLayout;
	}
	public void setBusImageCount(long busImageCount) {
		this.busImageCount = busImageCount;
	}
	public void setBusTypeId(long busTypeId) {
		this.busTypeId = busTypeId;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public void setBusRoutes(String busRoutes) {
		this.busRoutes = busRoutes;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}
	public void setDropPointMandatory(String dropPointMandatory) {
		this.dropPointMandatory = dropPointMandatory;
	}
	public void setFares(List<Double> fares) {
		this.fares = fares;
	}
	public void setOperator(long operator) {
		this.operator = operator;
	}
	public void setOtgEnabled(String otgEnabled) {
		this.otgEnabled = otgEnabled;
	}
	public void setPartialCancellationAllowed(String partialCancellationAllowed) {
		this.partialCancellationAllowed = partialCancellationAllowed;
	}
	public void setRtc(String rtc) {
		this.rtc = rtc;
	}
	public void setSeater(String seater) {
		this.seater = seater;
	}
	public void setMaxSeatsPerTicket(long maxSeatsPerTicket) {
		this.maxSeatsPerTicket = maxSeatsPerTicket;
	}
	public void setZeroCancellationTime(double zeroCancellationTime) {
		this.zeroCancellationTime = zeroCancellationTime;
	}
	public void setmTicketEnabled(String mTicketEnabled) {
		this.mTicketEnabled = mTicketEnabled;
	}
	public void setTravels(String travels) {
		this.travels = travels;
	}
	public void setAvailSrCitizen(String availSrCitizen) {
		this.availSrCitizen = availSrCitizen;
	}
	public void setAvailCatCard(String availCatCard) {
		this.availCatCard = availCatCard;
	}
	public void setIdProofRequired(String idProofRequired) {
		this.idProofRequired = idProofRequired;
	}
	public void setLiveTrackingAvailable(String liveTrackingAvailable) {
		this.liveTrackingAvailable = liveTrackingAvailable;
	}
	public void setNonAC(String nonAC) {
		this.nonAC = nonAC;
	}
	public void setPrimaryPaxCancellable(String primaryPaxCancellable) {
		this.primaryPaxCancellable = primaryPaxCancellable;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public void setSingleLadies(String singleLadies) {
		this.singleLadies = singleLadies;
	}
	public void setSleeper(String sleeper) {
		this.sleeper = sleeper;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public void setAdditionalCommission(String additionalCommission) {
		this.additionalCommission = additionalCommission;
	}
	public void setAgentServiceCharge(double agentServiceCharge) {
		this.agentServiceCharge = agentServiceCharge;
	}
	public void setAgentServiceChargeAllowed(String agentServiceChargeAllowed) {
		this.agentServiceChargeAllowed = agentServiceChargeAllowed;
	}
	public void setBoCommission(double boCommission) {
		this.boCommission = boCommission;
	}
	public void setBusServiceId(String busServiceId) {
		this.busServiceId = busServiceId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setExactSearch(String exactSearch) {
		this.exactSearch = exactSearch;
	}
	public void setFlatComApplicable(String flatComApplicable) {
		this.flatComApplicable = flatComApplicable;
	}
	public void setFlatSSComApplicable(String flatSSComApplicable) {
		this.flatSSComApplicable = flatSSComApplicable;
	}
	public void setGdsCommission(String gdsCommission) {
		this.gdsCommission = gdsCommission;
	}
	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
	}
	public void setOtgPolicy(String otgPolicy) {
		this.otgPolicy = otgPolicy;
	}
	public void setPartnerBaseCommission(double partnerBaseCommission) {
		this.partnerBaseCommission = partnerBaseCommission;
	}
	public void setSelfInventory(String selfInventory) {
		this.selfInventory = selfInventory;
	}
	public void setSocialDistancing(String socialDistancing) {
		this.socialDistancing = socialDistancing;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setTatkalTime(String tatkalTime) {
		this.tatkalTime = tatkalTime;
	}
	public void setUnAvailable(String unAvailable) {
		this.unAvailable = unAvailable;
	}
	public void setViaRoutes(String viaRoutes) {
		this.viaRoutes = viaRoutes;
	}
	@Override
	public String toString() {
		return "AvailableTrips [isAC=" + isAC + ", isAgentAccount=" + isAgentAccount + ", id=" + id + ", arrivalTime="
				+ arrivalTime + ", availableSeats=" + availableSeats + ", bookable=" + bookable + ", avlWindowSeats="
				+ avlWindowSeats + ", boardingTimes=" + boardingTimes + ", droppingTimes=" + droppingTimes
				+ ", fareDetails=" + fareDetails + ", bpDpSeatLayout=" + bpDpSeatLayout + ", busImageCount="
				+ busImageCount + ", busTypeId=" + busTypeId + ", busType=" + busType + ", busRoutes=" + busRoutes
				+ ", cancellationPolicy=" + cancellationPolicy + ", departureTime=" + departureTime
				+ ", dropPointMandatory=" + dropPointMandatory + ", fares=" + fares + ", operator=" + operator
				+ ", otgEnabled=" + otgEnabled + ", partialCancellationAllowed=" + partialCancellationAllowed + ", rtc="
				+ rtc + ", seater=" + seater + ", maxSeatsPerTicket=" + maxSeatsPerTicket + ", zeroCancellationTime="
				+ zeroCancellationTime + ", mTicketEnabled=" + mTicketEnabled + ", travels=" + travels
				+ ", availSrCitizen=" + availSrCitizen + ", availCatCard=" + availCatCard + ", idProofRequired="
				+ idProofRequired + ", liveTrackingAvailable=" + liveTrackingAvailable + ", nonAC=" + nonAC
				+ ", primaryPaxCancellable=" + primaryPaxCancellable + ", routeId=" + routeId + ", singleLadies="
				+ singleLadies + ", sleeper=" + sleeper + ", vehicleType=" + vehicleType + ", additionalCommission="
				+ additionalCommission + ", agentServiceCharge=" + agentServiceCharge + ", agentServiceChargeAllowed="
				+ agentServiceChargeAllowed + ", boCommission=" + boCommission + ", busServiceId=" + busServiceId
				+ ", cpId=" + cpId + ", destination=" + destination + ", doj=" + doj + ", duration=" + duration
				+ ", exactSearch=" + exactSearch + ", flatComApplicable=" + flatComApplicable + ", flatSSComApplicable="
				+ flatSSComApplicable + ", gdsCommission=" + gdsCommission + ", nextDay=" + nextDay + ", otgPolicy="
				+ otgPolicy + ", partnerBaseCommission=" + partnerBaseCommission + ", selfInventory=" + selfInventory
				+ ", socialDistancing=" + socialDistancing + ", source=" + source + ", tatkalTime=" + tatkalTime
				+ ", unAvailable=" + unAvailable + ", viaRoutes=" + viaRoutes + "]";
	}
   
	
	
	
   
}
