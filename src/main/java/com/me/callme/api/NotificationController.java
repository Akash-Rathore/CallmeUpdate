package com.me.callme.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.me.callme.model.Billing;
import com.me.callme.model.CustomNotificationRecharge;
import com.me.callme.model.Notification;
import com.me.callme.model.Product;
import com.me.callme.repository.NotificationRepository;
import com.me.callme.service.NotificationService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/notification")
@Api(value = "NotificationApi", tags = { "Notification Api" })
@CrossOrigin(origins = "*")
public class NotificationController {

	@Autowired
	private NotificationService mNotificationService;
	@Autowired
	private NotificationRepository mNotificationRepository;
	
	  @GetMapping("/getNodeWithUserId/{UserId}/") 
	  public List<Notification> getNoteByUserId(@PathVariable(value = "UserId") Integer userid) { 
		  
		  
		  Pageable request = PageRequest.of(0, 10, Sort.Direction.DESC, "id");


		  
		  return   mNotificationRepository.findByUserId( userid,request); 
		  }
	
	
	@PostMapping("/submit")
	public Notification SubmitNotication(@RequestBody Notification mNotification) {
		return mNotificationService.notificationSubmitToFcm(mNotification);
		
	}
	
	@PostMapping("/Recharge")
	public Notification RechargeNotification(@RequestBody CustomNotificationRecharge mCustomNotificationRecharge) {
		return mNotificationService.notificationRecharge(mCustomNotificationRecharge.getmProduct(),mCustomNotificationRecharge.getmBilling());
	}
	
	
	@PostMapping("/incomingCall")
	public String NotificationIncomingCall(@RequestParam Integer userId,Integer callingMin,Integer Sms,Integer rs) {
		return mNotificationService.notificationGetMinSms(userId, callingMin, rs,Sms).toString();
	}
	
	
	@PostMapping("/generateNotification")
	public Notification generateNotification(@RequestParam Integer userId, @RequestParam String message) {
		return mNotificationService.generateNotify(userId, message, "MESSAGE");
	}
}
