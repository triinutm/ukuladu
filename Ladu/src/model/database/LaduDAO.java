package model.database;

import hibernate.Item;
import hibernate.ItemType;
import hibernate.TypeAttribute;
import hibernate.UserAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import model.AttributeModel;
import model.SearchForm;
import model.item.dao.HibernateUtil;

public class LaduDAO {



	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement  pstmt = null ;
		ResultSet ItemSet  = null ;
		Item Current_Item  = null ;
		String sql ;
		Connection db  = null;	
		try {
			db = DBConnection.getConnection();
			pstmt = db.prepareStatement("select * from ITEM");

			ItemSet = pstmt.executeQuery();

			while(ItemSet.next()) {    
				Current_Item = new Item();    
				Current_Item.setItem(ItemSet.getInt("item"));
				//				Current_Item.setUnitTypeFk(ItemSet.getLong("unitTypeFk"));
				//				Current_Item.setSupplierEnterpriseFk(ItemSet.getLong("supplierEnterpriseFK"));
				//				Current_Item.setItemTypeFk(ItemSet.getLong("itmeTypeFK"));
				Current_Item.setName(ItemSet.getString("name"));
				//				Current_Item.setStorePrice((ItemSet.getBigDecimal("storePrice")));
				//				Current_Item.setSalePrice(ItemSet.getBigDecimal("salerPrice"));
				//				Current_Item.setProducer(ItemSet.getString("producer"));
				//				Current_Item.setDescription(ItemSet.getString("description"));
				//				Current_Item.setProducerCode(ItemSet.getString("producerCode"));
				//				Current_Item.setSingleItem(ItemSet.getString("singleItem"));
				//				Current_Item.setUpperItemFk(ItemSet.getLong("uppperItemFk"));
				//				Current_Item.setSerialNo(ItemSet.getString("serialNo"));
				//				Current_Item.setCreated(ItemSet.getDate("created"));

				itemList.add(Current_Item);

			}
		}

		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			//			MyLogger.Log("ItemDAO.GetItem_fromDB():",ex.getMessage());

		}
		finally
		{
			DBConnection.closeStatement(pstmt);
			DBConnection.closeResultSet(ItemSet);
			DBConnection.close(db);
		}

		return itemList ;
	}

	public List<Item> searchItems(SearchForm form, String catalog) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Item> items = new ArrayList<Item>();
		try {
			session.beginTransaction();
			StringBuilder query = new StringBuilder(
					"select i.item, i.name, i.description, i.sale_price, "
							+ " i.store_price, i.producer, i.producer_code, item_type_fk from item i  where 1=1 ");
			appendLike("name", form.getName(), query);
			appendLike("description", form.getDescription(), query);
			appendLike("producer", form.getProducer(), query);
			appendLike("producer_code", form.getProducerCode(), query);
			appendAnd("sale_price", form.getSalePrice(), query);
			appendAnd("store_price", form.getStorePrice(), query);
			if (StringUtils.isNotBlank(form.getAttribute())) {
				query.append(" and i.item in (SELECT item_fk FROM item_attribute WHERE item_fk=i.item and "
						+ "(value_text like '%"
						+ form.getAttribute()
						+ "%' or value_number = " + form.getAttribute() + "))");
			}
			if (form.getAttributes().size() > 0) {
				if (catalog != null) {
					appendAnd("item_type_fk", catalog, query);
				}
				for (Long key : form.getAttributes().keySet()) {
					AttributeModel currentAttribute = form.getAttributes().get(
							key);
					if (StringUtils.isNotBlank(currentAttribute
							.getAttributeValue())) {
						query.append(" and i.item in (SELECT item_fk FROM item_attribute WHERE item_fk=i.item "
								+ "and item_attribute_type_fk ="
								+ key
								+ " and (value_text like '%"
								+ currentAttribute.getAttributeValue() + "%' ");
						String numeric = isNumeric(currentAttribute
								.getAttributeValue());
						if (numeric != null) {
							query.append(" or value_number = " + numeric);
						}
						query.append("))");
					}
				}
			}
			System.out.println(query.toString());
			Query q = session.createSQLQuery(query.toString());

			ScrollableResults results = q.scroll();
			while (results.next()) {
				Object[] row = results.get();
				Item item = new Item();
				item.setItem(Long.parseLong(row[0].toString()));
				item.setName(row[1] != null ? row[1].toString() : null);
				item.setDescription(row[2] != null ? row[2].toString() : "");
				if (row[3] != null) {
					item.setSalePrice(new BigDecimal(row[3].toString()));
				}
				if (row[4] != null) {
					item.setStorePrice(new BigDecimal(row[4].toString()));
				}
				item.setProducer(row[5] != null ? row[5].toString() : "");
				item.setProducerCode(row[6] != null ? row[6].toString() : "");
				items.add(item);
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("viga");
		}
		System.out.println(items.size());
		return items;
	}

	private void appendLike(String field, String value, StringBuilder query) {
		if (StringUtils.isNotBlank(value)) {
			query.append(" and " + field + " like '%" + value + "%'");
		}
	}

	private void appendAnd(String field, String value, StringBuilder query) {
		if (StringUtils.isNotBlank(value)) {
			String numeric = isNumeric(value);
			if (numeric != null) {
				query.append(" and " + field + " = " + value);
			} else {
				query.append(" and 1=2");
			}
		}
	}

	private String isNumeric(String s) {
		try {
			s = s.replace(",", ".");
			new BigDecimal(s);
			return s;
		} catch (Exception e) {
			return null;
		}
	}

	public UserAccount getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemType getItemTypeById(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		ItemType itemType = null;
		try {
			session.getTransaction().begin();
			itemType = (ItemType) session.get(ItemType.class, new Long(id));
			// session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			//                log.warn("Error: getItemTypeById()");
		}
		return itemType;

	}

	public List<TypeAttribute> getTypeAttributesByItemType(int itemType) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<TypeAttribute> typeAttributes = null;
		try {
			session.beginTransaction();
			Query q = session
					.createQuery("from TypeAttribute where itemType.itemType = :id order by orderby");
			q.setInteger("id", itemType);
			typeAttributes = (List<TypeAttribute>) q.list();

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return typeAttributes;
	}

}
