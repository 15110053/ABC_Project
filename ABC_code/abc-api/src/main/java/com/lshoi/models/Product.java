package com.lshoi.models;


import java.util.Date;

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
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	
	@Column
	private String productName;
	
	@Column
	private String description;
	
	@Column
	private double price;
	
	@Column
	private String image;
	
	@Column
	private boolean status;
	
	@Column
	private Date addDate;
	
	@Column
	private int viewCounter = 0;
	
	@ManyToOne
	@JoinColumn(name = "idSubCategory")
	private SubCategory subCategory;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getViewCounter() {
		return viewCounter;
	}

	public void setViewCounter(int viewCounter) {
		this.viewCounter = viewCounter;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
