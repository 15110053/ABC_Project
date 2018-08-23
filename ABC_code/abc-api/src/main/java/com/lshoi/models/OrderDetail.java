package com.lshoi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class OrderDetail implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name = "idOrder")
	private OrderProduct order;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "idProduct")
	private Product product;
	
	@Column
	private int quantity;
	
	@Column
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public OrderDetail() {
		super();
	}

	public OrderProduct getOrder() {
		return order;
	}

	public void setOrder(OrderProduct order) {
		this.order = order;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
}
