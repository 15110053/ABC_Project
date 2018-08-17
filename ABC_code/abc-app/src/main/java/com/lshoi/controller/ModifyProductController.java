package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ProductDTO;
import com.lshoi.DTO.ResponseDTO;
import com.lshoi.service.ProductServiceImpl;

@RestController
@RequestMapping("ModifyProduct")
public class ModifyProductController {

	private ProductServiceImpl productService;
	
	public ModifyProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@PostMapping("AddProduct")
	@CrossOrigin
	public ResponseDTO addProduct(@RequestBody ProductDTO product) {
		ResponseDTO res = new ResponseDTO();
		if(productService.addProduct(product)) {
			res.setMessage("add product successful");
			res.setStatus(0);
		}else {
			res.setMessage("add product failed");
			res.setStatus(1);
		}
		return res;
	}
	

	@GetMapping("ProductManagement")
	@CrossOrigin
	public ResponseDTO getProductByUSer(@RequestParam int userID) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0); 
		res.setMessage("get product by user");
		res.setObject(productService.getProductByUser(userID));
		return res;
	}
	
	@PostMapping("EditProduct")
	@CrossOrigin
	public ResponseDTO editProduct(@RequestBody ProductDTO editData) {
		boolean result = productService.editProduct(editData);
		if(result) {
			ResponseDTO res = new ResponseDTO();
			res.setStatus(0);
			res.setMessage("edit product complete");
			res.setObject(result);
			return res;
		}
		ResponseDTO res = new ResponseDTO();
		res.setStatus(1);
		res.setMessage("edit product fail");
		res.setObject(result);
		return res;
	}
}
