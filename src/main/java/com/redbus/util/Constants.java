package com.redbus.util;

public class Constants {
	public final static String BASE_URL = "http://api.seatseller.travel/";
	public final static String CONSUMER_SECRET="za89GTQXEg2BnwG2bN94SrKNFhIlhr";
	public final static String CONSUMER_KEY="Sp5sTNIBA1Vbi0brnDOKvWMFtjXQuu";
	public final static String GET_CITIES = "cities";
	public final static String GET_ALIASES = "aliases";
	public final static String GET_AVAILABLE_TRIPS= "availabletrips?source={1}&destination={2}&doj={3}";
	public final static String GET_TRIP_DETAILS= "tripdetails?id={1}";
	public final static String GET_TRIP_DETAILS_V2= "tripdetailsV2";
	public final static String BLOCK_TICKET = "blockTicket";
	public final static String GET_RTC_FARE_BREAKUP= "rtcfarebreakup?blockKey={1}";
	public final static String CONFIRM_TICKET= "bookticket?blockKey={1}";
	public final static String CANCELLATION_DATA= "cancellationdata?tin={1}";
	public final static String CANCEL_TICKET= "cancelticket";
	public final static String GET_TICKET_DETAILS= "ticket?tin={1}";
	public final static String CHECK_BOOKED_TICKET_DETAILS= "checkBookedTicket?blockKey={1}";
	public final static String GET_BUS_CANCELLATION_INFO="busCancellationInfo?from={1}&to={2}";
	public final static String GET_BUS_CANCELLATION_INFO_WITHOUT_DATE_RANGE="busCancellationInfo";
	public final static String GET_BORDING_PONT_DETAILS="boardingPoint?id={1}&tripId={2}";

}
