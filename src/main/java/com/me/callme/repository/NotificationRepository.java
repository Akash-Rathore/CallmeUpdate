package com.me.callme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;
import com.me.callme.model.BlockedUser;
import com.me.callme.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

	
	@Query(value="from Notification r where  r.partyB_id=:user_id")
	List<Notification> findByUserId(Integer user_id,Pageable pageable);
	
    public List<Notification> findAllByOrderByIdDesc();

}
