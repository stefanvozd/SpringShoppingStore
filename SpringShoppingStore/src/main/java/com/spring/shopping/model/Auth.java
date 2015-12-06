package com.spring.shopping.model;

public class Auth {
	private String id;

	private String timestamp;

	private String authtoken;

	private Integer nonce;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	public Integer getNonce() {
		return nonce;
	}

	public void setNonce(Integer nonce) {
		this.nonce = nonce;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", timestamp = " + timestamp
				+ ", authtoken = " + authtoken + ", nonce = " + nonce + "]";
	}
}