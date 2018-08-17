package com.lshoi.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Category{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;
	
	@Column
	private String categoryName;
	
	/*@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<SubCategory> subCategory;*/
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/*public Set<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}*/
	
}
