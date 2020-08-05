package com.me.callme.service;

import com.me.callme.model.Billing;
import com.me.callme.model.Notification;
import com.me.callme.model.Product;

public interface NotificationService {

	public Notification notificationSubmitToFcm(Notification notification);
	
	public Notification notificationRedeem(Integer userId);
	
	public Notification notificationGetMinSms(Integer userId,Integer callMin, Integer rs,Integer sms);
	
	//public Notification notificationGetSms(Integer userId,Integer callMin, Integer sms);

	public Notification notificationGeneral(Integer userId, String msg);
	
	public Notification notificationRecharge(Product mProduct,Billing mBilling);
	
	public Notification notificationProfileCheck(Integer partyA,Integer PartyB);
	
	public Notification notificationAddFav(Integer userId);
	
	public Notification notificationNewUser(Integer userId);

	public Notification generateNotifyForApprovedImage(Integer userId);

	public Notification generateNotify(Integer userId, String msg, String type);
	
	
	
}
