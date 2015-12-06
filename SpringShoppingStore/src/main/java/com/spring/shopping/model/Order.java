package com.spring.shopping.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	private Long orderId;
	private java.util.Date createdDate;
	private java.util.Date updatedDate;
	private String emailAddress;
	private String orderStatus;
	private BigDecimal orderTotal;
	private Long customerId;
	private Long addressId;
	private Long transactionid;
	private String  sellerPhoneNumber;

	public Long getTransactionid() {
		return transactionid;
	}
	
	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	
	public static String mappStatus (int iStatus) {
		switch (iStatus) {
		case 1: return "Pending";
		case 2: return "Paid";
		case 3: return "Rejected";
		case -1: return "Error";
		}
		
		return "Error";
	}



	public String getSellerPhoneNumber() {
		return sellerPhoneNumber;
	}

	public void setSellerPhoneNumber(String setSellerPhoneNumber) {
		this.sellerPhoneNumber = setSellerPhoneNumber;
	}

}