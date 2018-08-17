package com.lshoi.service;

import java.util.List;

import com.lshoi.DTO.OrderAndCartDTO;
import com.lshoi.DTO.OrderDetailDTO;
import com.lshoi.DTO.OrderProductDTO;

public interface OrderService {
	public boolean createOrderProduct(OrderAndCartDTO orderAndCartDTO);
	public List<OrderProductDTO> getOrderByUser(int userId);
	public List<OrderProductDTO> cancelOrder(OrderProductDTO orderDTO);
	public List<OrderDetailDTO> getOrderDetail(int orderId);
}
