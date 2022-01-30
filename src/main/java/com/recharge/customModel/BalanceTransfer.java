package com.recharge.customModel;

import java.io.Serializable;

public class BalanceTransfer implements Serializable{

	private Integer transferId;
	private String fromId;
	private Double fromOpenBal;
	private Double transferAmount;
	private Double fromClosingBal;
	private String toId;
	private Double toOpenBal;
	private Double recievedAmount;
	private Double toclosingBal;
	private String remarks;
	private String narration;
	private String date;
	private String time;
	private String wlId;
	private String request_to;
	private String from_name;
	private String to_name;

	public BalanceTransfer() {
	}

	public BalanceTransfer(Integer transferId, String fromId, Double fromOpenBal, Double transferAmount,
			Double fromClosingBal, String toId, Double toOpenBal, Double recievedAmount, Double toclosingBal,
			String remarks, String narration, String date, String time, String wlId,String request_to,String from_name,String to_name) {

		this.transferId = transferId;
		this.fromId = fromId;
		this.fromOpenBal = fromOpenBal;
		this.transferAmount = transferAmount;
		this.fromClosingBal = fromClosingBal;
		this.toId = toId;
		this.toOpenBal = toOpenBal;
		this.recievedAmount = recievedAmount;
		this.toclosingBal = toclosingBal;
		this.remarks = remarks;
		this.narration = narration;
		this.date = date;
		this.time = time;
		this.wlId = wlId;
		this.request_to = request_to;
		this.from_name = from_name;
		this.to_name = to_name;
	}

	public Integer getTransferId() {
		return this.transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public String getFromId() {
		return this.fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public Double getFromOpenBal() {
		return this.fromOpenBal;
	}

	public void setFromOpenBal(Double fromOpenBal) {
		this.fromOpenBal = fromOpenBal;
	}

	public Double getTransferAmount() {
		return this.transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Double getFromClosingBal() {
		return this.fromClosingBal;
	}

	public void setFromClosingBal(Double fromClosingBal) {
		this.fromClosingBal = fromClosingBal;
	}

	public String getToId() {
		return this.toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public Double getToOpenBal() {
		return this.toOpenBal;
	}

	public void setToOpenBal(Double toOpenBal) {
		this.toOpenBal = toOpenBal;
	}

	public Double getRecievedAmount() {
		return this.recievedAmount;
	}

	public void setRecievedAmount(Double recievedAmount) {
		this.recievedAmount = recievedAmount;
	}

	public Double getToclosingBal() {
		return this.toclosingBal;
	}

	public void setToclosingBal(Double toclosingBal) {
		this.toclosingBal = toclosingBal;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNarration() {
		return this.narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getRequest_to() {
		return request_to;
	}

	public void setRequest_to(String request_to) {
		this.request_to = request_to;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

}
