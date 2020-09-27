package com.me.callme.model;




public class BlockerResponse {
	
	private String blocked_user;

	
	public BlockerResponse(String blocked_user)
	{
		this.blocked_user=blocked_user;
	}
	
	public String getBlocked_user() {
		return blocked_user;
	}

	public void setBlocked_user(String blocked_user) {
		this.blocked_user = blocked_user;
	}

	@Override
	public String toString() {
		return "BlockerResponse [blocked_user=" + blocked_user + "]";
	}
	
   





	


}
