package hibernate;

import java.util.ArrayList;
import java.util.List;

import model.item.dao.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateDBConnection {
	
	public UserAccount getUserByUserName(String userName){
		Session session = null;
		UserAccount userAccount = new UserAccount();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("from UserAccount where userName = :userName");
		    q.setParameter("userName", userName);
		    
			userAccount = (UserAccount) q.list().get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
			
		}
		return userAccount;
	}
	
	public List<ItemType> getMainItems() {
		Session session = null;
		List<ItemType> items = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			items = session.createQuery("from ItemType as it where itemType_1 is null ").list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
			
		}
		return items;
	}
	
	public ItemType getItemTypeById(int id){
		Session session = null;
		ItemType item = new ItemType();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("from ItemType where itemType = :itemId");
		    q.setParameter("itemId", id);
		    item = (ItemType) q.list().get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return item;
	}

}
