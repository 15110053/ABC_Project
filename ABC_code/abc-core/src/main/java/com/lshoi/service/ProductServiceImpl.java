package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.ProductDTO;
import com.lshoi.DTO.SubCategoryDTO;
import com.lshoi.DTO.UserDTO;
import com.lshoi.models.Product;
import com.lshoi.models.SubCategory;
import com.lshoi.repositoty.ProductDAO;
import com.lshoi.repositoty.SubCategoryDAO;
import com.lshoi.utilities.UtilityConvertBetweenEntityAndDTO;

public class ProductServiceImpl implements ProductService{

	private ProductDAO productDAO; // = new ProductDAO();
	private SubCategoryDAO subCategoryDAO;
	
	public ProductServiceImpl(ProductDAO productDAO, SubCategoryDAO subCategoryDAO) {
		this.productDAO = productDAO;
		this.subCategoryDAO = subCategoryDAO;
	}

	@Transactional
	@Override
	public List<ProductDTO> getProductList(int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getProducList(index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public ProductDTO getProduct(int idProduct) {
		return UtilityConvertBetweenEntityAndDTO.convertToProductDTO(productDAO.getProduct(idProduct));
	}

	@Transactional
	@Override
	public Long getProductCount() {
		return productDAO.getProductCount();
	}

	@Transactional
	@Override
	public List<ProductDTO> getTheMostViewedProduct(int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getTheMostViewedProduct(index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}
	
	@Transactional
	@Override
	public List<ProductDTO> getNewProduct(int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getNewProduct(index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public List<ProductDTO> getProductBySubCategory(int categoryId, int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getProductBySubCategory(categoryId, index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public Long getProductBySubCategoryCount(int categoryId) {
		return productDAO.getProductBySubCategoryCount(categoryId);
	}

	@Transactional
	@Override
	public List<ProductDTO> getProductBySearch(String keyWord, int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getProductBySearch(keyWord, index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public Long getProductBySearchCount(String keyWord) {
		return productDAO.getProductBySearchCount(keyWord);
	}

	@Transactional
	@Override
	public List<ProductDTO> getProductByCategory(int categoryId, int index, int maxResult) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getProductByCategory(categoryId, index, maxResult);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public Long getProductByCategoryCount(int categoryId) {
		return productDAO.getProductByCategoryCount(categoryId);
	}

	@Transactional
	@Override
	public boolean addProduct(ProductDTO productDTO) {
		SubCategory subcategory = subCategoryDAO.getSubCategor(productDTO.getSubCategoryDTO().getIdSubCategory());
		SubCategoryDTO subCategoryDTO = UtilityConvertBetweenEntityAndDTO.convertToSubCategoryDTO(subcategory);
		productDTO.setSubCategoryDTO(subCategoryDTO);
		UserDTO userDTO = AuthenticationService.getInstance().checkTokenExist(productDTO.getUserDTO().getUserName());
		productDTO.setUserDTO(userDTO);
		Product product = UtilityConvertBetweenEntityAndDTO.convertToProductEntity(productDTO);
		return productDAO.addProduct(product);
	}

	@Transactional
	@Override
	public List<ProductDTO> getProductByUser(int userID) {
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		List<Product> listProduct = productDAO.getProductByUser(userID);
		for(Product x : listProduct) {
			listProductDTO.add(UtilityConvertBetweenEntityAndDTO.convertToProductDTO(x));
		}
		return listProductDTO;
	}

	@Transactional
	@Override
	public boolean editProduct(ProductDTO productDTO) {
		return productDAO.editProduct(UtilityConvertBetweenEntityAndDTO.convertToProductEntity(productDTO));
	}

}
