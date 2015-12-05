package com.spring.shopping.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public long saveNewProduct(final Product product, final Long categoryId, final Long subcategoryId) {
		final String sql = "insert into product ( Name, Featured, Price, Available, Category_Id, Description, SubCategory_Id, Manufacturer, Customer_Id )"
				+ "values (?,?,?,?,?,?,?,?,?)"; 
		
		Object[] args = new Object[] { product.getName(),product.getFeatured(), product.getPrice(), product.getAvailable(), categoryId, product.getDescription(),
				subcategoryId, product.getManufacturer(), product.getCustomerId() };
		
		
		final String name = "Rob";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int update2 = jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"Product_Id"});
		            int ind = 1;
		            ps.setString(ind++, product.getName());
		            ps.setInt(ind++, product.getFeatured());
		            ps.setBigDecimal(ind++, product.getPrice());
		            ps.setInt(ind++, product.getAvailable());
		            ps.setLong(ind++, categoryId);
		            ps.setString(ind++, product.getDescription());
		            ps.setLong(ind++,subcategoryId);
		            ps.setString(ind++, product.getManufacturer());
		            ps.setLong(ind++, product.getCustomerId());
		            
		            return ps;
		        }
		    },
		    keyHolder);
		
		Number key = keyHolder.getKey();
		
		return key.longValue();
		
	}
}
