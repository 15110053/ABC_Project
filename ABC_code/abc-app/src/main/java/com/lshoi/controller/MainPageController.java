package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ResponseDTO;
import com.lshoi.service.CategoryServiceImpl;
import com.lshoi.service.ProductServiceImpl;
import com.lshoi.service.SubCategoryServiceImpl;

@RestController
public class MainPageController {
	
	private CategoryServiceImpl categoryService;
	private SubCategoryServiceImpl subCategoryService;
	private ProductServiceImpl productService;
	
	public MainPageController(CategoryServiceImpl categoryService, 
			SubCategoryServiceImpl subCategoryService, ProductServiceImpl productService) {
		this.categoryService = categoryService;
		this.subCategoryService = subCategoryService;
		this.productService = productService;
	}
/*	
	@GetMapping("GetCategoryList")
	@CrossOrigin
	public List<CategoryDTO> getCategory(){
		return categoryService.getCategoryList();
	}*/
	
	@GetMapping("GetCategoryList")
	@CrossOrigin
	public ResponseDTO getCategory(){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("My Message");
		res.setObject(categoryService.getCategoryList());
		return res;
	}
	
	@GetMapping("GetSubCategoryList")
	@CrossOrigin
	public ResponseDTO getSubCategory(){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Subcategory List");
		res.setObject(subCategoryService.getSubCategoryList());
		return res;
	}
	
	@GetMapping("GetProductList")
	@CrossOrigin
	public ResponseDTO getproduct(@RequestParam int index, @RequestParam int maxResult){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product List");
		res.setObject(productService.getProductList(index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductCount")
	@CrossOrigin
	public ResponseDTO getproductCount(){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Amount of products");
		res.setObject(productService.getProductCount());
		return res;
	}
}
