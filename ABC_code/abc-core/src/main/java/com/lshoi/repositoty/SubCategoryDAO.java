package com.lshoi.repositoty;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lshoi.models.SubCategory;

public class SubCategoryDAO {
	
	private SessionFactory sessionFactory;
	
	public SubCategoryDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	public List<SubCategory> getSubCategoryList(){
		Session session = sessionFactory.getCurrentSession();
		List<SubCategory> subCategory = session
				.createQuery("from SubCategory order by category.idCategory").list();
		return subCategory;
	}
	
	public SubCategory getSubCategor(int id){
		Session session = sessionFactory.getCurrentSession();
		SubCategory subCategory = (SubCategory)session.createCriteria(SubCategory.class)
				.add(Restrictions.eq("idSubCategory", id)).uniqueResult();
		return subCategory;
	}
}
