package com.lshoi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.OrderDetailDTO;
import com.lshoi.DTO.ResponseDTO;
import com.lshoi.service.OrderServiceImpl;

@RestController
@RequestMapping("ChangeOrderStatus")
public class OrderStatusController {
	
	private OrderServiceImpl orderService;
	
	public OrderStatusController(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("GetWaitingOrderDetail")
	@CrossOrigin
	public ResponseDTO getOrderStatus(@RequestParam int producerId) {
		ResponseDTO res = new ResponseDTO();
		res.setObject(orderService.getOrderDetailByProducer(producerId));
		res.setStatus(0);
		res.setMessage("Order Detail need to handle");
		return res;
	}
	
	@GetMapping("GetInprocessOrderDetail")
	@CrossOrigin
	public ResponseDTO getInprocessOrderStatus(@RequestParam int producerId) {
		ResponseDTO res = new ResponseDTO();
		res.setObject(orderService.getInprocessOrderDetailByProducer(producerId));
		res.setStatus(0);
		res.setMessage("Inprocess Order Detail");
		return res;
	}
	
	@GetMapping("GetFailOrderDetail")
	@CrossOrigin
	public ResponseDTO getFailOrderStatus(@RequestParam int producerId) {
		ResponseDTO res = new ResponseDTO();
		res.setObject(orderService.getFailOrderDetailByProducer(producerId));
		res.setStatus(0);
		res.setMessage("Inprocess Order Detail");
		return res;
	}
	
	@PostMapping("InprocessOrderDetail")
	@CrossOrigin
	public ResponseDTO changeOrderToInprocess(@RequestBody OrderDetailDTO orderDetailDTO) {
		if(orderService.inprocessOrderDetail(orderDetailDTO) == true) {
			ResponseDTO res = new ResponseDTO();
			res.setStatus(0);
			res.setMessage("success change to in-process");
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setStatus(1);
		error.setMessage("Has error");
		return error;
	}
	
	@PostMapping("FailOrderDetail")
	@CrossOrigin
	public ResponseDTO changeOrderToFail(@RequestBody OrderDetailDTO orderDetailDTO) {
		if(orderService.failOrderDetail(orderDetailDTO)) {
			ResponseDTO res = new ResponseDTO();
			res.setStatus(0);
			res.setMessage("Fail Order Detail successfully");
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setStatus(1);
		error.setMessage("Has error");
		return error;
	}
}
