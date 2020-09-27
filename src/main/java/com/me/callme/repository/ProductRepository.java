package com.me.callme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.callme.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  
	
	@Transactional
    @Modifying(clearAutomatically=true)
    @Query(value ="UPDATE Product p set p.amount=:amount , p.country=:country , p.min=:min , p.sms=:sms , p.status=:status , p.unit=:unit where p.id=:package_id")
	public int getUpdatePackage(@Param("package_id")long id , @Param("amount") Integer amount , @Param("country") Integer country ,@Param("min") Integer min , @Param("sms") Integer sms , @Param("status") Integer status,@Param("unit") String unit);
	

	
	
}
