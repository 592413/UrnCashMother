package com.skyflight.BookingCustomModel;

import java.util.List;
import com.skyflight.MealBaggagesCustomModel.Baggages;

public class PassengerDetails {
	private String PassengerType;
	private String Title;
	private boolean IsLeadPax;
	private String FirstName;
	private String Gender;
	private String LastName;
	private String Email;
	private String City;
	private String Mobile;
	private String PaxNumber ;
	private String DateofBirth;
	private String PassportNo;
	private String PassportExpiry;
	private List<Seat> Seat;
	private List<Meal> Meal;
	private List<Baggages> Baggages;
	
	public String getPassengerType() {
		return PassengerType;
	}
	public void setPassengerType(String passengerType) {
		PassengerType = passengerType;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public boolean isIsLeadPax() {
		return IsLeadPax;
	}
	public void setIsLeadPax(boolean isLeadPax) {
		IsLeadPax = isLeadPax;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getPaxNumber() {
		return PaxNumber;
	}
	public void setPaxNumber(String paxNumber) {
		PaxNumber = paxNumber;
	}
	public List<Seat> getSeat() {
		return Seat;
	}
	public void setSeat(List<Seat> seat) {
		Seat = seat;
	}
	public List<Meal> getMeal() {
		return Meal;
	}
	public void setMeal(List<Meal> meal) {
		Meal = meal;
	}
	public List<Baggages> getBaggages() {
		return Baggages;
	}
	public void setBaggages(List<Baggages> baggages) {
		Baggages = baggages;
	}
	public String getDateofBirth() {
		return DateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		DateofBirth = dateofBirth;
	}
	public String getPassportNo() {
		return PassportNo;
	}
	public void setPassportNo(String passportNo) {
		PassportNo = passportNo;
	}
	public String getPassportExpiry() {
		return PassportExpiry;
	}
	public void setPassportExpiry(String passportExpiry) {
		PassportExpiry = passportExpiry;
	}
	
	

}
