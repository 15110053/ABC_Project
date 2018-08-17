package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ResponseDTO;

@RestController
@RequestMapping("Cart")
public class CartController {
	
	@GetMapping("Check")
	@CrossOrigin
	public ResponseDTO check() {
		ResponseDTO res = new ResponseDTO();
		res.setMessage("has permission");
		res.setStatus(0);
		return res;
	}
	
	@GetMapping("Purchase")
	@CrossOrigin
	public ResponseDTO purchase() {
		ResponseDTO res = new ResponseDTO();
		res.setMessage("Purchase processing");
		res.setStatus(0);
		return res;
	}
}
