package com.spring.shopping.repository;

import java.util.List;

import com.spring.shopping.model.Product;

public interface ProductConfigRepository {

	List<Product> readFeaturedProducts();

	Product readProductById(Long productId);
	
	List<Product> readProductByCustomerId(Long customerId);
	
	public int saveNewProduct(Product product, Long categoryId, Long subcategoryId);
	

}
