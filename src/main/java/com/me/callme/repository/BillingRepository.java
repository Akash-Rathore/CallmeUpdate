package com.me.callme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>{

	@Query(value="from Billing r where  r.user_id=:user_id")
	List<Billing> findByUserId(Integer user_id,Pageable pageable);
	
}
