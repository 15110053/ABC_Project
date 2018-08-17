package com.lshoi.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.lshoi.models.User;

public class UserDAO {

	private SessionFactory sessionFactory;
	
	public UserDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	public void register(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
	}
	
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		User loginUser = (User)session.createCriteria(User.class)
			.add(Restrictions.like("loginName", user.getLoginName()))
			.add(Restrictions.like("password", user.getPassword()))
			.uniqueResult();
		return loginUser;
	}
	
	public User getUserDTO(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("userId", userId)).uniqueResult();
		session.flush();
		session.clear();
		return user;
	}
	
	public Boolean changeUserInfo(User user) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(user);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
