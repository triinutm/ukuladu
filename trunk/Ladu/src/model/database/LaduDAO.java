package model.database;

import hibernate.Enterprise;
import hibernate.Item;
import hibernate.ItemAction;
import hibernate.ItemActionType;
import hibernate.ItemAttribute;
import hibernate.ItemStore;
import hibernate.ItemType;
import hibernate.Store;
import hibernate.TypeAttribute;
import hibernate.UnitType;
import hibernate.UserAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.AttributeModel;
import model.ProductModel;
import model.SearchForm;
import model.item.dao.HibernateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LaduDAO {

	 static Logger log = Logger.getLogger(DBConnection.class);

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

	
	
	@SuppressWarnings("unchecked")
    public List<ItemType> getAllItemTypes() {

            Session session = HibernateUtil.getSessionFactory().openSession();
            List<ItemType> itemTypeList = null;
            try {
                    session.getTransaction().begin();
                    itemTypeList = (List<ItemType>) session.createCriteria(
                                    ItemType.class).list();
                    // session.getTransaction().commit();
            } catch (Exception e) {
                    session.getTransaction().rollback();
                    System.out.println("16");
                    log.warn("Error: getAllItemTypes()");
            }

            return itemTypeList;
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
                    System.out.println("15");
                    log.warn("Error: getItemTypeById()");
            }
            return itemType;

    }

    @SuppressWarnings("unchecked")
    public List<ItemType> getItemTypeCatalogs(int id) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<ItemType> itemTypes = null;
            try {
                    session.getTransaction().begin();

                    Query q = session
                                    .createQuery("from ItemType as p where p.itemType_1.itemType =:id order by p.typeName");
                    q.setInteger("id", id);
                    itemTypes = (List<ItemType>) q.list();
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("14");
                    log.warn("Error: getItemTypeCatalogs()");
            }

            return itemTypes;
    }

    @SuppressWarnings("unchecked")
    public List<ItemType> getAllFirstlevelCatalogs() {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<ItemType> itemTypes = null;
            try {
                    session.beginTransaction();

                    Query q = session
                                    .createQuery("from ItemType as p where p.level =1 order by p.typeName");
                    itemTypes = (List<ItemType>) q.list();
                    // session.getTransaction().commit();

            } catch (RuntimeException e) {
                    session.getTransaction().rollback();
                    System.out.println(e.getMessage());
                    System.out.println("13");
                    log.warn("Error: getAllFirstLevelCatalogs");
            }
            return itemTypes;
    }

    /*
     * 
     */
    @SuppressWarnings("unchecked")
    public UserAccount getUserByUsername(String username) {
            UserAccount userAccount = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            try {
                    session.beginTransaction();
                    Query query = session
                                    .createQuery("from UserAccount as u where u.username= :username");
                    query.setString("username", username);
                    List<UserAccount> userAccountList = (List<UserAccount>) query
                                    .setMaxResults(1).list();
                    if (!userAccountList.isEmpty()) {
                            userAccount = (UserAccount) userAccountList.get(0);
                    }

            } catch (Exception e) {
            	System.out.println("12");
                    e.printStackTrace();
            }

            return userAccount;
    }

    @SuppressWarnings("unchecked")
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
            	System.out.println("11");
                    e.printStackTrace();
            }
            return typeAttributes;
    }

    /**
     * Meetod leiab Item objekti id järgi.
     * 
     * @param id
     *            - otsitava toote id
     * @return - null kui toodet ei leita.
     */
    public Item getItemById(int id) {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Item item = null;
            try {
                    session.getTransaction().begin();
                    item = (Item) session.get(Item.class, new Long(id));
                    // session.getTransaction().commit();
            } catch (Exception e) {
                    session.getTransaction().rollback();
                    System.out.println("10");
                    log.warn("Error: getItemTypeById()");
                    e.printStackTrace();
            }
            return item;
    }

    /**
     * Meetod küsib andmebaasist kõik laod.
     * 
     * @return - null, kui ladusid ei leita.
     */
    @SuppressWarnings("unchecked")
    public List<Store> getAllWareHouses() {

            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Store> storeList = null;
            try {
                    session.getTransaction().begin();
                    storeList = (List<Store>) session.createCriteria(Store.class)
                                    .list();
                    session.getTransaction().commit();
            } catch (Exception e) {
                    session.getTransaction().rollback();
                    System.out.println("9");
                    log.warn("Error: getAllWareHouses()");
                    e.printStackTrace();
            }
            return storeList;
    }

    /**
     * Toote lisamine koos attribuutidega
     * 
     * @param model
     * @return
     */
    public Item saveItem(ProductModel model) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            Item item = new Item();
            item.setDescription(model.getDescription().getAttributeValue());
            item.setName(model.getName().getAttributeValue());
            item.setSalePrice(new BigDecimal(model.getPrice().getAttributeValue()));
            item.setCreated(new Date());
            item.setUnitType((UnitType) session.get(UnitType.class, 1L));
            item.setItemType((ItemType) session.get(ItemType.class,
                            new Long(model.getItemType())));
            item.setEnterprise((Enterprise) session.get(Enterprise.class, 2));
            Set<ItemAttribute> attributes = new HashSet<ItemAttribute>();
            LaduDAO ladu = new LaduDAO();
            // Otsime toote attribuudid ning paneme ID järgi map-i
            List<TypeAttribute> itemAttributes =ladu
                            .getTypeAttributesByItemType(Integer.parseInt(model
                                            .getItemType()));
            Map<Long, TypeAttribute> attributeMap = new HashMap<Long, TypeAttribute>();
            for (TypeAttribute a : itemAttributes) {
                    attributeMap.put(a.getTypeAttribute(), a);
            }
            for (Long key : model.getAttributes().keySet()) {
                    AttributeModel currentAttribute = model.getAttributes().get(key);
                    // tühja attribuuti ei lisa
                    if (StringUtils.isNotBlank(currentAttribute.getAttributeValue())) {
                            ItemAttribute attribute = new ItemAttribute();
                            TypeAttribute attributeDefinition = attributeMap.get(key);
                            // kas on tekstiväli
                            if (attributeDefinition.getItemAttributeType().getDataType()
                                            .equals(1L)) {
                                    attribute.setDataType(1L);
                                    attribute
                                                    .setValueText(currentAttribute.getAttributeValue());
                            } else if (attributeDefinition.getItemAttributeType()
                                            .getDataType().equals(2L)) {
                                    attribute.setDataType(2L);
                                    attribute.setValueNumber(new Long(currentAttribute
                                                    .getAttributeValue()));
                            }
                            attribute.setItemAttributeType(attributeDefinition
                                            .getItemAttributeType());
                            attribute.setOrderby(attributeDefinition.getOrderby());
                            attribute.setItem(item);
                            attributes.add(attribute);
                    }

            }
            item.setItemAttributes(attributes);
            session.save(item);
            trans.commit();
            session.close();
            return item;
    }

    /**
     * Toote lisamine koos attribuutidega
     * 
     * @param model
     * @param itemId
     * @return
     */
    public Item updateItem(ProductModel model, Long itemId) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            Item item = (Item) session.get(Item.class, itemId);
            item.setDescription(model.getDescription().getAttributeValue());
            item.setName(model.getName().getAttributeValue());
            item.setSalePrice(new BigDecimal(model.getPrice().getAttributeValue()));
            Set<ItemAttribute> attributes = item.getItemAttributes();
            Map<Long, ItemAttribute> existingAttributes = new HashMap<Long, ItemAttribute>();
            for (ItemAttribute at : attributes) {
                    existingAttributes.put(at.getItemAttribute(), at);
            }
           LaduDAO ladu = new LaduDAO();
            // Otsime toote attribuudid ning paneme ID järgi map-i
            List<TypeAttribute> itemAttributes = ladu
                            .getTypeAttributesByItemType(Integer.parseInt(model
                                            .getItemType()));
            Map<Long, TypeAttribute> attributeMap = new HashMap<Long, TypeAttribute>();
            for (TypeAttribute a : itemAttributes) {
                    attributeMap.put(a.getTypeAttribute(), a);
            }
            for (Long key : model.getAttributes().keySet()) {
                    AttributeModel currentAttribute = model.getAttributes().get(key);
                    // kas baasis on juba attribuudi kirje olemas
                    if (currentAttribute.getAttributeId() != null) {
                            ItemAttribute attr = existingAttributes.get(currentAttribute
                                            .getAttributeId());
                            if (attr.getDataType().equals(1L)) {
                                    if (StringUtils.isNotBlank(currentAttribute
                                                    .getAttributeValue())) {
                                            attr.setValueText(currentAttribute.getAttributeValue());
                                    } else {
                                            attr.setValueText(null);
                                    }
                            } else if (attr.getDataType().equals(2L)) {
                                    if (StringUtils.isNotBlank(currentAttribute
                                                    .getAttributeValue())) {
                                            attr.setValueNumber(new Long(currentAttribute
                                                            .getAttributeValue()));
                                    } else {
                                            attr.setValueNumber(null);
                                    }

                            }
                    } else {
                            // tühja väärtust pole mõtet lisada
                            if (StringUtils
                                            .isNotBlank(currentAttribute.getAttributeValue())) {
                                    ItemAttribute attribute = new ItemAttribute();
                                    TypeAttribute attributeDefinition = attributeMap.get(key);
                                    // kas on tekstiväli
                                    if (attributeDefinition.getItemAttributeType()
                                                    .getDataType().equals(1L)) {
                                            attribute.setDataType(1L);
                                            attribute.setValueText(currentAttribute
                                                            .getAttributeValue());
                                    } else if (attributeDefinition.getItemAttributeType()
                                                    .getDataType().equals(2L)) {
                                            attribute.setDataType(2L);
                                            attribute.setValueNumber(new Long(
                                                            currentAttribute.getAttributeValue()));
                                    }
                                    attribute.setItemAttributeType(attributeDefinition
                                                    .getItemAttributeType());
                                    attribute.setOrderby(attributeDefinition.getOrderby());
                                    attribute.setItem(item);
                                    attributes.add(attribute);
                            }
                    }
            }
            session.save(item);
            trans.commit();
            session.close();
            return item;
    }

    public List<Item> searchItems(SearchForm form, String catalog) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Item> items = new ArrayList<Item>();
            try {
                    session.beginTransaction();
                    StringBuilder query = new StringBuilder(
                                    "select i.item, i.name, i.description, i.sale_price, "
                                                    + " i.store_price, i.producer, i.producer_code, i.item_type_fk from item  i  where 1=1 ");
                    appendLike("i.name", form.getName(), query);
                    appendLike("i.description", form.getDescription(), query);
                    appendLike("i.producer", form.getProducer(), query);
                    appendLike("i.producer_code", form.getProducerCode(), query);
                    appendAnd("i.sale_price", form.getSalePrice(), query);
                    appendAnd("i.store_price", form.getStorePrice(), query);
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
                                                            + "and i.item_attribute_type_fk ="
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
            	System.out.println("8");
                    e.printStackTrace();
            }
            return items;
    }

    private void appendLike(String field, String value, StringBuilder query) {
            if (StringUtils.isNotBlank(value)) {
                    query.append(" and " + field + "  like '%" + value + "%'");
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

    /**
     * Meetod leiab ItemActionType objekti id järgi.
     * 
     * @param id
     *            - otsitava lao toimingu tüübi id
     * @return - null kui lao toimingu tüüpi ei leita.
     */
    public ItemActionType getItemActionType(int id) {

            Session session = HibernateUtil.getSessionFactory().openSession();
            ItemActionType itemActionType = null;
            try {
                    session.getTransaction().begin();
                    itemActionType = (ItemActionType) session.get(ItemActionType.class,
                                    new Long(id));
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("7");
                    session.getTransaction().rollback();
                    log.warn("Error: getItemActionType()");
                    e.printStackTrace();
            }
            return itemActionType;
    }

    /**
     * Meetod, mis sisestab lao toimingu andmebaasi.
     * 
     * @param itemAction
     *            - sisestatav toiming.
     */
    public void insertItemAction(ItemAction itemAction) {

            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                    session.beginTransaction();
                    session.save(itemAction);
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("6");
                    log.warn("EventManager: insertItemAction()" + e.getMessage());
                    e.printStackTrace();
            }
    }

    public void deleteItem(Long id) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Item i = (Item) session.get(Item.class, id);
            if (i != null) {
                    session.delete(i);
                    session.getTransaction().commit();
            }
            session.close();
    }

    /**
     * Meetod, mis uuendab Store Item'i.
     * 
     * @param itemStore
     *            - sisestatav toiming.
     */
    public void updateItemStore(ItemStore itemStore) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            try {
                    session.beginTransaction();
                    session.update(itemStore);
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("5");
                    log.warn("DBUtil: updateItemStore()" + e.getMessage());
                    e.printStackTrace();
            }
    }
    
    public void insertItemStore(ItemStore itemStore){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            try {
                    session.beginTransaction();
                    session.save(itemStore);
                    session.getTransaction().commit();
                    
            } catch (Exception e) {
            	System.out.println("4");
                    log.warn("DBUtil: insertItemStore()" + e.getMessage());
                    e.printStackTrace();
            }
    }

    /**
     * Meetod, mis uuendab toote hinna laos (aka modelit ItemStore), kutsutakse
     * välja andmebaasi protseduur.
     * 
     * @param item
     *            - toode, mille hinda uuendatakse.
     * @param newItemCount
     *            - lisatav toodete kogus.
     * @param newItemPrice
     *            - lisatava toote hind.
     */
    public void updateItemPriceInWareHouse(Item item, int newItemCount,
                    double newItemPrice) {

            long itemId = item.getItem();

            try {
                    DBConnection dbConnection = new DBConnection();
                    Connection connection = dbConnection.getConnection();
                    Statement statement = connection.createStatement();
                    statement.execute("select f_uuenda_lao_hinda (" + itemId + ", "
                                    + newItemCount + ", " + newItemPrice + ");");
                    statement.close();
                    connection.close();
            } catch (SQLException e) {
            	System.out.println("3");
                    log.warn("DBUtil: updateItemPriceInWareHouse()" + e.getMessage());
                    e.printStackTrace();
            }
    }

    /**
     * Meetod, mis leiab ItemStore kande itemi ja store'i põhjal.
     * 
     * @param item - toode.
     * @param store - ladu.
     * @return
     */
    @SuppressWarnings("unchecked")
    public ItemStore getItemStoreByItemAndStore(Item item, Store store) {
            ItemStore itemStore = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                    session.beginTransaction();
                    Query query = session
                                    .createQuery("from ItemStore as i where i.item= :itemId and i.store= :storeId");
                    query.setEntity("itemId", item);
                    query.setEntity("storeId", store);

                    List<ItemStore> itemStoreList = (List<ItemStore>) query
                                    .setMaxResults(1).list();
                    if (!itemStoreList.isEmpty()) {
                            itemStore = (ItemStore) itemStoreList.get(0);
                    }
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("2");
                    log.warn("DBUtil: getItemStoreByItemAndStore()" + e.getMessage());
                    e.printStackTrace();
            }

            return itemStore;
    }
    
    /**
     * Meetod, mis leiab ItemStore kande itemi ja store'i põhjal.
     * 
     * @param item - toode.
     * @param store - ladu.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ItemStore> getItemStoresByItem(Item item) {
            List<ItemStore> itemStore = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                    session.beginTransaction();
                    Query query = session
                                    .createQuery("from ItemStore as i where i.item= :itemId");
                    query.setEntity("itemId", item);
                    itemStore = (List<ItemStore>) query.list();
                    session.getTransaction().commit();
            } catch (Exception e) {
            	System.out.println("1");
                    log.warn("DBUtil: getItemStoresByItem()" + e.getMessage());
                    e.printStackTrace();
            }

            return itemStore;
    }



}
