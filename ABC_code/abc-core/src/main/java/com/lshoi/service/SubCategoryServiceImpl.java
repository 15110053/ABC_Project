package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.SubCategoryDTO;
import com.lshoi.models.SubCategory;
import com.lshoi.repositoty.SubCategoryDAO;
import com.lshoi.utilities.UtilityConvertBetweenEntityAndDTO;

public class SubCategoryServiceImpl implements SubCategoryService{
	private SubCategoryDAO subCategoryDAO; // = new SubCategoryDAO();

	
	
	public SubCategoryServiceImpl(SubCategoryDAO subCategoryDAO) {
		this.subCategoryDAO = subCategoryDAO;
	}

	@Transactional
	@Override
	public List<SubCategoryDTO> getSubCategoryList() {
		List<SubCategory> listSubCategory = subCategoryDAO.getSubCategoryList();
		List<SubCategoryDTO> listSubCategoryDTO = new ArrayList<>();
		for(SubCategory x : listSubCategory) {
			listSubCategoryDTO.add(UtilityConvertBetweenEntityAndDTO.convertToSubCategoryDTO(x));
		}
		return listSubCategoryDTO;
	}
	 
}
