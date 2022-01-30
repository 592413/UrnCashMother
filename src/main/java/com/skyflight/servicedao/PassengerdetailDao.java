package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.PassengerDetail;



public interface PassengerdetailDao {

	public List<PassengerDetail> getAllPassengerDetail();

	public PassengerDetail getPassengerDetailById(String passengerId);

	public boolean insertPassengerDetail(PassengerDetail passenger);

	public boolean updatePassengerDetail(PassengerDetail passenger);

	public void deletePassengerDetail(int passengerId);
	
	public boolean updatePassengerDetailbyairlinepnrAndTicketNumber(String airlinepnr,String ticketnumber); 

	public List<PassengerDetail> getPassengerDetailByNamedQuery(String query, Map<String, Object> param);






}
