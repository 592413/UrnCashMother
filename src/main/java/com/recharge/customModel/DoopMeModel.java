package com.recharge.customModel;

import java.io.Serializable;

public class DoopMeModel implements Serializable {
	private String ERROR;
	private String STATUS;
	private String OPTXNID;
	private String MEMBERREQID;
	private String AMOUNT;
	private String NUMBER;
	private String MESSAGE;
	private String FIELD1;
	private String FIELD2;
	private String ip;

	public DoopMeModel(String eRROR, String sTATUS, String oPTXNID, String mEMBERREQID, String aMOUNT, String nUMBER,
			String mESSAGE, String fIELD1, String fIELD2, String ip) {

		ERROR = eRROR;
		STATUS = sTATUS;
		OPTXNID = oPTXNID;
		MEMBERREQID = mEMBERREQID;
		AMOUNT = aMOUNT;
		NUMBER = nUMBER;
		MESSAGE = mESSAGE;
		FIELD1 = fIELD1;
		FIELD2 = fIELD2;
		this.ip = ip;
	}

	public DoopMeModel() {
		// TODO Auto-generated constructor stub
	}

	public String getERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getOPTXNID() {
		return OPTXNID;
	}

	public void setOPTXNID(String oPTXNID) {
		OPTXNID = oPTXNID;
	}

	public String getMEMBERREQID() {
		return MEMBERREQID;
	}

	public void setMEMBERREQID(String mEMBERREQID) {
		MEMBERREQID = mEMBERREQID;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public String getNUMBER() {
		return NUMBER;
	}

	public void setNUMBER(String nUMBER) {
		NUMBER = nUMBER;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getFIELD1() {
		return FIELD1;
	}

	public void setFIELD1(String fIELD1) {
		FIELD1 = fIELD1;
	}

	public String getFIELD2() {
		return FIELD2;
	}

	public void setFIELD2(String fIELD2) {
		FIELD2 = fIELD2;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "DoopMeModel [ERROR=" + ERROR + ", STATUS=" + STATUS + ", OPTXNID=" + OPTXNID + ", MEMBERREQID="
				+ MEMBERREQID + ", AMOUNT=" + AMOUNT + ", NUMBER=" + NUMBER + ", MESSAGE=" + MESSAGE + ", FIELD1="
				+ FIELD1 + ", FIELD2=" + FIELD2 + ", ip=" + ip + "]";
	}

}
