package com.lshoi.service;

import java.util.List;

import com.lshoi.DTO.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getProductList(int index, int maxResult);
	public ProductDTO getProduct(int idProduct);
	public Long getProductCount();
	public List<ProductDTO> getTheMostViewedProduct(int index, int maxResult);
	public List<ProductDTO> getNewProduct(int index, int maxResult); 
	public List<ProductDTO> getProductBySubCategory(int categoryId, int index, int maxResult); 
	public Long getProductBySubCategoryCount(int categoryId);
	public List<ProductDTO> getProductBySearch(String keyWord, int index, int maxResult); 
	public Long getProductBySearchCount(String keyWord); 
	public List<ProductDTO> getProductByCategory(int categoryId, int index, int maxResult);
	public Long getProductByCategoryCount(int categoryId);
	//create, change info product
	public boolean addProduct(ProductDTO productDTO);
	public boolean editProduct(ProductDTO productDTO);
	public List<ProductDTO> getProductByUser(int userID);
}
