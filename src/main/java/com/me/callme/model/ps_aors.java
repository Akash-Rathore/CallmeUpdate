package com.me.callme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ps_aors")
public class ps_aors {
	public enum StatusEnum {
	    yes, no
	}

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "max_contacts")
	private Integer max_contacts;

	@Column(name = "remove_existing")
	private String remove_existing;
	
	
	@Column(name = "qualify_frequency")
	private Integer qualify_frequency;
	
	
	@Column(name = "qualify_timeout")
	private Float qualify_timeout;
	
	

	public String getRemove_existing() {
		return remove_existing;
	}

	public void setRemove_existing(String remove_existing) {
		this.remove_existing = remove_existing;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMax_contacts() {
		return max_contacts;
	}

	public void setMax_contacts(Integer max_contacts) {
		this.max_contacts = max_contacts;
	}

	public Integer getQualify_frequency() {
		return qualify_frequency;
	}

	public void setQualify_frequency(Integer qualify_frequency) {
		this.qualify_frequency = qualify_frequency;
	}


	public Float getQualify_timeout() {
		return qualify_timeout;
	}

	public void setQualify_timeout(Float qualify_timeout) {
		this.qualify_timeout = qualify_timeout;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "authenticate_qualify")
	private StatusEnum status = StatusEnum.no;

	public StatusEnum getStatus() {
	    return status;
	}

	public void setStatus(StatusEnum status) {
	    this.status = status;
	}
	
	
}
