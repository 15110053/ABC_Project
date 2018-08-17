package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.CategoryDTO;
import com.lshoi.models.Category;
import com.lshoi.repositoty.CategoryDAO;
import com.lshoi.utilities.UtilityConvertBetweenEntityAndDTO;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDAO categoryDAO ;//= new CategoryDAO();
	
	public CategoryServiceImpl(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	@Transactional
	@Override
	public List<CategoryDTO> getCategoryList() {
		List<Category> listCategory = categoryDAO.getCategoryList();
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		for(Category x : listCategory) {
			listCategoryDTO.add(UtilityConvertBetweenEntityAndDTO.convertToCategoryDTO(x));
		}
		return listCategoryDTO;
	}
}
