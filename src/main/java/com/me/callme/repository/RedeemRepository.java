package com.me.callme.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;
import com.me.callme.model.Redeem;
import com.me.callme.model.RedeemResponse;
import com.me.callme.model.User;

@Repository
public interface RedeemRepository  extends JpaRepository<Redeem, Integer>{

	@Query(value="from Redeem r where  r.user_id=:user_id order by id desc ")
	List<Redeem> findByUserId(Integer user_id,Pageable pageable);
	
	//	
	   //userId , m.username , r.mobile , r.amount , r.payment_type
		
	
	//@Query(value="select s.user_id from go_redeem r inner join users s on r.user_id=s.user_id  where r.date_time between ?1 and ?2 group by s.username"  , nativeQuery=true)
	//public List<RedeemResponse> redeemdate(String startdate, String enddate);
	
	@Query(value="SELECT  new com.me.callme.model.RedeemResponse(m.userId , m.username , r.mobile , r.amount , r.payment_type) FROM User m JOIN m.redeem r where r.date_time BETWEEN :startdate AND :enddate GROUP BY m.username")
	public  List<RedeemResponse> redeemdate(@Param("startdate")Date startdate, @Param("enddate")Date enddate);

	
}
