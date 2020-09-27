package com.me.callme.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	

	@Column(name = "role", length = 512)
	private String role;
	
	@Column(name = "email", length = 512)
	private String email;

	@Column(name = "password", length = 512)
	private String password;
	
	@Column(name = "notifi_rights", length = 512)
	private String notifi_rights;
	
	@Column(name = "pic_approval_rights", length = 512)
	private String pic_approval_rights;
	
	@Column(name = "redem_rights", length = 512)
	private String redem_rights;
  
	
	@OneToMany(targetEntity = BlockedUser.class , cascade = CascadeType.ALL)
	 @JoinColumn(name ="blocked_by" , referencedColumnName = "user_id")
	 private List<BlockedUser> blockedUser;
	
	 @OneToMany(targetEntity = FavouriteUser.class , cascade = CascadeType.ALL)
	 @JoinColumn(name ="favourite_by" , referencedColumnName = "user_id")
	 private List<FavouriteUser> favouriteUser;
	
     @OneToMany(targetEntity = Redeem.class , cascade = CascadeType.ALL)
	 @JoinColumn(name ="user_id" , referencedColumnName = "user_id")
	 private List<Redeem> redeem;
		
		
	
	
	public User()
	{
		
	}
	
	public User(long userId2, String username2, String gender2, String city2, String mobile2, String password,String notifi_rights,String pic_approval_rights,String redem_rights) {
		
		this.userId=userId2;
		this.username=username2;
		this.gender=gender2;
		this.city=city2;
		this.mobile=mobile2;
		this.password=password;
	    this.notifi_rights=notifi_rights;
	    this.pic_approval_rights=pic_approval_rights;
	    this.redem_rights=redem_rights;
	}
	
	public User(long userId2, String username2, String gender2, String city2, String mobile2, String password) {
		this.userId=userId2;
		this.username=username2;
		this.gender=gender2;
		this.city=city2;
		this.mobile=mobile2;
		this.password=password;
	    
	}

	public User(String username, String role, String email, String mobile, String city, String password,String gender,String notifi_rights,String pic_approval_rights,String redem_rights) {
		
		this.username=username;
		this.role=role;
		this.email=email;
		this.mobile=mobile;
		this.city=city;
		this.password=password;
	    this.gender=gender;
	    this.notifi_rights=notifi_rights;
	    this.pic_approval_rights=pic_approval_rights;
	    this.redem_rights=redem_rights;
	}
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<BlockedUser> getBlockedUser() {
		return blockedUser;
	}
	public void setBlockedUser(List<BlockedUser> blockedUser) {
		this.blockedUser = blockedUser;
	}
	public List<FavouriteUser> getFavouriteUser() {
		return favouriteUser;
	}
	public void setFavouriteUser(List<FavouriteUser> favouriteUser) {
		this.favouriteUser = favouriteUser;
	}
	public List<Redeem> getRedeem() {
		return redeem;
	}
	public void setRedeem(List<Redeem> redeem) {
		this.redeem = redeem;
	}

	 public String getEmail() {
			return email;
	 }
	 public void setEmail(String email) {
			this.email = email;
	 }

	 public String getPassword() {
			return password;
	 }
	 public void setPassword(String password) {
			this.password = password;
	 }
	public String getNotifi_rights() {
		return notifi_rights;
	}
	public void setNotifi_rights(String notifi_rights) {
		this.notifi_rights = notifi_rights;
	}
	public String getPic_approval_rights() {
		return pic_approval_rights;
	}
	public void setPic_approval_rights(String pic_approval_rights) {
		this.pic_approval_rights = pic_approval_rights;
	}
	public String getRedem_rights() {
		return redem_rights;
	}
	public void setRedem_rights(String redem_rights) {
		this.redem_rights = redem_rights;
	}

	
	
	
}
