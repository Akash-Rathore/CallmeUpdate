package com.me.callme.utils;

import com.me.callme.model.User;
import com.me.callme.repository.UserRepository;
import com.me.callme.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class NotificationScheduler {
	private static final Logger  logger = LoggerFactory.getLogger(NotificationScheduler.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;

//    @Scheduled(cron = "0/10 * * * * ?") // Every 10 seconds
    @Scheduled(cron = "0 0 10 * * *", zone = "Asia/Kolkata") // Every day at 10 AM
    public void notificationScheduler() {
        logger.info("Scheduler called");
        Iterable<User> allUsers = userRepository.findAll();
        LocalDate date = LocalDate.now();
        LocalDate uDate = null;

        String msg = "";

        for(User user: allUsers) {
            // TODO: Code to send the notification based on the conditions...
            logger.info("user data :- " + user);
            if(user.getMin().intValue() <= 2) {
            	logger.info("if user data :- " + user);
            	notificationService.generateNotify(Integer.parseInt(user.getUserId().toString() + ""), "Dear Subscriber, Your GossipLine account balance is low. Kindly reacharge and GET 100 days Validity NO...", "MESSAGE");
            }
        }

    }
    
    //@Scheduled(cron = "0/12 * * * * ?") // Every 12 seconds
    @Scheduled(cron = "0 0 11 * * *", zone = "Asia/Kolkata") // Every day at 10.30 AM
    public void notificationScheduler2() {
        logger.info("Scheduler called");
        Iterable<User> allUsers = userRepository.findAll();
        LocalDate date = LocalDate.now();
        LocalDate uDate = null;

        String msg = "";

        for(User user: allUsers) {
            // TODO: Code to send the notification based on the conditions...
            logger.info("user data :- " + user);
            if(user.getBillingExpiryDate() != null) {
	            uDate = (user.getBillingExpiryDate()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            if(Period.between(date, uDate).getDays() > 0 && Period.between(date, uDate).getDays() < 3 && !Period.between(date, uDate).isNegative()) {
	            	notificationService.generateNotify(Integer.parseInt(user.getUserId().toString() + ""), "Dear Subscriber, Your GossipLine account Validity will expire soon. Kindly reacharge and GET 100 days Validity NO...","MESSAGE");
	            }
            }
        }

    }
}
