package service;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;

import model.database.*;
import hibernate.Item;
import hibernate.ItemAction;
import hibernate.ItemStore;
import hibernate.Store;
import hibernate.UserAccount;

public class WarehouseService {
        
        /**
         * Meetod, mis loob lao registreerimise toimingu.
         * @param userAccount - kasutaja
         * @param paramtereMap - parameetrite map
         * @param allStores - ladude nimekiri
         * @param item - arvele võetav toode
         * @return
         */
        public ItemAction createWareHouseRegisterItemAction(UserAccount userAccount, Map<String,String[]> paramtereMap, List<Store> allStores, Item item){
                
                ItemAction itemAction = new ItemAction();
                LaduDAO laduDAO = new LaduDAO();
                
                itemAction.setCreatedBy(userAccount.getEmployeeBySubjectFk());
                itemAction.setItemFk(item);
                String actionPrice = getString(paramtereMap, "warehouse_register_price");
                BigDecimal actionPriceBigInt = new BigDecimal(actionPrice);
                itemAction.setActionPrice(actionPriceBigInt);
                itemAction.setItemActionType(laduDAO.getItemActionType(1));
                
                String selectedStore = getString(paramtereMap, "register_select_store");
                long selectedStoreId = Integer.parseInt(selectedStore);
                Store store = getSelectedStore(allStores, selectedStoreId);
                itemAction.setStoreByStoreToFk(store);
                
                String moveNotes = getString(paramtereMap, "warehouse_register_notes");
                itemAction.setActionNote(moveNotes);

                Calendar today = Calendar.getInstance();
                today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
                Date actionDate = new Date(today.getTime().getTime());
                itemAction.setActionDate(actionDate);
                itemAction.setCreated(actionDate);
                String itemCount = getString(paramtereMap, "warehouse_register_quantity");
                long itemCountLong = Integer.parseInt(itemCount);
                itemAction.setItemCount(itemCountLong);
                
                ItemStore itemStore = laduDAO.getItemStoreByItemAndStore(item, store);
                
                Long itemCountAdded = new Long(itemCount);
                
                if(itemStore != null){
                        Long itemCountBefore = itemStore.getItemCount();
                        itemStore.setItemCount(itemCountBefore+(itemCountAdded));
                        laduDAO.updateItemStore(itemStore);
                }else{
                        ItemStore newItemStore = new ItemStore();
                        newItemStore.setItem(item);
                        newItemStore.setItemCount(itemCountAdded);
                        newItemStore.setStore(store);
                        laduDAO.insertItemStore(newItemStore);
                }
                return itemAction;
        }

        /**
         * Meetod, mis leiab etteantud mapist atribuudi järgi väärtuse.
         * @param map - parameetrite map
         * @param attribute - otsitav atribuut
         * @return - null, kui väärtust ei leita
         */
        public String getString(Map<String, String[]> map, String attribute) {
                String[] array = map.get(attribute);
                if (array != null && array.length > 0) {
                        return array[0];
                }
                return null;
        }
        
        public Store getSelectedStore(List<Store> allStores, long selectedStore){
                Store restultStore = null;
                
                for(Store store : allStores){
                        if(store.getStore() == selectedStore){
                                restultStore = store;
                        }
                }
                return restultStore;
        }
        
        /**
         * Toote laost lattu liigutamise meetod.
         * @param userAccount
         * @param paramtereMap
         * @param allStores
         * @param item
         * @return
         */
        public ItemAction createWareHouseMoveItemAction(UserAccount userAccount, Map<String,String[]> paramtereMap, List<Store> allStores, Item item){
                
                ItemAction itemAction = new ItemAction();
                LaduDAO laduDAO = new LaduDAO();
                
                String selectedStoreFrom = getString(paramtereMap, "move_from_store");
                long selectedStoreFromId = Integer.parseInt(selectedStoreFrom);
                Store storeFrom = getSelectedStore(allStores, selectedStoreFromId);
                
                String selectedStoreTo = getString(paramtereMap, "move_to_store");
                long selectedStoreToId = Integer.parseInt(selectedStoreTo);
                Store storeTo = getSelectedStore(allStores, selectedStoreToId);
                
                itemAction.setStoreByStoreFromFk(storeFrom);
                itemAction.setStoreByStoreToFk(storeTo);
                
                itemAction.setCreatedBy(userAccount.getEmployeeBySubjectFk());
                itemAction.setItemFk(item);
                
                String itemCount = getString(paramtereMap, "warehouse_move_quantity");
                long itemCountLong = Integer.parseInt(itemCount);
                itemAction.setItemCount(itemCountLong);
                
                String moveNotes = getString(paramtereMap, "warehouse_move_notes");
                itemAction.setActionNote(moveNotes);
                
                Calendar today = Calendar.getInstance();
                today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
                Date actionDate = new Date(today.getTime().getTime());
                itemAction.setActionDate(actionDate);
                itemAction.setCreated(actionDate);
                
                itemAction.setItemActionType(laduDAO.getItemActionType(3));
                return itemAction;      
        }
        
        public ItemStore getItemStore(Item item, List<Store> allStores, long selectedStore){
                
                LaduDAO laduDAO = new LaduDAO();
                ItemStore itemStore = new ItemStore();
                Store store = getSelectedStore(allStores, selectedStore);
                itemStore = laduDAO.getItemStoreByItemAndStore(item, store);
                return itemStore;
                
        }
        public ItemAction createWareHouseRemoveItemAction(UserAccount userAccount, Map<String,String[]> paramtereMap, List<Store> allStores, Item item){
                ItemAction itemAction=new ItemAction();
                LaduDAO laduDAO = new LaduDAO();
                String selectedStoreFrom = getString(paramtereMap, "remove_from_store");
                long selectedStoreFromId = Integer.parseInt(selectedStoreFrom);
                Store storeFrom = getSelectedStore(allStores, selectedStoreFromId);
                
                itemAction.setStoreByStoreFromFk(storeFrom);
                
                itemAction.setCreatedBy(userAccount.getEmployeeBySubjectFk());
                itemAction.setItemFk(item);
                
                itemAction.setItemActionType(laduDAO.getItemActionType(2));
                
                Calendar today = Calendar.getInstance();
                today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
                Date actionDate = new Date(today.getTime().getTime());
                itemAction.setActionDate(actionDate);
                itemAction.setCreated(actionDate);
                
                String itemCount = getString(paramtereMap, "warehouse_remove_quantity");
                long itemCountLong = Integer.parseInt(itemCount);
                itemAction.setItemCount(itemCountLong);
                
                String removeNotes = getString(paramtereMap, "warehouse_remove_notes");
                itemAction.setActionNote(removeNotes);
                
                return itemAction;
        }
        
        public void createItemStoreXml(Item item, String scontext) {
        try {
                
        LaduDAO laduDAO = new LaduDAO();
        
        List<ItemStore> itemstores = laduDAO.getItemStoresByItem(item);
        File file = new File(scontext + "storeList.xml");
        file.createNewFile();
        
        XStream xstream = new XStream();
        xstream.alias("itemstore", ItemStore.class);
        
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><?xml-stylesheet type=\"text/xsl\" href=\"storeList.xsl\"?>");
                bw.write("<itemstorelist>");
            for(ItemStore itemStore : itemstores){
                String xml = xstream.toXML(itemStore);
                bw.write(xml);
            }
        bw.write("</itemstorelist>");  
        bw.close();     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}