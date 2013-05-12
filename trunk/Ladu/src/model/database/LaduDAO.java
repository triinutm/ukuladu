package model.database;

import hibernate.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

}
