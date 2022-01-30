package com.skyflight.customquery;



public class CustomSqlQueryFlight {
	
	/*----------------------------MarkupRelated Query---------------------------------*/
	public static String getAllDomesticMarkup ="select flight_markup.*,airline.airline_name from flight_markup inner join airline on flight_markup.airline_code=airline.airline_code where flight_markup.username=:username and airline.service_type=:service_type";


}
