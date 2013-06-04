package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AttributeModel;
import model.FormUtil;
import model.ProductModel;
import model.database.LaduDAO;

import service.ProductValidator;

import hibernate.Item;
import hibernate.ItemType;
import hibernate.TypeAttribute;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/insert")
public class InsertController extends BaseController implements Servlet {
        private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsertController() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doOnPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {
        RequestDispatcher view = request.getRequestDispatcher("/insert.jsp");
        Map<String, String[]> parameterMap = request.getParameterMap();
        ProductModel m = FormUtil.getProductFromParameterMap(parameterMap);
        ProductValidator validator = new ProductValidator();
        boolean isValid = validator.validateProductModel(m);
        if(isValid){
            LaduDAO laduDAO = new LaduDAO();
            Item item = laduDAO.saveItem(m);
            if(item != null){
                response.sendRedirect(request.getContextPath()+"/product?id="+item.getItem());
                return;
            }
        }
        request.setAttribute("productModel", m);
        view.forward(request, response);
    }

    @Override
    protected void doOnGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            SQLException {
        if(request.getParameter("cat") != null){
            RequestDispatcher view = request.getRequestDispatcher("/insert.jsp");
            Integer id = Integer.parseInt(request.getParameter("cat"));
            LaduDAO m = new LaduDAO();
            List<TypeAttribute> itemAttributes = m.getTypeAttributesByItemType(id);
            ProductModel model = new ProductModel();
            ItemType type = m.getItemTypeById(id);
            model.setType(type.getTypeName());
            model.setItemType(id.toString());
            for(TypeAttribute attribute : itemAttributes){
                AttributeModel attibute = new AttributeModel();
                attibute.setAttributeName(attribute.getItemAttributeType().getTypeName());              
                model.getAttributes().put(attribute.getTypeAttribute(), attibute);
            }
            request.setAttribute("productModel", model);
            view.forward(request, response);
        }
        
        
    }

}