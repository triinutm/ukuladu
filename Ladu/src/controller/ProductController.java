package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import hibernate.Item;
import hibernate.ItemAttribute;
import hibernate.ItemType;
import hibernate.TypeAttribute;

import service.ProductValidator;


import model.AttributeModel;
import model.FormUtil;
import model.ProductModel;
import model.database.LaduDAO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product")
public class ProductController extends BaseController {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doOnPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher view = request.getRequestDispatcher("/productEdit.jsp");
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("id")) {
            LaduDAO laduDAO = new LaduDAO();
            ProductModel m = FormUtil.getProductFromParameterMap(parameterMap);
            if (parameterMap.containsKey("delete")) {
                Item item = laduDAO.getItemById(Integer.parseInt(request.getParameter("id")));
                if (item != null) {
                    StringBuilder errors = new StringBuilder();
                    if (item.getItems() != null && item.getItems().size() > 0) {
                        errors.append("Kustutamine ebaõnnestus, sest tootega on seotud laotoiminguid!<br>");
                    } else if (item.getItemPriceLists() != null && item.getItemPriceLists().size() > 0) {
                        errors.append("Kustutamine ebaõnnestus, sest tootega on seotud hinnakirjasid!<br>");
                    } else if (item.getItemStores() != null && item.getItemStores().size() > 0) {
                        errors.append("Kustutamine ebaõnnestus, sest tooted on juba laos arvel!<br>");
                    } else {
                        laduDAO.deleteItem(item.getItem());
                        response.sendRedirect(request.getContextPath());
                        return;
                    }
                    request.setAttribute("error", errors.toString());
                }
            } else {
                ProductValidator validator = new ProductValidator();
                boolean isValid = validator.validateProductModel(m);
                if (isValid) {
                    Item item = laduDAO.updateItem(m, new Long(parameterMap.get("id")[0]));
                    if (item != null) {
                        response.sendRedirect(request.getContextPath() + "/product?id=" + request.getParameter("id"));
                        return;
                    }
                }
            }
            request.setAttribute("productModel", m);
        }
        view.forward(request, response);
    }

    @Override
    protected void doOnGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher view = request.getRequestDispatcher("/productEdit.jsp");
        if (request.getParameter("id") != null && StringUtils.isNumeric(request.getParameter("id"))) {
            LaduDAO laduDAO = new LaduDAO();
            // Otsime baasist toote andmed
            Item item = laduDAO.getItemById(Integer.parseInt(request.getParameter("id")));
            if (item != null) {
                ProductModel model = new ProductModel();
                model.getName().setAttributeValue(item.getName());
                model.getDescription().setAttributeValue(item.getDescription());
                model.getPrice().setAttributeValue(item.getSalePrice().toString());
                Set<TypeAttribute> typeAttributes = item.getItemType().getTypeAttributes();
                Map<Long, TypeAttribute> typeAttributeIdMap = new HashMap<Long, TypeAttribute>();
                for (TypeAttribute t : typeAttributes) {
                    typeAttributeIdMap.put(t.getItemAttributeType().getItemAttributeType(), t);
                }
                Long typeId;
                // Käime läbi kõik toote attribuudid, mis baasis olemas on
                for (ItemAttribute attribute : item.getItemAttributes()) { 
                    AttributeModel attributeModel = new AttributeModel();
                    attributeModel.setAttributeId(attribute.getItemAttribute());
                    attributeModel.setAttributeName(attribute.getItemAttributeType().getTypeName());
                    if (attribute.getDataType().equals(1L)) {
                        attributeModel.setAttributeValue(attribute.getValueText());
                    } else if (attribute.getDataType().equals(2L)) {
                        attributeModel.setAttributeValue(attribute.getValueNumber().toString());
                    }
                    typeId = typeAttributeIdMap.get(attribute.getItemAttributeType().getItemAttributeType()).getTypeAttribute();
                    typeAttributeIdMap.remove(attribute.getItemAttributeType().getItemAttributeType());
                    model.getAttributes().put(typeId, attributeModel);
                }
                // kui baasi polnud kõiki attribuute kohe lisatud, siis lisame
                // siin ka ülejäänud toote attribuudid
                for (TypeAttribute t : typeAttributeIdMap.values()) {
                    AttributeModel attibute = new AttributeModel();
                    attibute.setAttributeName(t.getItemAttributeType().getTypeName());
                    model.getAttributes().put(t.getTypeAttribute(), attibute);
                }
                ItemType type = item.getItemType();
                model.setType(type.getTypeName());
                model.setItemType(String.valueOf(type.getItemType()));

                request.setAttribute("productModel", model);
            }
        }

        view.forward(request, response);
    }

}
