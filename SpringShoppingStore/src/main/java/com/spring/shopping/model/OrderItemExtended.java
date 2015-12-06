package com.spring.shopping.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemExtended implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long orderItemId;
	private Long orderId;
	private Long productId;
	private Product product;
	private int quantity;

	private java.util.Date createdDate;
	private String orderStatus;
	
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public BigDecimal getTotal() {
		double amount = 0.0;
		if (product != null) {
			amount += product.getPrice().doubleValue() * quantity;
		}
		return new BigDecimal(amount);
	}

	public void decrementQuantity() {
		quantity--;

	}

	@Override
	public String toString() {
		return "OrderItem [product=" + product + ", quantity=" + quantity
				+ ", getProduct()=" + getProduct() + ", getQuantity()="
				+ getQuantity() + ", getTotal()=" + getTotal()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}