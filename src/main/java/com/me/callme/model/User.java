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
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {


	private static final long serialVersionUID = 2683317269832649079L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_TABLE_GENERATOR")
	@TableGenerator(name = "USER_TABLE_GENERATOR", table = "PK_Sequences", pkColumnName = "Sequence_Name", pkColumnValue = "USER_SEQ", valueColumnName = "Sequence_Value", allocationSize = 1)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;
	
	@Column(name = "last_billing_date", length = 512)
	private Date lastBillingDate;
	
	@Column(name = "username", length = 512)
	private String username;
	
	@Column(name = "gender", length = 30)
	private String gender;
	
	@Column(name = "age", length = 30)
	private Integer age;
	
	@Column(name = "city", length = 512)
	private String city;
	
	@Column(name = "lang", length = 512)
	private String lang;
	
	@Column(name = "description", length = 512)
	private String description;
	
	@Column(name = "mobile", length = 30)
	private String mobile;
	
	@Column(name = "country", length = 255)
	private String country;
	
	@Column(name = "scmnotification", length = 512)
	private String scmnotification;
	
	@Column(name = "lastupdatetime", length = 512)
	private Date lastupdatetime;
	
	@Column(name = "lastlogintime", length = 512)
	private Date lastlogintime;
	
	@Column(name = "lastlogouttime", length = 512)
	private Date lastlogouttime;
	
	@Column(name = "registerdatetime", length = 512)
	private Date registerdatetime;
	
	@Column(name = "customloginstatus", length = 512)
	private String customloginstatus;
	
	@Column(name = "asterikloginstatus", length = 512)
	private String sipstatus;
	
	@Column(name = "accountstatus", length = 512)
	private Boolean accountstatus;
	
	@Column(name = "loginstatus", length = 512)
	private Boolean loginstatus;
	
	@Column(name = "FCMtoken", length = 512)
	private String FCMtoken;
	
	@Column(name = "pending_img", length = 512)
	private String PendingImg;
	
	@Column(name = "approved_img", length = 512)
	private String ApprovedImg;
	
	@Column(name = "look_for", length = 512)
	private String lookFor;
	
	@Column(name = "min",length=30, nullable = false, columnDefinition = "int default 0")
	private Integer min=0;
	
	@Column(name = "sms",length=30, nullable = false, columnDefinition = "int default 0")
	private Integer sms=0;
	
	@Column(name = "redeem_min",length=30, nullable = false, columnDefinition = "int default 0")
	private Integer redeem_min=0;
	

	@Transient
	private Boolean isBloccked;
	
	
	@Column(name = "billing_expiry_date", length = 512)
	private Date billingExpiryDate;
	
	
	
	
	
	public Date getBillingExpiryDate() {
		return billingExpiryDate;
	}

	public void setBillingExpiryDate(Date billingExpiryDate) {
		this.billingExpiryDate = billingExpiryDate;
	}

	
	
	
	public Date getLastBillingDate() {
		return lastBillingDate;
	}

	public void setLastBillingDate(Date lastBillingDate) {
		this.lastBillingDate = lastBillingDate;
	}
	
	public Boolean getIsBloccked() {
		return isBloccked;
	}

	public void setIsBloccked(Boolean isBloccked) {
		this.isBloccked = isBloccked;
	}

	public Integer getRedeem_min() {
		return redeem_min;
	}

	public void setRedeem_min(Integer redeem_min) {
		this.redeem_min = redeem_min;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getScmnotification() {
		return scmnotification;
	}

	public void setScmnotification(String scmnotification) {
		this.scmnotification = scmnotification;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Date getLastlogouttime() {
		return lastlogouttime;
	}

	public void setLastlogouttime(Date lastlogouttime) {
		this.lastlogouttime = lastlogouttime;
	}

	public Date getRegisterdatetime() {
		return registerdatetime;
	}

	public void setRegisterdatetime(Date registerdatetime) {
		this.registerdatetime = registerdatetime;
	}

	public String getCustomloginstatus() {
		return customloginstatus;
	}

	public void setCustomloginstatus(String customloginstatus) {
		this.customloginstatus = customloginstatus;
	}

	public String getSipstatus() {
		return sipstatus;
	}

	public void setSipstatus(String sipstatus) {
		this.sipstatus = sipstatus;
	}

	public Boolean getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(Boolean accountstatus) {
		this.accountstatus = accountstatus;
	}

	public Boolean getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(Boolean loginstatus) {
		this.loginstatus = loginstatus;
	}

	public String getFCMtoken() {
		return FCMtoken;
	}

	public void setFCMtoken(String fCMtoken) {
		FCMtoken = fCMtoken;
	}

	public String getPendingImg() {
		if(PendingImg==null)
		{
			return PendingImg;
		}
		return PendingImg.replace("/home/centos/images/","http://api.gossipline.in/virtual/");
	}

	public void setPendingImg(String pendingImg) {
		PendingImg = pendingImg;
	}

	public String getApprovedImg() {
		if(ApprovedImg==null)
		{
			return ApprovedImg;
		}
		return ApprovedImg.replace("/home/centos/images/","http://api.gossipline.in/virtual/");
	}

	public void setApprovedImg(String approvedImg) {
		ApprovedImg = approvedImg;
	}

	public String getLookFor() {
		return lookFor;
	}

	public void setLookFor(String lookFor) {
		this.lookFor = lookFor;
	}

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

	
	
	
}
