package com.me.callme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.callme.model.Billing;
import com.me.callme.model.Product;

@Repository
public interface productRepository extends JpaRepository<Product, Long>{

}
