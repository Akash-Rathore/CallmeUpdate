package com.me.callme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;
import com.me.callme.model.UserMobile;

@Repository
public interface UserMobileResponsibilty extends JpaRepository<UserMobile, Integer> {

	
	@Query(value="from UserMobile r where  r.user_id=:user_id")
	
	List<UserMobile> findByUserId(Integer user_id);
}
