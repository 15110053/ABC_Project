package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.CartDTO;
import com.lshoi.DTO.OrderAndCartDTO;
import com.lshoi.DTO.OrderDetailDTO;
import com.lshoi.DTO.OrderProductDTO;
import com.lshoi.DTO.OrderStatusDTO;
import com.lshoi.DTO.ProductDTO;
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
					orderDetail.setStatus(OrderStatus.NONE);
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
		boolean result = true;
		OrderProduct order = orderDAO.getOrderById(orderDTO.getIdOrder());
		if(order.getConsumer().getUserId() != orderDTO.getConsumerDTO().getUserId() || order.getStatus() != OrderStatus.NONE)
			return null;
		order.setStatus(OrderStatus.CANCEL);
		if(orderDAO.changeOrderStatus(order) == true) {
			List<OrderDetailDTO> listOrderDetail = getOrderDetail(orderDTO.getIdOrder());
			for(OrderDetailDTO x : listOrderDetail) {
				x.setStatusDTO(OrderStatusDTO.CANCEL);
				if(orderDAO.changeOrderDetailStatus(UtilityConvertBetweenEntityAndDTO.convertToOrderDetailEntity(x)) == false) {
					result = false;
				}
			}
			if(result == true)
				return getOrderByUser(orderDTO.getConsumerDTO().getUserId());
		}
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
	
	@Transactional
	@Override
	public List<OrderDetailDTO> getOrderDetailByProducer(int idProducer) {
		List<OrderDetail> listOrderDetailByProducer = orderDAO.getWaitingOrderDetailByProducer(idProducer);
		List<OrderDetailDTO> listOrderDetailByProducerDTO = new ArrayList<>();
		if(listOrderDetailByProducer == null) {
			return listOrderDetailByProducerDTO;
		}
		for(OrderDetail x : listOrderDetailByProducer) {
			OrderDetailDTO orderDetail = UtilityConvertBetweenEntityAndDTO.convertToOrderDetailDTO(x);
			//set consumer as null
			OrderProductDTO orderProduct = orderDetail.getOrder();
			orderProduct.setConsumerDTO(null);
			
			//set subcategory and user as null
			ProductDTO product = orderDetail.getProduct();
			product.setSubCategoryDTO(null);
			product.setUserDTO(null);
			
			orderDetail.setOrder(orderProduct);
			orderDetail.setProduct(product);
			listOrderDetailByProducerDTO.add(orderDetail);
		}
		return listOrderDetailByProducerDTO;
	}
	
	@Transactional
	@Override
	public List<OrderDetailDTO> getInprocessOrderDetailByProducer(int idProducer) {
		List<OrderDetail> listOrderDetailByProducer = orderDAO.getInprocessOrderDetailByProducer(idProducer);
		List<OrderDetailDTO> listOrderDetailByProducerDTO = new ArrayList<>();
		if(listOrderDetailByProducer == null) {
			return listOrderDetailByProducerDTO;
		}
		for(OrderDetail x : listOrderDetailByProducer) {
			OrderDetailDTO orderDetail = UtilityConvertBetweenEntityAndDTO.convertToOrderDetailDTO(x);
			//set consumer as null
			OrderProductDTO orderProduct = orderDetail.getOrder();
			orderProduct.setConsumerDTO(null);
			
			//set subcategory and user as null
			ProductDTO product = orderDetail.getProduct();
			product.setSubCategoryDTO(null);
			product.setUserDTO(null);
			
			orderDetail.setOrder(orderProduct);
			orderDetail.setProduct(product);
			listOrderDetailByProducerDTO.add(orderDetail);
		}
		return listOrderDetailByProducerDTO;
	}

	@Transactional
	@Override
	public boolean inprocessOrderDetail(OrderDetailDTO orderDetailDTO) {
		boolean result = true;
		List<OrderDetail> listOrderDetail = orderDAO.getOrderDetailByProducer(orderDetailDTO.getOrder().getIdOrder(), orderDetailDTO.getProduct().getUserDTO().getUserId());
		if(listOrderDetail.get(0).getStatus() != OrderStatus.NONE)
			return false;
		for(int i=0; i<listOrderDetail.size();i++) {
			listOrderDetail.get(i).setStatus(OrderStatus.INPROCESS);
			if(orderDAO.changeOrderDetailStatus(listOrderDetail.get(i)) == false)
				result = false;
		}
		if(result == true) {
			OrderProduct order = orderDAO.getOrderById(orderDetailDTO.getOrder().getIdOrder());
			order.setStatus(OrderStatus.INPROCESS);
			if(orderDAO.changeOrderStatus(order))
				return true;
		}
		return false;
	}
	
	/*@Transactional
	@Override
	public List<OrderDetailDTO>	transferingOrderDetail(OrderDetailDTO orderDetailDTO) {
		OrderDetail orderDetail = orderDAO.getAnOrderDetail(orderDetailDTO.getOrder().getIdOrder(), orderDetailDTO.getProduct().getIdProduct());
		if(orderDetail.getStatus() != OrderStatus.INPROCESS)
			return null;
		orderDetail.setStatus(OrderStatus.TRANSFERRING);
		if(orderDAO.changeOrderDetailStatus(orderDetail) == true)
			return getOrderDetailByProducer(orderDetailDTO.getProduct().getUserDTO().getUserId());
		return null;
	}*/
	
	@Transactional
	@Override
	public boolean failOrderDetail(OrderDetailDTO orderDetailDTO) {
		boolean result = true;
		List<OrderDetail> listOrderDetail = orderDAO.getOrderDetailByProducer(orderDetailDTO.getOrder().getIdOrder(), orderDetailDTO.getProduct().getUserDTO().getUserId());
		for(int i=0; i<listOrderDetail.size();i++) {
			listOrderDetail.get(i).setStatus(OrderStatus.FAIL);
			if(orderDAO.changeOrderDetailStatus(listOrderDetail.get(i)) == false)
				result = false;
		}
		if(result == true) {
			int failProductPrice = 0;
			List<OrderDetail> listCheckOrderDetail = orderDAO.getOrderDetail(orderDetailDTO.getOrder().getIdOrder());
			OrderProduct order = orderDAO.getOrderById(orderDetailDTO.getOrder().getIdOrder());
			boolean checkIsFail = true;
			boolean checkHasOneFail = false;
			for(OrderDetail x: listCheckOrderDetail) {
				if(x.getStatus() != OrderStatus.FAIL)
					checkIsFail = false;
				else {
					checkHasOneFail = true;
					failProductPrice += (x.getQuantity() * x.getProduct().getPrice());
				}
			}
			if(checkIsFail == true) {
				order.setStatus(OrderStatus.FAIL);
				order.setPrice(0);
				if(orderDAO.changeOrderStatus(order))
					return true;
			}else if(checkHasOneFail == true) {
				order.setStatus(OrderStatus.INPROCESS);
				order.setPrice(order.getPrice()-failProductPrice);
				if(orderDAO.changeOrderStatus(order))
					return true;
			}
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public List<OrderDetailDTO> getFailOrderDetailByProducer(int idProducer) {		
		List<OrderDetail> listOrderDetailByProducer = orderDAO.getFailOrderDetailByProducer(idProducer);
		List<OrderDetailDTO> listOrderDetailByProducerDTO = new ArrayList<>();
		if(listOrderDetailByProducer == null) {
			return listOrderDetailByProducerDTO;
		}
		for(OrderDetail x : listOrderDetailByProducer) {
			OrderDetailDTO orderDetail = UtilityConvertBetweenEntityAndDTO.convertToOrderDetailDTO(x);
			//set consumer as null
			OrderProductDTO orderProduct = orderDetail.getOrder();
			orderProduct.setConsumerDTO(null);
			
			//set subcategory and user as null
			ProductDTO product = orderDetail.getProduct();
			product.setSubCategoryDTO(null);
			product.setUserDTO(null);
			
			orderDetail.setOrder(orderProduct);
			orderDetail.setProduct(product);
			listOrderDetailByProducerDTO.add(orderDetail);
		}
		return listOrderDetailByProducerDTO;
	}
}
