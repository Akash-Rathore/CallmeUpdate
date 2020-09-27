package com.me.callme.web;

import java.util.List;

public class UserJsonObject<User> {
	
	    int iTotalRecords;
	    int iTotalDisplayRecords;
	    String sEcho;
	    String sColumns;
	    List<User> aaData;
	    
		
		
		public void setAaData(List<User> aaData) {
			this.aaData = aaData;
		}
		public void setiTotalRecords(int iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}
		public int getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}
		public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}
		public String getsEcho() {
			return sEcho;
		}
		public void setsEcho(String sEcho) {
			this.sEcho = sEcho;
		}
		public String getsColumns() {
			return sColumns;
		}
		public void setsColumns(String sColumns) {
			this.sColumns = sColumns;
		}
		
	    
	    
	    

}
