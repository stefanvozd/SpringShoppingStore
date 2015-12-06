package com.spring.shopping.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.shopping.model.AddressForm;
import com.spring.shopping.model.Customer;
import com.spring.shopping.model.Order;
import com.spring.shopping.model.OrderItemExtended;
import com.spring.shopping.model.Product;

public interface OrderService {
	public Order createOrder(CartService cartService, 			Customer customer, AddressForm address, HttpServletRequest request) throws ParseException;
	public List<Product> getAllOrderItems(Order order);
	
	public List<Order> getAllOrdersForCustomer(Customer customer);
	
	Order getOrderById(Long orderId);

	public List<OrderItemExtended> getAllOrdersForProduct(Long productId);
	
	List<Order> getAllPendingOrders();
	
	public Order createOrder(CartService cartService, Customer customer, AddressForm address,
			HttpServletRequest request, String transactionId, String mappStatus) throws ParseException;
	
	void updateOrder(Order order);
	
	Order createOrder(BigDecimal orderTotal, String sellPhoneNumber, HttpServletRequest request, String transactionId, String string) throws ParseException;
}
