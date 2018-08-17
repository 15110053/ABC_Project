package com.lshoi.repositoty;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lshoi.models.Category;

public class CategoryDAO {
	private SessionFactory sessionFactory;

	public CategoryDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Category> getCategoryList() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Category> category = session.createQuery("from Category").list();
		return category;
	}

}
