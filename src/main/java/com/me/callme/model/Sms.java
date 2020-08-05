package com.me.callme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "go_sms")
public class Sms {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	@Column(name = "date_time")
	private Date date_time;
	
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "transcation_id")
	private String transcation_id;
	
	
}
