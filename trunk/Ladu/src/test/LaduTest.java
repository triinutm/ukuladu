package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import hibernate.Item;
import model.ProductModel;
import model.database.LaduDAO;

import org.junit.Test;

import service.ProductValidator;

public class LaduTest {
	
	@Test
    public void testUpdateItemPriceInWareHouse(){
            
            LaduDAO laduDAO = new LaduDAO();
            Item itemBefore = laduDAO.getItemById(1);
            BigDecimal itemStorePriceBefore = itemBefore.getStorePrice();
            laduDAO.updateItemPriceInWareHouse(itemBefore, 30, 800);
            
            Item itemAfter = laduDAO.getItemById(1);
            BigDecimal itemStorePriceAfter = itemAfter.getStorePrice();
            assertNotNull(itemBefore);
            assertNotNull(itemAfter);
            assertFalse(itemStorePriceBefore.equals(itemStorePriceAfter));
    }
}
