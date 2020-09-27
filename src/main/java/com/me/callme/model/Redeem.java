package com.me.callme.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "go_redeem")
@Data
@EqualsAndHashCode(callSuper = false)
public class Redeem implements Serializable{


	private static final long serialVersionUID = 2683317269832649079L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "status")
	private Integer status=2;
	
	@Column(name = "payment_type")
	private String payment_type;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	@Column(name = "date_time")
	private Date date_time;

	

	
	
	
	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", user_id=" + user_id + ", amount=" + amount + ", mobile=" + mobile + ", status="
				+ status + ", payment_type=" + payment_type + ", date_time=" + date_time + "},";
	}


	
	
	
	
	
	
}
