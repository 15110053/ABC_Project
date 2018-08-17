package com.lshoi.repositoty;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lshoi.models.OrderDetail;
import com.lshoi.models.OrderProduct;

public class OrderDAO {
	private SessionFactory sessionFactory;
	
	public OrderDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public int CreateOrderProduct(OrderProduct order) {
		try {
			Session session = sessionFactory.getCurrentSession();
			int id = (Integer)session.save(order);
			return id;
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public boolean addOrderDetail(OrderDetail orderDetail) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(orderDetail);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public List<OrderProduct> getOrderByUser(int userId){
		Session session = sessionFactory.getCurrentSession();
		List<OrderProduct> listOrder = session.createCriteria(OrderProduct.class)
				.add(Restrictions.eq("consumer.userId", userId))
				.list();
		return listOrder;
	}
	
	public OrderProduct getOrderById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OrderProduct)session.createCriteria(OrderProduct.class)
				.add(Restrictions.eq("idOrder", id)).uniqueResult();
	}
	
	public boolean cancelOrder(OrderProduct order) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(order);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public List<OrderDetail> getOrderDetail(int orderId){
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createCriteria(OrderDetail.class)
					.add(Restrictions.eq("order.idOrder", orderId)).list();
		}catch(Exception ex) {
			return null;
		}
	}
}
