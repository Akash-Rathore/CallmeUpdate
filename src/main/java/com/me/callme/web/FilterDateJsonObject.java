package com.me.callme.web;

import java.util.List;

import com.me.callme.model.Redeem;
import com.me.callme.model.RedeemResponse;

public class FilterDateJsonObject {

	    int iTotalRecords;
	    int iTotalDisplayRecords;
	    String sEcho;
	    String sColumns;
	    List<RedeemResponse> aaData;
	    
		public int getiTotalRecords() {
			return iTotalRecords;
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
		public List<RedeemResponse> getAaData() {
			return aaData;
		}
		public void setAaData(List<RedeemResponse> aaData) {
			this.aaData = aaData;
		}
		

	
}
