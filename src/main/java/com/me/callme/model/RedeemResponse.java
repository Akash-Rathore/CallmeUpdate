package com.me.callme.model;

public class RedeemResponse {

	
	private long user_id;
	private String username;
	private String mobile;
	private Integer amount;
	private String payment_type;
	
	public RedeemResponse(long user_id , String username , String mobile,Integer amount,String payment_type) {
		
		this.user_id=user_id;
		this.username=username;
		this.mobile=mobile;
		this.amount=amount;
		this.payment_type=payment_type;
		
	}
	
  
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}



	
}
