package com.me.callme.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "go_product")
public class Product implements Serializable{

	
/*	CREATE TABLE `go_product` (
			  `id` int(11) NOT NULL AUTO_INCREMENT,
			  `amount` int(11) DEFAULT NULL,
			  `unit` varchar(255) DEFAULT NULL,
			  `country` int(11) DEFAULT NULL,
			  `operator` varchar(255) DEFAULT NULL,
			  `status` int(1) DEFAULT NULL,
			  `pack_des` text,
			  PRIMARY KEY (`id`)
			) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "unit")
	private String  unit;
	
	@Column(name = "country")
	private Integer country;
	
	@Column(name = "operator")
	private Integer operator;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "pack_des")
	private String  pack_des;
	
	
	@Column(name = "min")
	private Integer  min;
	
	
	@Column(name = "sms")
	private Integer  sms;
	
	
	

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getSms() {
		return sms;
	}

	public void setSms(Integer sms) {
		this.sms = sms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPack_des() {
		return pack_des;
	}

	public void setPack_des(String pack_des) {
		this.pack_des = pack_des;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", amount=" + amount + ", unit=" + unit + ", country=" + country + ", operator="
				+ operator + ", status=" + status + ", pack_des=" + pack_des + "]";
	}
	
	
	
	
	
}
