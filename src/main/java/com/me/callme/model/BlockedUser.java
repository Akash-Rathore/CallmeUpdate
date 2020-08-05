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

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Table(name = "blocked_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class BlockedUser implements Serializable {
	
	private static final long serialVersionUID = 7409023952139972411L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Long id;

		@Column(name = "blocked_by", length = 512)
		private Long blockedby;
		
		@Column(name = "blocked_user", length = 512)
		private String blockeduser;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getBlockedby() {
			return blockedby;
		}

		public void setBlockedby(Long blockedby) {
			this.blockedby = blockedby;
		}

		public String getBlockeduser() {
			return blockeduser;
		}

		public void setBlockeduser(String blockeduser) {
			this.blockeduser = blockeduser;
		}

		
}
