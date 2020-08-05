package com.me.callme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "go_user_mobile")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserMobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "mobile_no")
	private String mobile_no;
	
	@Column(name = "verifed_date")
	private Date verifed_date;
	

	@Column(name = "payment_type")
	private String payment_type;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	public String getMobile_no() {
		return mobile_no;
	}


	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}


	public Date getVerifed_date() {
		return verifed_date;
	}


	public void setVerifed_date(Date verifed_date) {
		this.verifed_date = verifed_date;
	}


	public String getPayment_type() {
		return payment_type;
	}


	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
	
	
	  
	  
	  
	  
	
	
}
