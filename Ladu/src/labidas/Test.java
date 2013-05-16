package labidas;

import java.util.ArrayList;
import java.util.List;

import hibernate.*;

import model.database.LaduDAO;
import model.item.dao.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.collection.internal.PersistentSet;

public class Test {

	public static void main(String[] args) {
		Session session = null;
		List<Item> itemList = new ArrayList<Item>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			itemList = session.createQuery("from Item as i").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}
		for (Item item : itemList) {
			System.out.println(item.getName());
		}

		LaduDAO ladu = new LaduDAO();
		List<Item> list = ladu.getAllItems();

		for (Item item : list) {
			System.out.println("Teine ring: " + item.getName());
		}

		HibernateDBConnection hDBC = new HibernateDBConnection();
		System.out.println(hDBC.getUserByUserName("juhan").getPassw());

		List<ItemType> mainItems = hDBC.getMainItems();
		for (ItemType item : mainItems) {
			System.out.println("Main: " + item.getTypeName().toString());
			try {
				List<ItemType> itemTypes = (List<ItemType>) item.getItemTypes();
				for (ItemType itemType : itemTypes) {
					System.out.println(itemType.getTypeName());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

}
