package com.me.callme.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.me.callme.model.Product;
import com.me.callme.repository.ProductRepository;
import com.me.callme.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	@Override
	public List<Product> getAllProduct() {
		List<Product> product = productRepository.findAll();
		return product;
	}
	@Override
	public void newPackage(Product product) {
		productRepository.save(product);
	 }
	@Override
	public int getUpdatePackage(Product product) {
		return 	productRepository.getUpdatePackage(product.getId() , product.getAmount() , product.getCountry() , product.getMin() , product.getSms() , product.getStatus(), product.getUnit());	
	}
	
	@Override
	public Optional<Product> getSinglePackage(long singleRecord) {
		return productRepository.findById(singleRecord) ;
	}

	
	
}
