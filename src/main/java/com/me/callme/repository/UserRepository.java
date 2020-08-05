package com.me.callme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.callme.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value="from User m where m.mobile = :mobile")
	User findByMobile(@Param("mobile") String mobile);
	
	@Query(value="from User m where m.username = :username")
	User findByUsername(@Param("username") String username);
	
	//@Query("from User m where m.accountstatus =true and m.loginstatus=true and m.userId=userId")
	//List<User> findByAccountstatusLoginstatusUserId(Long userId);
	
	@Query(value="from User m where m.accountstatus =true and m.loginstatus=true and m.userId=userId")
	User findByAccountstatusLoginstatusUserId(Long userId);
	
	//@Query("from User m where m.accountstatus =true and loginstatus=true")
	public Iterable<User> findAll(Pageable page);
	
	
	//@Query("from User m where m.accountstatus =true and loginstatus=true")
	//public Iterable<User> findAllByAccountstatusLoginstatus(Boolean status,Boolean status1,Pageable page);
}
