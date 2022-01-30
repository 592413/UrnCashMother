package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "nsdlpanattachment")
@NamedQueries({
	@NamedQuery(name="getNSDLAttachment",query="From NSDLPanAttachment as na where na.invoiceno=:invoiceno"),
	@NamedQuery(name="getNSDLAttachmentById",query="From NSDLPanAttachment as na where na.userId=:userId")
})
public class NSDLPanAttachment implements Serializable{
	private int id;
	private String userId;
	private String invoiceno;
	private String name;
	
	private byte[] bytesProofOfIdentity;
	
	private String idprooftype;
	public NSDLPanAttachment() {
		super();
	}
	
	
	public NSDLPanAttachment(String userId, String invoiceno, String name, byte[] bytesProofOfIdentity,String idprooftype) {
		
		this.userId = userId;
		this.invoiceno = invoiceno;
		this.name = name;
		this.bytesProofOfIdentity = bytesProofOfIdentity;
		this.idprooftype = idprooftype;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getBytesProofOfIdentity() {
		return bytesProofOfIdentity;
	}
	public void setBytesProofOfIdentity(byte[] bytesProofOfIdentity) {
		this.bytesProofOfIdentity = bytesProofOfIdentity;
	}


	

	public String getIdprooftype() {
		return idprooftype;
	}


	public void setIdprooftype(String idprooftype) {
		this.idprooftype = idprooftype;
	}
	
	

}
