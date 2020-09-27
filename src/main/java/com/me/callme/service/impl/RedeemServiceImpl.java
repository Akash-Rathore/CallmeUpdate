package com.me.callme.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.me.callme.model.Redeem;
import com.me.callme.model.RedeemResponse;
import com.me.callme.repository.RedeemRepository;
import com.me.callme.service.RedeemService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedeemServiceImpl implements RedeemService {

	@Autowired
	private RedeemRepository redeemRepository;

	
	  @Override public List<RedeemResponse> redeemdate(String startdate, String enddate) 
	  { 
		  
		  String pattern = "yyyy-MM-dd";
		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		  
		  try {
			
			  Date startdate1 = simpleDateFormat.parse(startdate); 
			  Date enddate2 = simpleDateFormat.parse(enddate); 
			  return redeemRepository.redeemdate(startdate1,enddate2); 
		  }catch(Exception exception) {
			  
		  }
		  
		 return null; 		
     }
	 
}
