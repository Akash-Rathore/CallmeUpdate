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
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "go_notification")
public class Notification implements Serializable {

	
	
	
	
	private static final long serialVersionUID = 1L;

   // @ApiModelProperty(value = "name", hidden = true)
	@SerializedName("id")
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	Long id;

	@SerializedName("device_id")
	@Expose
	@Column(name = "device_id")
	String device_id;

	@SerializedName("message")
	@Expose
	@Column(name = "message")
	String message;

	@SerializedName("partyA_id")
	@Expose
	@Column(name = "partyA_id")
	Integer partyA_id;

	@SerializedName("partyB_id")
	@Expose
	@Column(name = "partyB_id")
	Integer partyB_id;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Kolkata")
	@SerializedName("datetime")
	@Expose
	@Column(name = "datetime")
	Date datetime;

	@SerializedName("message_type")
	@Expose
	@Column(name = "message_type")
	String message_type;

	@SerializedName("status")
	@Expose
	@Column(name = "status")
	Integer status;

	@SerializedName("result")
	@Expose
	@Column(name = "result")
	String result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPartyA_id() {
		return partyA_id;
	}

	public void setPartyA_id(Integer partyA_id) {
		this.partyA_id = partyA_id;
	}

	public Integer getPartyB_id() {
		return partyB_id;
	}

	public void setPartyB_id(Integer partyB_id) {
		this.partyB_id = partyB_id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", device_id=" + device_id + ", message=" + message + ", partyA_id="
				+ partyA_id + ", partyB_id=" + partyB_id + ", datetime=" + datetime + ", message_type=" + message_type
				+ ", status=" + status + ", result=" + result + "]";
	}
	
	

}
