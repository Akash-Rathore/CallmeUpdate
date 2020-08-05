package com.me.callme.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
@Table(name = "go_billing")
public class Billing implements Serializable{

	
	/*CREATE TABLE `go_billing` (
			  `id` int(11) NOT NULL AUTO_INCREMENT,
			  `product_id` int(11) DEFAULT NULL,
			  `quantity` int(11) DEFAULT NULL,
			  `amount` int(11) DEFAULT NULL,
			  `user_id` int(11) DEFAULT NULL,
			  `t_id` int(11) DEFAULT NULL,
			  `status` int(1) DEFAULT NULL,
			  `request_date_time` datetime DEFAULT NULL,
			  `response_date_time` datetime DEFAULT NULL,
			  `payment_type` int(2) DEFAULT NULL,
			  PRIMARY KEY (`id`)
			) ENGINE=InnoDB DEFAULT CHARSET=latin1*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	@Column(name = "product_id")
	private Integer product_id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "t_id")
	private String  t_id;
	
	
	@Column(name = "order_id")
	private String  order_id;
	
	
	@Column(name = "status")
	private Integer  status;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	@SerializedName("request_date_time")
	@Expose
	@Column(name = "request_date_time")
	Date request_date_time;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	@SerializedName("response_date_time")
	@Expose
	@Column(name = "response_date_time")
	Date response_date_time;
	
	@Column(name = "payment_type")
	String payment_type;
	
	
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRequest_date_time() {
		return request_date_time;
	}

	public void setRequest_date_time(Date request_date_time) {
		this.request_date_time = request_date_time;
	}

	public Date getResponse_date_time() {
		return response_date_time;
	}

	public void setResponse_date_time(Date response_date_time) {
		this.response_date_time = response_date_time;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	@Override
	public String toString() {
		return "Billing [id=" + id + ", product_id=" + product_id + ", quantity=" + quantity + ", amount=" + amount
				+ ", user_id=" + user_id + ", t_id=" + t_id + ", order_id=" + order_id + "]";
	}
	
	
	
}
