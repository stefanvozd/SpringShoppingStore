package com.spring.shopping.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.spring.shopping.model.Product;
import com.spring.shopping.repository.ProductConfigRepository;
import com.spring.shopping.util.ProductMapper;

@Repository
public class ProductConfigRepositoryJdbcImpl implements ProductConfigRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Product> readFeaturedProducts() {
		int number = 1;
		String sql = "SELECT * FROM product p where p.Featured= :number";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"number", number);
		List<Product> productsList = namedParameterJdbcTemplate.query(sql,
				sqlParameterSource, new ProductMapper());
		return productsList;
	}

	@Override
	public Product readProductById(Long productId) {
		String sql = "SELECT * FROM product p where p.Product_Id= :productId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"productId", productId);
		Product product = namedParameterJdbcTemplate.queryForObject(sql,
				sqlParameterSource, new ProductMapper());
		return product;
	}

	
	@Override
	public List<Product> readProductByCustomerId(Long customerId) {
		String sql = "SELECT * FROM product p where p.Customer_Id= :customerId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"customerId", customerId);
		List<Product> productsList = namedParameterJdbcTemplate.query(sql,
				sqlParameterSource, new ProductMapper());
		return productsList;
	}

	@Override
	public int saveNewProduct(Product product, Long categoryId, Long subcategoryId) {
		String sql = "insert into product ( Name, Featured, Price, Available, Category_Id, Description, SubCategory_Id, Manufacturer, Customer_Id )"
				+ "values (?,?,?,?,?,?,?,?,?)"; 
		
		Object[] args = new Object[] { product.getName(),product.getFeatured(), product.getPrice(), product.getAvailable(), categoryId, product.getDescription(),
				subcategoryId, product.getManufacturer(), product.getCustomerId() };
		
		int update = jdbcTemplate.update(sql, args);
		
		return update;
		
	}
}
