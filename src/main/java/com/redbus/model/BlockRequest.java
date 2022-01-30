package com.redbus.model;

import java.io.Serializable;
import java.util.List;

public class BlockRequest implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long availableTripId;
	private long boardingPointId;
    private long droppingPointId;
    private long destination;
    private long source;
    private List<InventoryItem> inventoryItems;
	public long getAvailableTripId() {
		return availableTripId;
	}
	public void setAvailableTripId(long availableTripId) {
		this.availableTripId = availableTripId;
	}
	public long getBoardingPointId() {
		return boardingPointId;
	}
	public void setBoardingPointId(long boardingPointId) {
		this.boardingPointId = boardingPointId;
	}
	public long getDroppingPointId() {
		return droppingPointId;
	}
	public void setDroppingPointId(long droppingPointId) {
		this.droppingPointId = droppingPointId;
	}
	public long getDestination() {
		return destination;
	}
	public void setDestination(long destination) {
		this.destination = destination;
	}
	public long getSource() {
		return source;
	}
	public void setSource(long source) {
		this.source = source;
	}
	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}
	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
	@Override
	public String toString() {
		return "BlockRequest [availableTripId=" + availableTripId + ", boardingPointId=" + boardingPointId
				+ ", droppingPointId=" + droppingPointId + ", destination=" + destination + ", source=" + source
				+ ", inventoryItems=" + inventoryItems + "]";
	}
   
    
    

}
