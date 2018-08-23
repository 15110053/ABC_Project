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
	public List<OrderDetailDTO> getOrderDetailByProducer(int idProducer);
	public boolean inprocessOrderDetail(OrderDetailDTO orderDetailDTO);
	//public List<OrderDetailDTO>	transferingOrderDetail(OrderDetailDTO orderDetailDTO);
	public boolean failOrderDetail(OrderDetailDTO orderDetailDTO);
	public List<OrderDetailDTO> getInprocessOrderDetailByProducer(int idProducer);
	public List<OrderDetailDTO> getFailOrderDetailByProducer(int idProducer);
}
