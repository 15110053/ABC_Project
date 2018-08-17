package com.lshoi.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class SubCategory{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubCategory;
	
	@Column
	private String subCategoryName;
	
	@ManyToOne
	@JoinColumn(name = "idCategory")
	private Category category;
	
	/*@OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER)
	private Set<Product> product;
*/
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/*public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}*/

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
