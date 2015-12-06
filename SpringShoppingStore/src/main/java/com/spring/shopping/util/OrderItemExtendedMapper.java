package com.spring.shopping.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.shopping.model.OrderItemExtended;

public class OrderItemExtendedMapper implements RowMapper<OrderItemExtended> {

	@Override
	public OrderItemExtended mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderItemExtended orderItem = new OrderItemExtended();
		orderItem.setOrderItemId(rs.getLong("OrderItem_Id"));
		orderItem.setOrderId(rs.getLong("Order_Id"));
		orderItem.setProductId(rs.getLong("Product_Id"));
		orderItem.setCreatedDate(rs.getDate("CreatedDate"));
		orderItem.setOrderStatus(rs.getString("Order_Status"));
		orderItem.setQuantity(rs.getInt("Quantity"));

		return orderItem;
	}

}
