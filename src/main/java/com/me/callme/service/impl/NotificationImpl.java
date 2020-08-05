package com.me.callme.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.riversun.fcm.FcmClient;
import org.riversun.fcm.model.EntityMessage;
import org.riversun.fcm.model.FcmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.callme.model.Billing;
import com.me.callme.model.Notification;
import com.me.callme.model.Product;
import com.me.callme.model.User;
import com.me.callme.repository.NotificationRepository;
import com.me.callme.repository.UserRepository;
import com.me.callme.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationImpl implements NotificationService {

	@Autowired
	private UserRepository mUserRepository;
	@Autowired
	private NotificationRepository mNotificationRepository;
	
	
	final String FCM_SERVER_KEY="AAAAugtPuOk:APA91bG4FMwneabU_pLReyP0CC4L1WhfrTRJ8Z9p5tIEJY5BoAGOaAqddn4taszY8rT4mMp44Kd4qk041PzbgREMO0Y3iRt0NGYhtvy8jJxdu1ZAlB2KUjxCraWkUfC5cUvfGL9EU05w";
	


	@Override
	public Notification notificationSubmitToFcm(Notification notification) {
		FcmClient client = new FcmClient();
		client.setAPIKey(FCM_SERVER_KEY);
		EntityMessage msg = new EntityMessage();
		// Set registration token that can be retrieved
		// from Android entity(mobile devices,browser front-end apps) when calling
		// FirebaseInstanceId.getInstance().getToken();
		msg.addRegistrationToken(notification.getDevice_id());
		// Add key value pair into payload
		msg.putStringData("message", notification.getMessage());
		long dateInMillis = System.currentTimeMillis();
		msg.putData("timestamp", dateInMillis);
		FcmResponse res = client.pushToEntities(msg);
		notification.setStatus(res.getSuccess());
		notification.setMessage_type(notification.getMessage_type());
		notification.setResult(res.getResult().toString());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
//		2020-07-06 18:41:51
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
		Date currentDate = calendar.getTime();
		notification.setDatetime(currentDate);
	    mNotificationRepository.save(notification);
		System.out.println(res);
		if (res.getResult() != null && res.getResult().get(0).getError() != null
				&& res.getResult().get(0).getError().equalsIgnoreCase("NotRegistered")) {

		User mUser=	mUserRepository.findById(Long.valueOf(notification.getPartyB_id())).get();
		mUser.setFCMtoken(null);
		mUserRepository.save(mUser);
		}
		return notification;
	}

	@Override
	public Notification notificationRedeem(Integer userId) {
		User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		String fcmToken=mUser.getFCMtoken();
		Notification mNotification=null;
		if(fcmToken!=null)
		{
			String message="We have received your redemption request. Same will be processed within next 48 working Hrs.";
			Notification	notification=new Notification();
			notification.setDevice_id(fcmToken);
			notification.setMessage(message);
			notification.setPartyB_id(userId);
			notification.setMessage_type("INCOMING_CALL");
			mNotification=notificationSubmitToFcm(notification);
			
		}
		return mNotification;
	}

	

	@Override
	public Notification notificationGeneral(Integer userId, String msg) {
		User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		return new Notification();
	}

	@Override
	public Notification notificationRecharge(Product mProduct,Billing mBilling) {
		User mUser=	mUserRepository.findById(Long.valueOf(mBilling.getUser_id())).get();
		String fcmToken=mUser.getFCMtoken();
		Notification mNotification=null;
		
		Integer TotalMin=mProduct.getMin()*mBilling.getQuantity();
		Integer TotalSms=mProduct.getSms()*mBilling.getQuantity();
		Integer TotalAmount=mBilling.getAmount();
		if(fcmToken!=null)
		{
			String message="Thanks for Subscribing GossipLine Pack. "+TotalMin+" Mins and "+TotalSms+" SMS has been added to your Gossipline Minutes \n" + 
					"and SMS Balance . Enjoy Now!";
			Notification	notification=new Notification();
			notification.setDevice_id(fcmToken);
			notification.setMessage(message);
			notification.setPartyB_id(mBilling.getUser_id());
			notification.setMessage_type("RECHARGE");
			mNotification=notificationSubmitToFcm(notification);
			
		}
		
		
		return mNotification;
	}
	
	
	

	@Override
	public Notification notificationProfileCheck(Integer partyA, Integer PartyB) {
		User mUserPartyA=	mUserRepository.findById(Long.valueOf(PartyB)).get();
		User mUserPartyB=	mUserRepository.findById(Long.valueOf(PartyB)).get();
		return new Notification();
	}

	@Override
	public Notification notificationAddFav(Integer userId) {
		User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		return new Notification();
	}

	



	
	
	
	@Override
	public Notification notificationNewUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notificationGetMinSms(Integer userId, Integer callMin, Integer rs, Integer sms) {
	User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		String fcmToken=mUser.getFCMtoken();
		Notification mNotification=null;
		if(fcmToken!=null)
		{
			String message="Congrats,  Rs. "+rs+" and "+sms+" messages has been added to your Redemption Wallet and balance SMS RESPECTIVELY for incoming call of "+callMin+" mins.";
			Notification	notification=new Notification();
			notification.setDevice_id(fcmToken);
			notification.setMessage(message);
			notification.setPartyB_id(userId);
			notification.setMessage_type("INCOMING_CALL");
			mNotification=notificationSubmitToFcm(notification);
			
		}
		
		
		return mNotification;
	}
	
	
	@Override
	public Notification generateNotifyForApprovedImage(Integer userId) {
	User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		String fcmToken=mUser.getFCMtoken();
		Notification mNotification=null;
		if(fcmToken!=null)
		{
			String message="Dear Subscriber, Your Profile Picture has been approved. Now enjoy making friends on GossipLine. Thank You";
			Notification	notification=new Notification();
			notification.setDevice_id(fcmToken);
			notification.setMessage(message);
			notification.setPartyB_id(userId);
			notification.setMessage_type("INCOMING_CALL");
			mNotification=notificationSubmitToFcm(notification);
			
		}
		
		
		return mNotification;
	}
	
	
	@Override
	public Notification generateNotify(Integer userId,String msg,String type) {
	User mUser=	mUserRepository.findById(Long.valueOf(userId)).get();
		String fcmToken=mUser.getFCMtoken();
		Notification mNotification=null;
		if(type==null || type.equals("")) {
			type = "INCOMING_CALL";
		}
		
		if(fcmToken!=null)
		{
//			String message="Dear Subscriber, Your Profile Picture has been approved. Now enjoy making friends on GossipLine. Thank You";
			Notification	notification=new Notification();
			notification.setDevice_id(fcmToken);
			notification.setMessage(msg);
			notification.setPartyB_id(userId);
			notification.setMessage_type(type);
			mNotification=notificationSubmitToFcm(notification);
			
		}
		
		
		return mNotification;
	}


}
