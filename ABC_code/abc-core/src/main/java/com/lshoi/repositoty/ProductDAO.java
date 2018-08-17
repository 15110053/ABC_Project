package com.lshoi.repositoty;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.lshoi.models.Product;
import com.lshoi.models.User;

public class ProductDAO {
	private SessionFactory sessionFactory;
	
	public ProductDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*public List<Product> getProducList(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Product> product = session.createQuery("from Product").list();
		transaction.commit();
		session.close();
		return product;
	}*/
	
	public Product getProduct(int idProduct) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, idProduct);
		return product;
	}
	
	public Long getProductCount() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	public List<Product> getProducList(int index, int maxResult) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.setFirstResult((index-1) * maxResult)
				.setMaxResults(maxResult)
				.list();
		return product;
	}
	
	public List<Product> getTheMostViewedProduct(int index, int maxResult){
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.addOrder(Order.desc("viewCounter"))
				.setFirstResult((index-1) * maxResult)
				.setMaxResults(maxResult)
				.list();
		return product;
	}
	
	public List<Product> getNewProduct(int index, int maxResult){
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.addOrder(Order.desc("addDate"))
				.setFirstResult((index-1) * maxResult)
				.setMaxResults(maxResult)
				.list();
		
		return product;
	}
	
	public List<Product> getProductBySubCategory(int categoryId, int index, int maxResult){
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.add(Restrictions.eq("subCategory.idSubCategory", categoryId))
				.setFirstResult((index-1) * maxResult)
				.setMaxResults(maxResult)
				.list();
		return product;
	}
	
	public Long getProductBySubCategoryCount(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.add(Restrictions.eq("subCategory.idSubCategory", categoryId))
				.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	public List<Product> getProductBySearch(String keyWord, int index, int maxResult){
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.add(Restrictions.like("productName", "%" + keyWord + "%"))
				.setFirstResult((index-1) * maxResult)
				.setMaxResults(maxResult)
				.list();
		return product;
	}
	
	public Long getProductBySearchCount(String keyWord) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class)
				.add(Restrictions.eq("status", true))
				.add(Restrictions.like("productName", "%" + keyWord + "%"))
				.setProjection(Projections.rowCount());
		Long result = (Long)criteria.uniqueResult();
		return result;
	}
	
	public List<Product> getProductByCategory(int categoryId, int index, int maxResult){
		Session session = sessionFactory.getCurrentSession();
		List<Product> product = session.createQuery("from Product where "
				+ "subCategory.category.idCategory = "+ categoryId)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.list();
		return product;
	}
	
	public Long getProductByCategoryCount(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Long result = (Long)session.createQuery("select count(*) from Product where "
				+ "subCategory.category.idCategory = "+ categoryId).uniqueResult();
		return result;
	}
	
	//create, change info product
	public boolean addProduct(Product product) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(product);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	public List<Product> getProductByUser(int userID){
		Session session = sessionFactory.getCurrentSession();
		/*User user = (User)session.createCriteria(User.class)
				.add(Restrictions.eq("userId", userID)).uniqueResult();
		if(user == null) return null;*/
		List<Product> listProduct = session.createCriteria(Product.class)
				.add(Restrictions.eq("user.userId" ,userID))
				.list();
		return listProduct;
	}
	public boolean editProduct(Product product) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(product);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
