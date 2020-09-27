package com.me.callme.service;

import java.util.List;

import com.me.callme.model.Redeem;
import com.me.callme.model.RedeemResponse;
import com.me.callme.repository.RedeemRepository;

public interface RedeemService {

	public List<RedeemResponse> redeemdate(String startdate , String enddate);

}
