package labidas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import hibernate.*;

import model.database.LaduDAO;
import model.database.PriceListDAO;
import model.item.dao.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.annotations.Parent;
import org.hibernate.collection.internal.PersistentSet;

public class Test {

	public static void main(String[] args) {
//		Session session = null;
//		List<Item> itemList = new ArrayList<Item>();
//		try {
//			session = HibernateUtil.getSessionFactory().getCurrentSession();
//			session.beginTransaction();
//			itemList = session.createQuery("from Item as i").list();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//
//		}
//		for (Item item : itemList) {
//			System.out.println(item.getName());
//		}
//
//		LaduDAO ladu = new LaduDAO();
//		List<Item> list = ladu.getAllItems();
//
//		for (Item item : list) {
//			System.out.println("Teine ring: " + item.getName());
//		}

//		HibernateDBConnection hDBC = new HibernateDBConnection();
		
		PriceListDAO dao = new PriceListDAO();
		
		System.out.println(dao.findCustomersById(1));
		
		System.out.println(dao.findAll());
		
//		System.out.println(hDBC.getUserByUserName("juhan").getPassw());
//
//		List<ItemType> mainItems = hDBC.getMainItems();
//		for (ItemType item : mainItems) {
//			System.out.println( item.getTypeName().toString());
//
//			Set itemTypes = item.getItemTypes();
//			List<ItemType> children = new ArrayList<ItemType>();
//			Iterator it = itemTypes.iterator();
//
//			while (it.hasNext()) {
//				children.add((ItemType) it.next());
//			}
//			for (ItemType itemType : children) {
//				System.out.println("-- "
//						+ itemType.getTypeName().toString());
//				
//					Set itemTypes1 = itemType.getItemTypes();
//					List<ItemType> children1 = new ArrayList<ItemType>();
//					Iterator it1 = itemTypes1.iterator();
//
//					while (it1.hasNext()) {
//						children1.add((ItemType) it1.next());
//					}
//						for (ItemType itemType1 : children1) {
//							System.out.println("  --- "
//									+ itemType1.getTypeName().toString());
//						}
//				
//				
//			}
//
//		}
	}

}
