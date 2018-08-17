package com.lshoi.DTO;

import java.util.Date;

public class OrderProductDTO {

	private int idOrder;
	private Date orderDate = new Date();
	private double price;
	private String content;
	private OrderStatusDTO statusDTO;
	private UserDTO consumerDTO;
	private String fullName;
	private String address;
	private String phoneNumber;
	public OrderProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public OrderStatusDTO getStatusDTO() {
		return statusDTO;
	}
	public void setStatusDTO(OrderStatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
	public UserDTO getConsumerDTO() {
		return consumerDTO;
	}
	public void setConsumerDTO(UserDTO consumerDTO) {
		this.consumerDTO = consumerDTO;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
