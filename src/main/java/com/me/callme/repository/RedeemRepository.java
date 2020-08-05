package com.me.callme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;
import com.me.callme.model.Redeem;
import com.me.callme.model.User;

@Repository
public interface RedeemRepository  extends JpaRepository<Redeem, Integer>{

	@Query(value="from Redeem r where  r.user_id=:user_id order by id desc ")
	List<Redeem> findByUserId(Integer user_id,Pageable pageable);
	
}
