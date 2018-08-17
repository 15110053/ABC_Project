package com.lshoi.DTO;

public class SubCategoryDTO {
	
	private int idSubCategory;
	private String subCategoryName;
	private CategoryDTO categoryDTO;
	
	public SubCategoryDTO() {}
	
	public int getIdSubCategory() {
		return idSubCategory;
	}

	public void setIdSubCategory(int idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

}
