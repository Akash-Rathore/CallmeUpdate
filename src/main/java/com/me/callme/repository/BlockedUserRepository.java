package com.me.callme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.callme.model.BlockedUser;
import com.me.callme.model.FavouriteUser;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long>{
	
	@Query(value = " from BlockedUser c where c.blockedby=:blockedby and c.blockeduser=:blockeduser ")
	public BlockedUser findByIdblockedto(@Param("blockedby") Long blockedby,@Param("blockeduser") String blockeduser);
	
	@Query(value= " from BlockedUser c where c.blockedby=:blockedby ")
	public List<BlockedUser> getAllBlockedUserById(@Param("blockedby") Long blockedby);
	
	
}
