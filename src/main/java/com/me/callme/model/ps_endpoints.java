package com.me.callme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ps_endpoints")

public class ps_endpoints {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="aors")
	private String aors; 

	@Column(name="auth")
	private String auth;
	
	@Column(name="context")
	private String context;
	
	
	@Column(name="disallow")
	private String disallow; 

	@Column(name="allow")
	private String allow;
	
	@Column(name="direct_media")
	private String direct_media;
	
	@Column(name="rtp_symmetric")
	private String rtp_symmetric; 

	@Column(name="message_context")
	private String message_contex;
	
	
	
	
	
	
	@Column(name="transport")
	private String transport;
	
	@Column(name="external_media_address")
	private String external_media_address;
	
	@Column(name="force_rport")
	private String force_rport;
	
	@Column(name="ice_support")
	private String ice_support;
	
	@Column(name="rewrite_contact")
	private String rewrite_contact;
	
	@Column(name="use_avpf")
	private String use_avpf;
	
	@Column(name="media_encryption")
	private String media_encryption;
	
	@Column(name="media_address")
	private String media_address;
	
	@Column(name="force_avp")
	private String force_avp;
	
	@Column(name="media_use_received_transport")
	private String media_use_received_transport;
	
	@Column(name="rtcp_mux")
	private String rctp_mux;
	
	
	
	
	
	
	

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getExternal_media_address() {
		return external_media_address;
	}

	public void setExternal_media_address(String external_media_address) {
		this.external_media_address = external_media_address;
	}

	public String getForce_rport() {
		return force_rport;
	}

	public void setForce_rport(String force_rport) {
		this.force_rport = force_rport;
	}

	public String getIce_support() {
		return ice_support;
	}

	public void setIce_support(String ice_support) {
		this.ice_support = ice_support;
	}

	public String getRewrite_contact() {
		return rewrite_contact;
	}

	public void setRewrite_contact(String rewrite_contact) {
		this.rewrite_contact = rewrite_contact;
	}

	public String getUse_avpf() {
		return use_avpf;
	}

	public void setUse_avpf(String use_avpf) {
		this.use_avpf = use_avpf;
	}

	public String getMedia_encryption() {
		return media_encryption;
	}

	public void setMedia_encryption(String media_encryption) {
		this.media_encryption = media_encryption;
	}

	public String getMedia_address() {
		return media_address;
	}

	public void setMedia_address(String media_address) {
		this.media_address = media_address;
	}

	public String getForce_avp() {
		return force_avp;
	}

	public void setForce_avp(String force_avp) {
		this.force_avp = force_avp;
	}

	public String getMedia_use_received_transport() {
		return media_use_received_transport;
	}

	public void setMedia_use_received_transport(String media_use_received_transport) {
		this.media_use_received_transport = media_use_received_transport;
	}

	public String getRctp_mux() {
		return rctp_mux;
	}

	public void setRctp_mux(String rctp_mux) {
		this.rctp_mux = rctp_mux;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAors() {
		return aors;
	}

	public void setAors(String aors) {
		this.aors = aors;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getDisallow() {
		return disallow;
	}

	public void setDisallow(String disallow) {
		this.disallow = disallow;
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getDirect_media() {
		return direct_media;
	}

	public void setDirect_media(String direct_media) {
		this.direct_media = direct_media;
	}

	public String getRtp_symmetric() {
		return rtp_symmetric;
	}

	public void setRtp_symmetric(String rtp_symmetric) {
		this.rtp_symmetric = rtp_symmetric;
	}

	public String getMessage_contex() {
		return message_contex;
	}

	public void setMessage_contex(String message_contex) {
		this.message_contex = message_contex;
	}
	
	
	
	

}
