package com.skyhotel.webserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.recharge.model.PackageWiseChargeComm;
import com.skyflight.model.Markup;
import com.skyhotel.CustomModel.BookingHotelRoomDetails;
import com.skyhotel.CustomModel.CancellationDetail;
import com.skyhotel.CustomModel.HotelBookResponse;
import com.skyhotel.CustomModel.HotelDetailsOutput;
import com.skyhotel.CustomModel.HotelPassengers;
import com.skyhotel.CustomModel.HotelPrice;
import com.skyhotel.CustomModel.HotelResults;
import com.skyhotel.CustomModel.HotelRoomCombination;
import com.skyhotel.CustomModel.HotelRoomCombinations;
import com.skyhotel.CustomModel.HotelRoomPrice;
import com.skyhotel.CustomModel.HotelRoomsDetail;
import com.skyhotel.CustomModel.RoomBlockOutput;
import com.skyhotel.CustomModel.RoomGuests;
import com.skyhotel.CustomModel.SearchResult;

public class HOtelWebserverParser {
	private static final Logger log = Logger.getLogger(HOtelWebserverParser.class);

	public static SearchResult searchHotelParser(String response,List<Markup> adminmarkupllist,List<Markup> usermarkuplist,List<PackageWiseChargeComm> pck,int roomno) {
		SearchResult output=new SearchResult();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("HotelSearchResult");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			if(jsonObj.getInt("ResponseStatus")==1){
				output.setTrackId(jsonObj.getString("TrackId"));
				output.setCheckIn(jsonObj.getString("CheckIn"));
				output.setCheckOut(jsonObj.getString("CheckOut"));
				int daydiff=UtilityHotel.dateDifference(jsonObj.getString("CheckIn"),jsonObj.getString("CheckOut"));
				output.setNightno(Integer.toString(daydiff));
				output.setCityId(jsonObj.getString("CityId"));
				output.setRoomsNo(Integer.toString(jsonObj.getInt("RoomsNo")));
				List<RoomGuests> RoomGuests=new ArrayList<RoomGuests>();
				JSONArray RoomGuestsJSONArray = jsonObj.getJSONArray("RoomGuests");
				RoomGuests rm=null;
				int totalguest=0;
				for(int i=0;i<RoomGuestsJSONArray.length();i++){
					rm=new RoomGuests();
					JSONObject RoomGuestssobj = RoomGuestsJSONArray.getJSONObject(i);
					totalguest=totalguest+Integer.parseInt(RoomGuestssobj.getString("AdultNo"))+Integer.parseInt(RoomGuestssobj.getString("ChildNo"));
					rm.setAdultNo(RoomGuestssobj.getString("AdultNo"));
					rm.setChildNo(RoomGuestssobj.getString("ChildNo"));
					List<Integer> ChildAge=new ArrayList<>();
					if(RoomGuestssobj.has("ChildAge")){
						JSONArray ChildAgeJSONArray = RoomGuestssobj.getJSONArray("ChildAge");
						for(int j=0;j<ChildAgeJSONArray.length();j++){
							ChildAge.add(ChildAgeJSONArray.getInt(j));
						}
						rm.setChildAge(ChildAge);
					}
					RoomGuests.add(rm);
				}
				output.setGuestno(Integer.toString(totalguest));
				output.setRoomGuests(RoomGuests);
				List<HotelResults> HotelResults=new ArrayList<HotelResults>();
				HotelResults hr= null;
				JSONArray HotelResultsJSONArray = jsonObj.getJSONArray("HotelResults");
				for(int k=0;k<HotelResultsJSONArray.length();k++){
					 hr= new HotelResults();
					 JSONObject HotelResultssobj = HotelResultsJSONArray.getJSONObject(k);
					 hr.setResultIndex(HotelResultssobj.getString("ResultIndex"));
					 hr.setSearchid(HotelResultssobj.getString("Searchid"));
					 hr.setProviderid(HotelResultssobj.getString("Providerid"));
					 hr.setHotelId(HotelResultssobj.getString("HotelId"));
					 hr.setHotelName(HotelResultssobj.getString("HotelName"));
					 hr.setStarRating(HotelResultssobj.getString("StarRating"));
					 hr.setHotelDescription(HotelResultssobj.getString("HotelDescription"));
					 hr.setHotelPhone(HotelResultssobj.getString("HotelPhone"));
					 hr.setHotelImage(HotelResultssobj.getString("HotelImage"));
					 hr.setHotelAddress(HotelResultssobj.getString("HotelAddress"));
					 hr.setHotelMap(HotelResultssobj.getString("HotelMap"));
					 hr.setLatitude(HotelResultssobj.getString("Latitude"));
					 hr.setLongitude(HotelResultssobj.getString("Longitude"));
					 
					 JSONObject HotelPriceobj=HotelResultssobj.getJSONObject("HotelPrice");
					 HotelPrice HotelPrice=new HotelPrice();
					 HotelPrice.setRoomPrice(HotelPriceobj.getDouble("RoomPrice"));
					 HotelPrice.setTax(HotelPriceobj.getDouble("Tax"));
					 HotelPrice.setExtraGuestCharge(HotelPriceobj.getDouble("ExtraGuestCharge"));
					 HotelPrice.setChildCharge(HotelPriceobj.getDouble("ChildCharge"));
					 HotelPrice.setOtherCharges(HotelPriceobj.getDouble("OtherCharges"));
					 HotelPrice.setDiscount(HotelPriceobj.getDouble("Discount"));
					 HotelPrice.setServiceTax(HotelPriceobj.getDouble("ServiceTax"));
					 double hotelprice=HotelPriceobj.getDouble("RoomPrice")+HotelPriceobj.getDouble("Tax")+HotelPriceobj.getDouble("ExtraGuestCharge")+HotelPriceobj.getDouble("ChildCharge")+HotelPriceobj.getDouble("OtherCharges")+HotelPriceobj.getDouble("Discount");
					 double adminmarkup = 0.0;
						double usermarkup = 0.0;
					 if(adminmarkupllist.size()>0){
							if(adminmarkupllist.get(0).getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								adminmarkup = hotelprice*adminmarkupllist.get(0).getMarkup_value()/100;
							}else{
								adminmarkup =adminmarkupllist.get(0).getMarkup_value();
							}
					 }
					 adminmarkup=adminmarkup*roomno;
					 if(usermarkuplist.size()>0){
							if(usermarkuplist.get(0).getMarkup_type().equalsIgnoreCase("PERCENTAGE")){
								usermarkup = hotelprice*usermarkuplist.get(0).getMarkup_value()/100;
							}else{
								usermarkup =usermarkuplist.get(0).getMarkup_value();
							}
					 }
					 usermarkup=usermarkup*roomno;
					 double charge=0.0;
						double portalcomm=0.0;
						for(PackageWiseChargeComm mrk : pck){
							System.out.println("mrk.getOperator_id()::"+mrk.getOperator_id());
							
								if (mrk.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
									portalcomm=((hotelprice+adminmarkup)*mrk.getComm())/100;
								}else{
									portalcomm=mrk.getComm();
								}
								if (mrk.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
									charge=((hotelprice+adminmarkup)*mrk.getCharge())/100;
								}else{
									charge=mrk.getCharge();
								}
							
						}
						portalcomm=portalcomm*roomno;
					 HotelPrice.setTotalprice(hotelprice);
					 hr.setHotelPrice(HotelPrice);
					 hr.setAdminmarkup(adminmarkup);
					 hr.setUsermarkup(usermarkup);
					 hr.setCommission(portalcomm);
					 HotelResults.add(hr);
				}
				output.setHotelResults(HotelResults);
				
			}else{
				JSONObject erro=jsonObj.getJSONObject("ErrorDetails");
				
				output.setErrorCode(erro.getInt("ErrorCode"));
				output.setErrorMessage(erro.getString("ErrorMessage"));
			}
			
		}catch(Exception e){
			log.warn("searchHotelParser:::"+e);
			}
		return output;
	}

	public static HotelDetailsOutput searchHotalDetailsParser(String response,Map<String,Object> request) {
		HotelDetailsOutput output=new HotelDetailsOutput();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("HotelDetails");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			if(jsonObj.getInt("ResponseStatus")==1){
				output.setTrackId(jsonObj.getString("TrackId"));
				output.setSearchid(jsonObj.getString("Searchid"));
				output.setProviderid(jsonObj.getString("Providerid"));
				output.setHotelId(jsonObj.getString("HotelId"));
				output.setHotelName(jsonObj.getString("HotelName"));
				output.setStarRating(jsonObj.getString("StarRating"));
				if(jsonObj.has("HotelDescription")){
					output.setHotelDescription(jsonObj.getString("HotelDescription"));
				}
				
				output.setHotelPhone(jsonObj.getString("HotelPhone"));
				output.setHotelAddress(jsonObj.getString("HotelAddress"));
				output.setLatitude(jsonObj.getString("Latitude"));
				output.setLongitude(jsonObj.getString("Longitude"));
				output.setCountryName(jsonObj.getString("CountryName"));
				List<String> HotelAttractions=new ArrayList<>();
				output.setHotelAttractions(HotelAttractions);
				List<String> HotelImages=new ArrayList<>();
				JSONArray HotelImagesarray=jsonObj.getJSONArray("HotelImages");
				for(int i=0;i<HotelImagesarray.length();i++){
					HotelImages.add(HotelImagesarray.getString(i));
				}
				output.setHotelImages(HotelImages);
				List<String> HotelFacilities=new ArrayList<>();
				JSONArray HotelFacilitiesarray=jsonObj.getJSONArray("HotelFacilities");
				for(int i=0;i<HotelFacilitiesarray.length();i++){
					HotelFacilities.add(HotelFacilitiesarray.getString(i));
				}
				output.setHotelFacilities(HotelFacilities);
				JSONObject RoomCombinations=jsonObj.getJSONObject("HotelRoomCombinations");
				
				JSONArray HotelRoomsDetailarray=jsonObj.getJSONArray("HotelRoomsDetail");
				double hotelprice=0;
				HotelRoomCombinations HotelRoomsDetail=new HotelRoomCombinations();
				HotelRoomsDetail.setInfoSource(RoomCombinations.getString("InfoSource"));
				HotelRoomsDetail.setIsPolicyPerStay(Boolean.getBoolean(RoomCombinations.get("IsPolicyPerStay").toString()));
				List<HotelRoomCombination> HotelRoomComlist=new ArrayList<>();
				JSONArray HotelRoomCombinationarray=RoomCombinations.getJSONArray("HotelRoomCombination");
				for(int j=0;j<HotelRoomCombinationarray.length();j++){
					HotelRoomCombination hrco=new HotelRoomCombination();
					List<HotelRoomsDetail> HotelRoomsDelist=new ArrayList<>();
					JSONObject hotelobj=(JSONObject) HotelRoomCombinationarray.get(j);
					JSONArray roomecode=hotelobj.getJSONArray("RoomCode");
					for(int k=0;k<roomecode.length();k++){
						System.out.println(roomecode.get(k));
						int roomcd=roomecode.getInt(k);
						for(int i=0;i<HotelRoomsDetailarray.length();i++){
							JSONObject HotelRoomsDetailobj=(JSONObject) HotelRoomsDetailarray.get(i);
							if(roomcd==Integer.parseInt(HotelRoomsDetailobj.get("RoomCode").toString())){
								HotelRoomsDetail hmd=new HotelRoomsDetail();
								hmd.setRequireAllPax(Boolean.getBoolean(HotelRoomsDetailobj.get("RequireAllPax").toString()));
								hmd.setRoomCode(HotelRoomsDetailobj.get("RoomCode").toString());
								hmd.setRoomName(HotelRoomsDetailobj.get("RoomName").toString());
								hmd.setRatePlanCode(HotelRoomsDetailobj.get("RatePlanCode").toString());
								hmd.setRoomTypeCode(HotelRoomsDetailobj.get("RoomTypeCode").toString());
								hmd.setRoomTypeName(HotelRoomsDetailobj.get("RoomTypeName").toString());
								hmd.setInfoSource(HotelRoomsDetailobj.get("InfoSource").toString());
								hmd.setSequenceNo(HotelRoomsDetailobj.get("SequenceNo").toString());
								hmd.setLastCancellationDate(HotelRoomsDetailobj.get("LastCancellationDate").toString());
								
								List<CancellationDetail> cancellationDetail=new ArrayList<CancellationDetail>();
								JSONArray CancellationDetailarray=HotelRoomsDetailobj.getJSONArray("CancellationDetail");
								CancellationDetail cds=new CancellationDetail();
								for(int h=0;h<CancellationDetailarray.length();h++){
									JSONObject CancellationDetailobj=(JSONObject) CancellationDetailarray.get(h);
									cds.setCharge(CancellationDetailobj.getInt("Charge"));
									cds.setChargeType(CancellationDetailobj.getInt("ChargeType"));
									cds.setCurrency(CancellationDetailobj.getString("Currency"));
									cds.setFromDate(CancellationDetailobj.getString("FromDate"));
									cds.setToDate(CancellationDetailobj.getString("ToDate"));
									cancellationDetail.add(cds);
								}
								hmd.setCancellationDetail(cancellationDetail);
								JSONArray Amenitiesarray=HotelRoomsDetailobj.getJSONArray("Amenities");
								List<String > Amenities=new ArrayList<>();
								for(int kk=0;kk<Amenitiesarray.length();kk++){
									Amenities.add(Amenitiesarray.getString(kk));
								}
								hmd.setAmenities(Amenities);
								
								JSONArray Inclusionarray=HotelRoomsDetailobj.getJSONArray("Inclusion");
								List<String > Inclusion=new ArrayList<>();
								for(int kk=0;kk<Inclusionarray.length();kk++){
									Inclusion.add(Inclusionarray.getString(kk));
								}
								hmd.setInclusion(Inclusion);
								hmd.setCancellationPolicies(HotelRoomsDetailobj.get("CancellationPolicies").toString());
								hmd.setRatePlan(HotelRoomsDetailobj.get("RatePlan").toString());
								
								HotelRoomPrice hrp=new HotelRoomPrice();
								JSONObject HotelRoomPriceobj=HotelRoomsDetailobj.getJSONObject("HotelRoomPrice");
								hrp.setRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice"));
								hrp.setTax(HotelRoomPriceobj.getDouble("Tax"));
								hrp.setExtraGuestCharge(HotelRoomPriceobj.getDouble("ExtraGuestCharge"));
								hrp.setChildCharge(HotelRoomPriceobj.getDouble("ChildCharge"));
								hrp.setOtherCharges(HotelRoomPriceobj.getDouble("OtherCharges"));
								hrp.setDiscount(HotelRoomPriceobj.getDouble("Discount"));
								hrp.setServiceTax(HotelRoomPriceobj.getDouble("ServiceTax"));
								 hotelprice=HotelRoomPriceobj.getDouble("RoomPrice")+HotelRoomPriceobj.getDouble("Tax")+HotelRoomPriceobj.getDouble("ExtraGuestCharge")+HotelRoomPriceobj.getDouble("ChildCharge")+HotelRoomPriceobj.getDouble("OtherCharges")+HotelRoomPriceobj.getDouble("Discount");
								hrp.setTotal(hotelprice);
								hmd.setTotal(hotelprice);
								hmd.setTax(HotelRoomPriceobj.getDouble("Tax"));
								hmd.setRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice"));
								hmd.setOtherCharges(HotelRoomPriceobj.getDouble("OtherCharges"));
								hmd.setHotelRoomPrice(hrp);
								hmd.setCustomRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice")+Double.parseDouble(request.get("adminmarkup").toString())+Double.parseDouble(request.get("usermarkup").toString()));
								hmd.setCustomtotal(hotelprice+Double.parseDouble(request.get("adminmarkup").toString())+Double.parseDouble(request.get("usermarkup").toString()));
								HotelRoomsDelist.add(hmd);
								
								HotelRoomsDetailarray.remove(i);
								break;
							}
						}
					}
					hrco.setHotelRoomsDetail(HotelRoomsDelist);
					HotelRoomComlist.add(hrco);
				}
				
				
				
				
				HotelRoomsDetail.setHotelRoomCombination(HotelRoomComlist);
				output.setHotelRoomCombinations(HotelRoomsDetail);
			/*	List<HotelRoomsDetail> HotelRoomsDetail=new ArrayList<HotelRoomsDetail>();
				JSONArray HotelRoomsDetailarray=jsonObj.getJSONArray("HotelRoomsDetail");
				HotelRoomsDetail hrm=new HotelRoomsDetail();
				for(int i=0;i<HotelRoomsDetailarray.length();i++){
					JSONObject HotelRoomsDetailobj=(JSONObject) HotelRoomsDetailarray.get(i);
					hrm.setRequireAllPax(HotelRoomsDetailobj.getBoolean("RequireAllPax"));
					hrm.setRoomCode(HotelRoomsDetailobj.getString("RoomCode"));
					hrm.setRoomName(HotelRoomsDetailobj.getString("RoomName"));
					hrm.setRatePlanCode(HotelRoomsDetailobj.getString("RatePlanCode"));
					hrm.setRoomTypeCode(HotelRoomsDetailobj.getString("RoomTypeCode"));
					hrm.setRoomTypeName(HotelRoomsDetailobj.getString("RoomTypeName"));
					hrm.setInfoSource(HotelRoomsDetailobj.getString("InfoSource"));
					hrm.setSequenceNo(HotelRoomsDetailobj.getString("SequenceNo"));
					hrm.setLastCancellationDate(HotelRoomsDetailobj.getString("LastCancellationDate"));
					List<CancellationDetail> cancellationDetail=new ArrayList<CancellationDetail>();
					JSONArray CancellationDetailarray=HotelRoomsDetailobj.getJSONArray("CancellationDetail");
					CancellationDetail cds=new CancellationDetail();
					for(int j=0;j<CancellationDetailarray.length();j++){
						JSONObject CancellationDetailobj=(JSONObject) CancellationDetailarray.get(j);
						cds.setCharge(CancellationDetailobj.getInt("Charge"));
						cds.setChargeType(CancellationDetailobj.getInt("ChargeType"));
						cds.setCurrency(CancellationDetailobj.getString("Currency"));
						cds.setFromDate(CancellationDetailobj.getString("FromDate"));
						cds.setToDate(CancellationDetailobj.getString("ToDate"));
						cancellationDetail.add(cds);
					}
					hrm.setCancellationDetail(cancellationDetail);
					
					JSONArray Amenitiesarray=HotelRoomsDetailobj.getJSONArray("Amenities");
					List<String > Amenities=new ArrayList<>();
					for(int k=0;k<Amenitiesarray.length();k++){
						Amenities.add(Amenitiesarray.getString(k));
					}
					hrm.setAmenities(Amenities);
					
					JSONArray Inclusionarray=HotelRoomsDetailobj.getJSONArray("Inclusion");
					List<String > Inclusion=new ArrayList<>();
					for(int k=0;k<Inclusionarray.length();k++){
						Inclusion.add(Inclusionarray.getString(k));
					}
					hrm.setInclusion(Inclusion);
					hrm.setCancellationPolicies(HotelRoomsDetailobj.getString("CancellationPolicies"));
					hrm.setRatePlan(HotelRoomsDetailobj.getString("RatePlan"));
					HotelRoomPrice hrp=new HotelRoomPrice();
					JSONObject HotelRoomPriceobj=HotelRoomsDetailobj.getJSONObject("HotelRoomPrice");
					hrp.setRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice"));
					hrp.setTax(HotelRoomPriceobj.getDouble("Tax"));
					hrp.setExtraGuestCharge(HotelRoomPriceobj.getDouble("ExtraGuestCharge"));
					hrp.setChildCharge(HotelRoomPriceobj.getDouble("ChildCharge"));
					hrp.setOtherCharges(HotelRoomPriceobj.getDouble("OtherCharges"));
					hrp.setDiscount(HotelRoomPriceobj.getDouble("Discount"));
					hrp.setServiceTax(HotelRoomPriceobj.getDouble("ServiceTax"));
					hrm.setHotelRoomPrice(hrp);
					HotelRoomsDetail.add(hrm);
				}*/
				//output.setHotelRoomsDetail(HotelRoomsDetail);
				
			}else{
				
			}
			
		}catch(Exception e){
			log.warn("searchHotalDetailsParser:::"+e);
		}
		return output;
	}

	public static RoomBlockOutput roomblockParser(String response) {
		RoomBlockOutput output=new RoomBlockOutput();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("HotelBlockResponse");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			output.setIsPriceChanged(Boolean.getBoolean(jsonObj.get("IsPriceChanged").toString()));
			output.setIsCancellationPolicyChanged(Boolean.getBoolean(jsonObj.get("IsCancellationPolicyChanged").toString()));
			output.setIsHotelPolicyChanged(Boolean.getBoolean(jsonObj.get("IsHotelPolicyChanged").toString()));
			output.setIsPackageFare(Boolean.getBoolean(jsonObj.get("IsPackageFare").toString()));
			output.setIsPackageDetailsMandatory(Boolean.getBoolean(jsonObj.get("IsPackageDetailsMandatory").toString()));
			if(jsonObj.getInt("ResponseStatus")==1){
				output.setTrackId(jsonObj.getString("TrackId"));
				output.setAvailabilityType(jsonObj.getString("AvailabilityType"));
				output.setHotelRules(jsonObj.getString("HotelRules"));
				List<HotelRoomsDetail> HotelRoomsDetail=new ArrayList<>();
				JSONArray HotelRoomsDetailarray=jsonObj.getJSONArray("HotelRoomsDetail") ;
				for(int i=0;i<HotelRoomsDetailarray.length();i++){
					JSONObject HotelRoomsDetailobj=(JSONObject) HotelRoomsDetailarray.get(i);
					HotelRoomsDetail hrm=new HotelRoomsDetail();
					hrm.setRequireAllPax(HotelRoomsDetailobj.getBoolean("RequireAllPax"));
					hrm.setRoomCode(HotelRoomsDetailobj.getString("RoomCode"));
					hrm.setRoomName(HotelRoomsDetailobj.getString("RoomName"));
					hrm.setRatePlanCode(HotelRoomsDetailobj.getString("RatePlanCode"));
					hrm.setRoomTypeCode(HotelRoomsDetailobj.getString("RoomTypeCode"));
					hrm.setRoomTypeName(HotelRoomsDetailobj.getString("RoomTypeName"));
					hrm.setInfoSource(HotelRoomsDetailobj.getString("InfoSource"));
					hrm.setSequenceNo(HotelRoomsDetailobj.getString("SequenceNo"));
					hrm.setLastCancellationDate(HotelRoomsDetailobj.getString("LastCancellationDate"));
					List<CancellationDetail> cancellationDetail=new ArrayList<CancellationDetail>();
					JSONArray CancellationDetailarray=HotelRoomsDetailobj.getJSONArray("CancellationDetail");
					CancellationDetail cds=new CancellationDetail();
					for(int h=0;h<CancellationDetailarray.length();h++){
						JSONObject CancellationDetailobj=(JSONObject) CancellationDetailarray.get(h);
						cds.setCharge(CancellationDetailobj.getInt("Charge"));
						cds.setChargeType(CancellationDetailobj.getInt("ChargeType"));
						cds.setCurrency(CancellationDetailobj.getString("Currency"));
						cds.setFromDate(CancellationDetailobj.getString("FromDate"));
						cds.setToDate(CancellationDetailobj.getString("ToDate"));
						cancellationDetail.add(cds);
					}
					hrm.setCancellationDetail(cancellationDetail);
					JSONArray Amenitiesarray=HotelRoomsDetailobj.getJSONArray("Amenities");
					List<String > Amenities=new ArrayList<>();
					for(int kk=0;kk<Amenitiesarray.length();kk++){
						Amenities.add(Amenitiesarray.getString(kk));
					}
					hrm.setAmenities(Amenities);
					
					JSONArray Inclusionarray=HotelRoomsDetailobj.getJSONArray("Inclusion");
					List<String > Inclusion=new ArrayList<>();
					for(int kk=0;kk<Inclusionarray.length();kk++){
						Inclusion.add(Inclusionarray.getString(kk));
					}
					hrm.setInclusion(Inclusion);
					hrm.setCancellationPolicies(HotelRoomsDetailobj.get("CancellationPolicies").toString());
					hrm.setRatePlan(HotelRoomsDetailobj.get("RatePlan").toString());
					HotelRoomPrice hrp=new HotelRoomPrice();
					JSONObject HotelRoomPriceobj=HotelRoomsDetailobj.getJSONObject("HotelRoomPrice");
					hrp.setRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice"));
					hrp.setTax(HotelRoomPriceobj.getDouble("Tax"));
					hrp.setExtraGuestCharge(HotelRoomPriceobj.getDouble("ExtraGuestCharge"));
					hrp.setChildCharge(HotelRoomPriceobj.getDouble("ChildCharge"));
					hrp.setOtherCharges(HotelRoomPriceobj.getDouble("OtherCharges"));
					hrp.setDiscount(HotelRoomPriceobj.getDouble("Discount"));
					hrp.setServiceTax(HotelRoomPriceobj.getDouble("ServiceTax"));
					double hotelprice=HotelRoomPriceobj.getDouble("RoomPrice")+HotelRoomPriceobj.getDouble("Tax")+HotelRoomPriceobj.getDouble("ExtraGuestCharge")+HotelRoomPriceobj.getDouble("ChildCharge")+HotelRoomPriceobj.getDouble("OtherCharges")+HotelRoomPriceobj.getDouble("Discount");
					hrp.setTotal(hotelprice);
					hrm.setTotal(hotelprice);
					hrm.setTax(HotelRoomPriceobj.getDouble("Tax"));
					hrm.setRoomPrice(HotelRoomPriceobj.getDouble("RoomPrice"));
					hrm.setOtherCharges(HotelRoomPriceobj.getDouble("OtherCharges"));
					hrm.setHotelRoomPrice(hrp);
					
					HotelRoomsDetail.add(hrm);
				}
				output.setHotelRoomsDetail(HotelRoomsDetail);
			}else{
				
			}
			
		}catch(Exception e){
			log.warn("roomblockParser:::"+e);
		}
		return output;
	}

	public static HotelBookResponse roombookParser(String response) {
		HotelBookResponse output=new HotelBookResponse();
		try{
			JSONObject jsonObj2 = new JSONObject(response);
			System.out.println("jsonObj2::"+jsonObj2);
			JSONObject jsonObj=jsonObj2.getJSONObject("HotelBookResponse");
			output.setResponseStatus(jsonObj.getInt("ResponseStatus"));
			if(jsonObj.getInt("ResponseStatus")==1){
			output.setTrackId(jsonObj.getString("TrackId"));
			output.setConfirmationNo(jsonObj.getString("ConfirmationNo"));
			output.setBookingRefNo(jsonObj.getString("BookingRefNo"));
			output.setBookingId(jsonObj.getString("BookingId"));
			output.setCheckIn(jsonObj.getString("CheckIn"));
			output.setCheckOut(jsonObj.getString("CheckOut"));
			output.setCity(jsonObj.getString("City"));
			output.setInvoiceNumber(jsonObj.getString("InvoiceNumber"));
			output.setInvoiceCreatedOn(jsonObj.getString("InvoiceCreatedOn"));
			output.setBookingDate(jsonObj.getString("BookingDate"));
			output.setHotelName(jsonObj.getString("HotelName"));
			output.setStarRating(jsonObj.getString("StarRating"));
			output.setHotelPolicyDetail(jsonObj.getString("HotelPolicyDetail"));
			output.setAddress1(jsonObj.getString("Address1"));
			output.setAddress2(jsonObj.getString("Address2"));
			output.setLatitude(jsonObj.getString("Latitude"));
			output.setLongitude(jsonObj.getString("Longitude"));
			output.setLastCancellationDate(jsonObj.getString("LastCancellationDate"));
			List<BookingHotelRoomDetails> bkhtlrmdt=new ArrayList<>();
			JSONArray arrayroondt=jsonObj.getJSONArray("HotelRoomsDetails");
			for(int i=0;i<arrayroondt.length();i++){
				JSONObject roomobj=arrayroondt.getJSONObject(i);
				BookingHotelRoomDetails BookingHotelRoomDetails=new BookingHotelRoomDetails();
				BookingHotelRoomDetails.setAdultCount(Integer.toString(roomobj.getInt("AdultCount")));
				BookingHotelRoomDetails.setChildCount(Integer.toString(roomobj.getInt("ChildCount")));
				List<HotelPassengers> HotelPassengers=new ArrayList<>();
				JSONArray HotelPassengersarray=roomobj.getJSONArray("HotelPassengers");
				for(int k=0;k<HotelPassengersarray.length();k++){
					JSONObject objhotelpassen=(JSONObject) HotelPassengersarray.get(k);
					HotelPassengers HotelPass=new HotelPassengers();
					HotelPass.setTitle(objhotelpassen.getString("Title"));
					HotelPass.setFirstName(objhotelpassen.getString("FirstName"));
					HotelPass.setLastName(objhotelpassen.getString("LastName"));
					HotelPass.setPhoneno(objhotelpassen.getString("Phoneno"));
					HotelPass.setEmail(objhotelpassen.getString("Email"));
					HotelPass.setPaxType(objhotelpassen.getString("PaxType"));
					HotelPass.setLeadPassenger(objhotelpassen.getBoolean("LeadPassenger"));
					HotelPass.setAge(Integer.toString(objhotelpassen.getInt("Age")));
					HotelPassengers.add(HotelPass);
				}
				
				BookingHotelRoomDetails.setHotelPassengers(HotelPassengers);
				BookingHotelRoomDetails.setRoomCode(roomobj.getString("RoomCode"));
				BookingHotelRoomDetails.setRoomTypeCode(roomobj.getString("RoomTypeCode"));
				BookingHotelRoomDetails.setRoomTypeName(roomobj.getString("RoomTypeName"));
				BookingHotelRoomDetails.setRatePlanCode(roomobj.getString("RatePlanCode"));
				HotelRoomPrice HotelRoomPrice=new HotelRoomPrice();
				JSONObject objroompricw=roomobj.getJSONObject("HotelRoomPrice");
				HotelRoomPrice.setChildCharge(objroompricw.getDouble("ChildCharge"));
				HotelRoomPrice.setRoomPrice(objroompricw.getDouble("RoomPrice"));
				HotelRoomPrice.setDiscount(objroompricw.getDouble("Discount"));
				HotelRoomPrice.setExtraGuestCharge(objroompricw.getDouble("ExtraGuestCharge"));
				HotelRoomPrice.setOtherCharges(objroompricw.getDouble("OtherCharges"));
				HotelRoomPrice.setTax(objroompricw.getDouble("Tax"));
				HotelRoomPrice.setServiceTax(objroompricw.getDouble("ServiceTax"));
				
				BookingHotelRoomDetails.setHotelRoomPrice(HotelRoomPrice);
				BookingHotelRoomDetails.setCancellationPolicy(roomobj.getString("CancellationPolicy"));
				
				
				bkhtlrmdt.add(BookingHotelRoomDetails);
			}
			
			output.setBookingHotelRoomDetails(bkhtlrmdt);
			}else{
				output=new HotelBookResponse(jsonObj.getInt("ResponseStatus"),jsonObj.getInt("ErrorCode"),jsonObj.getString("ErrorMessage"));
			}
		}catch(Exception e){
			log.warn("roombookParser:::"+e);
		}
		return output;
	}

}
