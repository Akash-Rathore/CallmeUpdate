package com.me.callme.service;

import java.util.List;
import java.util.Optional;
import com.me.callme.model.Product;

public interface ProductService {
 
	public List<Product> getAllProduct();
	public void newPackage(Product product);
	public int getUpdatePackage(Product product);
	public Optional<Product> getSinglePackage(long singleRecord);
}
