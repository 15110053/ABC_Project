package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ResponseDTO;
import com.lshoi.service.ProductServiceImpl;

@RestController
@RequestMapping("Product")
public class ProductPageController {
	
	private ProductServiceImpl productService;
	
	public ProductPageController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@GetMapping("GetProduct")
	@CrossOrigin
	public ResponseDTO getProduct(@RequestParam int idProduct) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product");
		res.setObject(productService.getProduct(idProduct));
		return res;
	}
	@GetMapping("GetTheMostViewedProduct")
	@CrossOrigin
	public ResponseDTO getTheMostViewedProduct(@RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("The most viewed products");
		res.setObject(productService.getTheMostViewedProduct(index, maxResult));
		return res;
	}
	
	@GetMapping("GetNewProduct")
	@CrossOrigin
	public ResponseDTO getNewProduct(@RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("New products");
		res.setObject(productService.getNewProduct(index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductBySubCategory")
	@CrossOrigin
	public ResponseDTO getProductByType(@RequestParam int categoryId, @RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product of a subcategory");
		res.setObject(productService.getProductBySubCategory(categoryId, index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductBySubCategoryCount")
	@CrossOrigin
	public ResponseDTO getProductByTypeCount(@RequestParam int categoryId) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Amount of product of a subcategory");
		res.setObject(productService.getProductBySubCategoryCount(categoryId));
		return res;
	}
	
	@GetMapping("GetProductBySearch")
	@CrossOrigin
	public ResponseDTO getProductBySearch(@RequestParam String keyWord, @RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product search");
		res.setObject(productService.getProductBySearch(keyWord, index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductBySearchCount")
	@CrossOrigin
	public ResponseDTO getProductBySearchCount(@RequestParam String keyWord) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Amount of product search");
		res.setObject(productService.getProductBySearchCount(keyWord));
		return res;
	}
	
	@GetMapping("GetProductByCategory")
	@CrossOrigin
	public ResponseDTO getProductByCategory(@RequestParam int categoryId, @RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product of a category");
		res.setObject(productService.getProductByCategory(categoryId, index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductByCategoryCount")
	@CrossOrigin
	public ResponseDTO getProductByCategoryCount(@RequestParam int categoryId) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Amount of product of a category");
		res.setObject(productService.getProductByCategoryCount(categoryId));
		return res;
	}
}
