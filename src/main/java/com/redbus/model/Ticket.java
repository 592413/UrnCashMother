package com.redbus.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket implements Serializable {

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private double bookingFee;
		private String busType;
	    private String cancellationPolicy;
	    private String dateOfIssue;
	    private long destinationCityId;
	    private String doj;
	    private long inventoryId;
	    private long dropLocationId;
	    private String dropLocation;
	    private String dropLocationAddress;
	    private String dropLocationLandmark;
	    private long dropTime;
	    private String hasRTCBreakup;
	    private String hasSpecialTemplate;
	    private List<InventoryItem> inventoryItems;
		@JsonProperty("MTicketEnabled")
	    private String mTicketEnabled;
		private String partialCancellationAllowed;
		private String pickUpContactNo;
		private String pickUpLocationAddress;
		private long pickupLocationId;
		private String pickupLocation;
		private String pickupLocationLandmark;
		private long pickupTime;
		private String pnr;
	    private long primeDepartureTime;
	    private ReschedulingPolicy reschedulingPolicy;
	    private double serviceCharge;
	    private String sourceCity;
	    private long sourceCityId;
	    private String status;
		private String tin;
	   	private String travels;
	   	private double refundAmount;
	    private double cancellationCharges;
	    private String dateOfCancellation;
	    private String destinationCity;
	    private String tripCode;
	    private String uidNumber;
	    private double asnFare;
	    private String serviceStartTime;
	    private String arrivalTime;
	    private String departureTime;
	    private String serviceNo;
	    private String routeNo;
	    private String corpCode;
	    private String otgPolicy;
		
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public double getBookingFee() {
			return bookingFee;
		}

		public String getBusType() {
			return busType;
		}

		public String getCancellationPolicy() {
			return cancellationPolicy;
		}

		public String getDateOfIssue() {
			return dateOfIssue;
		}

		public long getDestinationCityId() {
			return destinationCityId;
		}

		public String getDoj() {
			return doj;
		}

		public long getInventoryId() {
			return inventoryId;
		}

		public long getDropLocationId() {
			return dropLocationId;
		}

		public String getDropLocation() {
			return dropLocation;
		}

		public String getDropLocationAddress() {
			return dropLocationAddress;
		}

		public String getDropLocationLandmark() {
			return dropLocationLandmark;
		}

		public long getDropTime() {
			return dropTime;
		}

		public String getHasRTCBreakup() {
			return hasRTCBreakup;
		}

		public String getHasSpecialTemplate() {
			return hasSpecialTemplate;
		}

		public List<InventoryItem> getInventoryItems() {
			return inventoryItems;
		}

		public String getmTicketEnabled() {
			return mTicketEnabled;
		}

		public String getPartialCancellationAllowed() {
			return partialCancellationAllowed;
		}

		public String getPickUpContactNo() {
			return pickUpContactNo;
		}

		public String getPickUpLocationAddress() {
			return pickUpLocationAddress;
		}

		public long getPickupLocationId() {
			return pickupLocationId;
		}

		public String getPickupLocation() {
			return pickupLocation;
		}

		public String getPickupLocationLandmark() {
			return pickupLocationLandmark;
		}

		public long getPickupTime() {
			return pickupTime;
		}

		public String getPnr() {
			return pnr;
		}

		public long getPrimeDepartureTime() {
			return primeDepartureTime;
		}

		public ReschedulingPolicy getReschedulingPolicy() {
			return reschedulingPolicy;
		}

		public double getServiceCharge() {
			return serviceCharge;
		}

		public String getSourceCity() {
			return sourceCity;
		}

		public long getSourceCityId() {
			return sourceCityId;
		}

		public String getStatus() {
			return status;
		}

		public String getTin() {
			return tin;
		}

		public String getTravels() {
			return travels;
		}

		public double getRefundAmount() {
			return refundAmount;
		}

		public double getCancellationCharges() {
			return cancellationCharges;
		}

		public String getDateOfCancellation() {
			return dateOfCancellation;
		}

		public String getDestinationCity() {
			return destinationCity;
		}

		public String getTripCode() {
			return tripCode;
		}

		public String getUidNumber() {
			return uidNumber;
		}

		public double getAsnFare() {
			return asnFare;
		}

		public String getServiceStartTime() {
			return serviceStartTime;
		}

		public String getArrivalTime() {
			return arrivalTime;
		}

		public String getDepartureTime() {
			return departureTime;
		}

		public String getServiceNo() {
			return serviceNo;
		}

		public String getRouteNo() {
			return routeNo;
		}

		public String getCorpCode() {
			return corpCode;
		}

		public String getOtgPolicy() {
			return otgPolicy;
		}

		public void setBookingFee(double bookingFee) {
			this.bookingFee = bookingFee;
		}

		public void setBusType(String busType) {
			this.busType = busType;
		}

		public void setCancellationPolicy(String cancellationPolicy) {
			this.cancellationPolicy = cancellationPolicy;
		}

		public void setDateOfIssue(String dateOfIssue) {
			this.dateOfIssue = dateOfIssue;
		}

		public void setDestinationCityId(long destinationCityId) {
			this.destinationCityId = destinationCityId;
		}

		public void setDoj(String doj) {
			this.doj = doj;
		}

		public void setInventoryId(long inventoryId) {
			this.inventoryId = inventoryId;
		}

		public void setDropLocationId(long dropLocationId) {
			this.dropLocationId = dropLocationId;
		}

		public void setDropLocation(String dropLocation) {
			this.dropLocation = dropLocation;
		}

		public void setDropLocationAddress(String dropLocationAddress) {
			this.dropLocationAddress = dropLocationAddress;
		}

		public void setDropLocationLandmark(String dropLocationLandmark) {
			this.dropLocationLandmark = dropLocationLandmark;
		}

		public void setDropTime(long dropTime) {
			this.dropTime = dropTime;
		}

		public void setHasRTCBreakup(String hasRTCBreakup) {
			this.hasRTCBreakup = hasRTCBreakup;
		}

		public void setHasSpecialTemplate(String hasSpecialTemplate) {
			this.hasSpecialTemplate = hasSpecialTemplate;
		}

		public void setInventoryItems(List<InventoryItem> inventoryItems) {
			this.inventoryItems = inventoryItems;
		}

		public void setmTicketEnabled(String mTicketEnabled) {
			this.mTicketEnabled = mTicketEnabled;
		}

		public void setPartialCancellationAllowed(String partialCancellationAllowed) {
			this.partialCancellationAllowed = partialCancellationAllowed;
		}

		public void setPickUpContactNo(String pickUpContactNo) {
			this.pickUpContactNo = pickUpContactNo;
		}

		public void setPickUpLocationAddress(String pickUpLocationAddress) {
			this.pickUpLocationAddress = pickUpLocationAddress;
		}

		public void setPickupLocationId(long pickupLocationId) {
			this.pickupLocationId = pickupLocationId;
		}

		public void setPickupLocation(String pickupLocation) {
			this.pickupLocation = pickupLocation;
		}

		public void setPickupLocationLandmark(String pickupLocationLandmark) {
			this.pickupLocationLandmark = pickupLocationLandmark;
		}

		public void setPickupTime(long pickupTime) {
			this.pickupTime = pickupTime;
		}

		public void setPnr(String pnr) {
			this.pnr = pnr;
		}

		public void setPrimeDepartureTime(long primeDepartureTime) {
			this.primeDepartureTime = primeDepartureTime;
		}

		public void setReschedulingPolicy(ReschedulingPolicy reschedulingPolicy) {
			this.reschedulingPolicy = reschedulingPolicy;
		}

		public void setServiceCharge(double serviceCharge) {
			this.serviceCharge = serviceCharge;
		}

		public void setSourceCity(String sourceCity) {
			this.sourceCity = sourceCity;
		}

		public void setSourceCityId(long sourceCityId) {
			this.sourceCityId = sourceCityId;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setTin(String tin) {
			this.tin = tin;
		}

		public void setTravels(String travels) {
			this.travels = travels;
		}

		public void setRefundAmount(double refundAmount) {
			this.refundAmount = refundAmount;
		}

		public void setCancellationCharges(double cancellationCharges) {
			this.cancellationCharges = cancellationCharges;
		}

		public void setDateOfCancellation(String dateOfCancellation) {
			this.dateOfCancellation = dateOfCancellation;
		}

		public void setDestinationCity(String destinationCity) {
			this.destinationCity = destinationCity;
		}

		public void setTripCode(String tripCode) {
			this.tripCode = tripCode;
		}

		public void setUidNumber(String uidNumber) {
			this.uidNumber = uidNumber;
		}

		public void setAsnFare(double asnFare) {
			this.asnFare = asnFare;
		}

		public void setServiceStartTime(String serviceStartTime) {
			this.serviceStartTime = serviceStartTime;
		}

		public void setArrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		public void setDepartureTime(String departureTime) {
			this.departureTime = departureTime;
		}

		public void setServiceNo(String serviceNo) {
			this.serviceNo = serviceNo;
		}

		public void setRouteNo(String routeNo) {
			this.routeNo = routeNo;
		}

		public void setCorpCode(String corpCode) {
			this.corpCode = corpCode;
		}

		public void setOtgPolicy(String otgPolicy) {
			this.otgPolicy = otgPolicy;
		}

		@Override
		public String toString() {
			return "Ticket [bookingFee=" + bookingFee + ", busType=" + busType + ", cancellationPolicy="
					+ cancellationPolicy + ", dateOfIssue=" + dateOfIssue + ", destinationCityId=" + destinationCityId
					+ ", doj=" + doj + ", inventoryId=" + inventoryId + ", dropLocationId=" + dropLocationId
					+ ", dropLocation=" + dropLocation + ", dropLocationAddress=" + dropLocationAddress
					+ ", dropLocationLandmark=" + dropLocationLandmark + ", dropTime=" + dropTime + ", hasRTCBreakup="
					+ hasRTCBreakup + ", hasSpecialTemplate=" + hasSpecialTemplate + ", inventoryItems="
					+ inventoryItems + ", mTicketEnabled=" + mTicketEnabled + ", partialCancellationAllowed="
					+ partialCancellationAllowed + ", pickUpContactNo=" + pickUpContactNo + ", pickUpLocationAddress="
					+ pickUpLocationAddress + ", pickupLocationId=" + pickupLocationId + ", pickupLocation="
					+ pickupLocation + ", pickupLocationLandmark=" + pickupLocationLandmark + ", pickupTime="
					+ pickupTime + ", pnr=" + pnr + ", primeDepartureTime=" + primeDepartureTime
					+ ", reschedulingPolicy=" + reschedulingPolicy + ", serviceCharge=" + serviceCharge
					+ ", sourceCity=" + sourceCity + ", sourceCityId=" + sourceCityId + ", status=" + status + ", tin="
					+ tin + ", travels=" + travels + ", refundAmount=" + refundAmount + ", cancellationCharges="
					+ cancellationCharges + ", dateOfCancellation=" + dateOfCancellation + ", destinationCity="
					+ destinationCity + ", tripCode=" + tripCode + ", uidNumber=" + uidNumber + ", asnFare=" + asnFare
					+ ", serviceStartTime=" + serviceStartTime + ", arrivalTime=" + arrivalTime + ", departureTime="
					+ departureTime + ", serviceNo=" + serviceNo + ", routeNo=" + routeNo + ", corpCode=" + corpCode
					+ ", otgPolicy=" + otgPolicy + "]";
		}
	  
	   
	   
	    
	    
	    
}
