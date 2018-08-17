package com.lshoi.DTO;

import java.util.List;

public class OrderAndCartDTO {

	private OrderProductDTO orderDTO;
	private List<CartDTO> cart;
	
	public OrderProductDTO getOrderDTO() {
		return orderDTO;
	}
	public void setOrderDTO(OrderProductDTO orderDTO) {
		this.orderDTO = orderDTO;
	}
	public List<CartDTO> getCart() {
		return cart;
	}
	public void setCart(List<CartDTO> cart) {
		this.cart = cart;
	}
	
}
