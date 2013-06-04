package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hibernate.Item;
import hibernate.ItemAction;
import hibernate.ItemStore;
import hibernate.Store;
import hibernate.UserAccount;

import service.WarehouseService;
import model.database.*;

/**
 * Lao toiminguid juhtiv kontroller.
 * Näite url: localhost/warehouse?item=3
 */
@WebServlet("/warehouse")
public class WareHouseController extends BaseController {

        private static final long serialVersionUID = 1L;

        
        @Override
        protected void doOnGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException, SQLException {
                        RequestDispatcher view = request.getRequestDispatcher("/warehouse.jsp");
                        request.setCharacterEncoding("UTF-8");
                        
                        if(request.getParameter("item") != null){
                                WarehouseService wareHouseService = new WarehouseService();
                                Integer itemId = Integer.parseInt(request.getParameter("item"));
                                LaduDAO ladu = new LaduDAO();
                                Item item = ladu.getItemById(itemId);
                                List<Store> allStores = ladu.getAllWareHouses();
                                if(item != null){
                                        request.setAttribute("item", item);
                                        if(allStores != null){
                                                request.setAttribute("allStores", allStores);
                                        }
                                }
                                List<ItemStore> itemStores = ladu.getItemStoresByItem(item);
                                request.setAttribute("itemStores", itemStores);
                                String scontext = getServletContext().getRealPath("/");
                                wareHouseService.createItemStoreXml(item,scontext);
                        }
                        view.forward(request, response); 
        }


        @Override
        protected void doOnPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException,
                        SQLException {
                RequestDispatcher view = request.getRequestDispatcher("/warehouse.jsp");
                request.setCharacterEncoding("UTF-8");
                
                UserAccount user = (UserAccount)request.getSession().getAttribute("user");
                Map<String,String[]> paramtereMap = request.getParameterMap();
                LaduDAO ladu = new LaduDAO();
                WarehouseService wareHouseService = new WarehouseService();
                
                List<Store> allStores = ladu.getAllWareHouses();
                int itemId = Integer.parseInt(wareHouseService.getString(paramtereMap, "item_id"));
                Item item = ladu.getItemById(itemId);
                
                if(request.getParameter("action").equals("register") && user != null && item != null && allStores != null){
                        
                        ItemAction itemActionRegister = wareHouseService.createWareHouseRegisterItemAction(user, paramtereMap, allStores, item);
                        if(itemActionRegister != null){
                                String itemCount = wareHouseService.getString(paramtereMap, "warehouse_register_quantity");
                                String actionPrice = wareHouseService.getString(paramtereMap, "warehouse_register_price");
                                ladu.updateItemPriceInWareHouse(item, Integer.parseInt(itemCount),Double.parseDouble(actionPrice));
                                ladu.insertItemAction(itemActionRegister);                            
                                request.setAttribute("register_successful", "Toote arvele v6tmine 6nnestus!");
                        }
                        request.setAttribute("item", item);
                        request.setAttribute("allStores", allStores);
                        
                }else if(request.getParameter("action").equals("remove") && user != null && item != null && allStores != null){
                        String selectedStoreFrom = wareHouseService.getString(paramtereMap, "remove_from_store");
                        long selectedStoreFromId = Integer.parseInt(selectedStoreFrom);
                        
                        String itemCountOnMove =  wareHouseService.getString(paramtereMap, "warehouse_remove_quantity");
                        Long itemCountOnMove1 = new Long(itemCountOnMove); 
                        
                        ItemStore itemStoreFrom = wareHouseService.getItemStore(item, allStores, selectedStoreFromId);
                        
                        System.out.println("LAOS ON TOOTEID VEEL : " + itemStoreFrom.getItemCount());
                        
                        if(itemStoreFrom.getItemCount() == null){ //kontrollime, kas saadud lao kirjes on tootel kogus olemas.
                                request.setAttribute("move_from_err", "Antud toodet selles laos pole!");
                        }
                        else if(itemCountOnMove1 > (itemStoreFrom.getItemCount()) ){ //kontrollime, kas laos on piisavalt tooteid mida liigutada
                                request.setAttribute("move_from_err_counts", "Toodet pole laos piisavalt!");
                        }else{
                                itemStoreFrom.setItemCount(itemStoreFrom.getItemCount() - (itemCountOnMove1)); //lahutame olemasolevast laost toodete koguse maha
                                        
                                        ladu.updateItemStore(itemStoreFrom);
                                        ItemAction itemActionRemove = wareHouseService.createWareHouseRemoveItemAction(user, paramtereMap, allStores, item);
                                        if(itemActionRemove != null){
                                                ladu.insertItemAction(itemActionRemove);
                                                request.setAttribute("remove_successful", "Toote eemaldamine 6nnestus!");
                                        }
                                
                                }
                        System.out.println("OlenUkus" + item.getName());
                        request.setAttribute("item", item);
                        request.setAttribute("allStores", allStores);
                        
                        
                }else if(request.getParameter("action").equals("move") && user != null && item != null && allStores != null){
                        
                        String selectedStoreFrom = wareHouseService.getString(paramtereMap, "move_from_store");
                        long selectedStoreFromId = Integer.parseInt(selectedStoreFrom);
                        
                        String selectedStoreTo = wareHouseService.getString(paramtereMap, "move_to_store");
                        long selectedStoreToId = Integer.parseInt(selectedStoreTo);
                        
                        String itemCountOnMove =  wareHouseService.getString(paramtereMap, "warehouse_move_quantity");
                        Long itemCountOnMoveBigDecimal = new Long(itemCountOnMove); 
                        
                        ItemStore itemStoreFrom = wareHouseService.getItemStore(item, allStores, selectedStoreFromId);
                        ItemStore itemStoreTo = wareHouseService.getItemStore(item, allStores, selectedStoreToId);
                        
                        if(itemStoreFrom.getItemCount() == null){ //kontrollime, kas saadud lao kirjes on tootel kogus olemas.
                                request.setAttribute("move_from_err", "Antud toodet selles laos pole!");
                        }
                        else if(itemCountOnMoveBigDecimal > (itemStoreFrom.getItemCount()) ){ //kontrollime, kas laos on piisavalt tooteid mida liigutada
                                request.setAttribute("move_from_err_counts", "Toodet pole laos piisavalt!");
                        }else{
                                if(itemStoreTo != null){ //kui toode on olemas laos, siis lisame kogust.
                                        
                                        itemStoreFrom.setItemCount(itemStoreFrom.getItemCount() - (itemCountOnMoveBigDecimal)); //lahutame olemasolevast laost toodete koguse maha
                                        itemStoreTo.setItemCount(itemStoreTo.getItemCount() + (itemCountOnMoveBigDecimal)); //lisame uude lattu toodete koguse juurde
                                        
                                        ladu.updateItemStore(itemStoreFrom);
                                        ladu.updateItemStore(itemStoreTo);
                                        
                                        ItemAction itemActionMove = wareHouseService.createWareHouseMoveItemAction(user, paramtereMap, allStores, item);
                                        if(itemActionMove != null){
                                                ladu.insertItemAction(itemActionMove);
                                                request.setAttribute("move_successful", "Toote ladude vahel liigutamine 6nnestus!");
                                        }
                                }else{ //kui toodet lisatavas laos pole, siis loome uue lao kirje
                                        ItemStore newItemStore = new ItemStore();
                                        newItemStore.setItem(item);
                                        newItemStore.setItemCount(itemCountOnMoveBigDecimal);
                                        newItemStore.setStore(wareHouseService.getSelectedStore(allStores, selectedStoreToId));
                                        ladu.insertItemStore(newItemStore);
                                        
                                        ItemAction itemActionMove = wareHouseService.createWareHouseMoveItemAction(user, paramtereMap, allStores, item);
                                        if(itemActionMove != null){
                                                ladu.insertItemAction(itemActionMove);
                                                request.setAttribute("move_successful", "Toote ladude vahel liigutamine 6nnestus!");
                                        }
                                }
                        }
                        request.setAttribute("item", item);
                        request.setAttribute("allStores", allStores);
                }else{
                        request.setAttribute("parameter_needed", "Laotoimingute tegemiseks on parameeter action vajalik!");
                }
                List<ItemStore> itemStores = ladu.getItemStoresByItem(item);
                request.setAttribute("itemStores", itemStores);
                String scontext = getServletContext().getRealPath("/");
                wareHouseService.createItemStoreXml(item,scontext);
                view.forward(request, response);        
        }
}
