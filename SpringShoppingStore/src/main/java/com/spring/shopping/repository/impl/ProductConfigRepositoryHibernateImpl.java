package com.spring.shopping.repository.impl;

import java.util.List;

import com.spring.shopping.model.Product;
import com.spring.shopping.repository.ProductConfigRepository;

public class ProductConfigRepositoryHibernateImpl implements
		ProductConfigRepository {

	@Override
	public List<Product> readFeaturedProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product readProductById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> readProductByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveNewProduct(Product product, Long categoryId, Long subcategoryId){
		// TODO Auto-generated method stub
		return 0;
	}

}
