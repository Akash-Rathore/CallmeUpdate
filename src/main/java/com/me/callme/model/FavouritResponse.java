package com.me.callme.model;

public class FavouritResponse {
 
	private String favouriteuser;

	public FavouritResponse(String favouriteuser) {
		
		this.favouriteuser=favouriteuser;
		
	}
	
	
	public String getFavouriteuser() {
		return favouriteuser;
	}

	public void setFavouriteuser(String favouriteuser) {
		this.favouriteuser = favouriteuser;
	}

	@Override
	public String toString() {
		return "FavouritResponse [favouriteuser=" + favouriteuser + "]";
	}

	
  
	

}