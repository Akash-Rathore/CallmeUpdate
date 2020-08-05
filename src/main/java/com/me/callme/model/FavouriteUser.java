package com.me.callme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "favourite_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class FavouriteUser implements Serializable {
	
	private static final long serialVersionUID = 7409023952139972411L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Long id;

		@Column(name = "favourite_by", length = 512)
		private Long favouriteby;
		
		@Column(name = "favourite_user", length = 512)
		private String favouriteuser;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getFavouriteby() {
			return favouriteby;
		}

		public void setFavouriteby(Long favouriteby) {
			this.favouriteby = favouriteby;
		}

		public String getFavouriteuser() {
			return favouriteuser;
		}

		public void setFavouriteuser(String favouriteuser) {
			this.favouriteuser = favouriteuser;
		}
		

}

