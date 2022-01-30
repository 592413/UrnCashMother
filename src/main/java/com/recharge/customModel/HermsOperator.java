package com.recharge.customModel;

public class HermsOperator implements java.io.Serializable {
	private String code="";
	private String desc="";
	private String commission="";
	private String itemType="";
	
	public void setCode(String code){
		this.code = code;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public void setCommission(String commission){
		this.commission = commission;
	}
	public void setItemType(String itemType){
		this.itemType = itemType;
	}
	
	public String getCode(){
		return code;
	}
	public String getDesc(){
		return desc;
	}
	public String getCommission(){
		return commission;
	}
	public String getItemType(){
		return itemType;
	}
}
