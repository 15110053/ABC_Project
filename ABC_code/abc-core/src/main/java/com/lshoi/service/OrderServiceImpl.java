package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.CartDTO;
import com.lshoi.DTO.OrderAndCartDTO;
import com.lshoi.DTO.OrderDetailDTO;
import com.lshoi.DTO.OrderProductDTO;
import com.lshoi.DTO.ProductDTO;
import com.lshoi.DTO.UserDTO;
import com.lshoi.models.OrderDetail;
import com.lshoi.models.OrderProduct;
import com.lshoi.models.OrderStatus;
import com.lshoi.repositoty.OrderDAO;
import com.lshoi.utilities.UtilityConvertBetweenEntityAndDTO;

public class OrderServiceImpl implements OrderService{
	private OrderDAO orderDAO;
	
	public OrderServiceImpl(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	@Transactional
	@Override
	public boolean createOrderProduct(OrderAndCartDTO orderDTO) {
		try {
			int id = orderDAO.CreateOrderProduct(UtilityConvertBetweenEntityAndDTO.convertToOrderEntity(orderDTO.getOrderDTO()));
			if(id != 0) {
				for(int i=0; i<orderDTO.getCart().size(); i++) {
					CartDTO cartDTO = orderDTO.getCart().get(i);
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProduct(UtilityConvertBetweenEntityAndDTO.convertToProductEntity(cartDTO.getProductDTO()));
					orderDetail.setQuantity(cartDTO.getQuantity());
					OrderProduct orderProduct = new OrderProduct();
					orderProduct.setIdOrder(id);
					orderDetail.setOrder(orderProduct);
					orderDAO.addOrderDetail(orderDetail);
				}
				return true;
			}
			return false;
		}catch(Exception ex) {
			return false;
		} 
	}
	
	@Transactional
	@Override
	public List<OrderProductDTO> getOrderByUser(int userId) {
		List<OrderProductDTO> listOrderDTO = new ArrayList<>();
		List<OrderProduct> listOrder = orderDAO.getOrderByUser(userId);
		for(OrderProduct x : listOrder) {
			OrderProductDTO order = UtilityConvertBetweenEntityAndDTO.convertToOrderDTO(x);
			order.setConsumerDTO(null);
			listOrderDTO.add(order);
		}
		return listOrderDTO;
	}
	
	@Transactional
	@Override
	public List<OrderProductDTO> cancelOrder(OrderProductDTO orderDTO) {
		OrderProduct order = orderDAO.getOrderById(orderDTO.getIdOrder());
		if(order.getConsumer().getUserId() != orderDTO.getConsumerDTO().getUserId() || order.getStatus() != OrderStatus.NONE)
			return null;
		order.setStatus(OrderStatus.valueOf(orderDTO.getStatusDTO().name()));
		if(orderDAO.cancelOrder(order) == true)
			return getOrderByUser(orderDTO.getConsumerDTO().getUserId());
		return null;
	}
	
	@Transactional
	@Override
	public List<OrderDetailDTO> getOrderDetail(int orderId) {
		List<OrderDetail> listOrderDetail = orderDAO.getOrderDetail(orderId);
		List<OrderDetailDTO> listOrderDetailDTO = new ArrayList<>();
		for(OrderDetail x : listOrderDetail) {
			OrderDetailDTO orderDetailDTO = UtilityConvertBetweenEntityAndDTO.convertToOrderDetailDTO(x);
			//set consumer is null
			OrderProductDTO orderProductDTO = orderDetailDTO.getOrder();
			orderProductDTO.setConsumerDTO(null);
			
			//set product's category is null
			ProductDTO productDTO = orderDetailDTO.getProduct();
			productDTO.setSubCategoryDTO(null);
			//user DTO is null
			productDTO.setUserDTO(null);
			
			orderDetailDTO.setOrder(orderProductDTO);
			orderDetailDTO.setProduct(productDTO);
			listOrderDetailDTO.add(orderDetailDTO);
		}
		return listOrderDetailDTO;
	}

}
