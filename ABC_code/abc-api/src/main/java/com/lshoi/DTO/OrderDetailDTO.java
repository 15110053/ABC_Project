package com.lshoi.DTO;

public class OrderDetailDTO {
	
	private OrderProductDTO order;
	private ProductDTO product;
	private int quantity;
	
	public OrderDetailDTO() {
		super();
	}
	
	public OrderProductDTO getOrder() {
		return order;
	}

	public void setOrder(OrderProductDTO order) {
		this.order = order;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
