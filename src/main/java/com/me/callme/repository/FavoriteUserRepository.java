package com.me.callme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.me.callme.model.FavouriteUser;

public interface FavoriteUserRepository extends JpaRepository<FavouriteUser, Long>{
	@Query(value = " from FavouriteUser c where c.favouriteby=:favouriteby and c.favouriteuser=:favouriteuser ")
	public FavouriteUser findByIdfavouriteto(@Param("favouriteby") Long blockedby,@Param("favouriteuser") String blockeduser);
}
