package controller;

import hibernate.Item;
import hibernate.ItemType;
import hibernate.TypeAttribute;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import model.AttributeModel;
import model.FormUtil;
import model.SearchForm;
import model.database.DBConnection;
import model.database.LaduDAO;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/Search.jsp");
		String catalog = request.getParameter("cat");
		if (StringUtils.isNotBlank(catalog) && StringUtils.isNumeric(catalog)) {
			SearchForm form = new SearchForm();
			LaduDAO m = new LaduDAO();
			System.out.println("enne item");
			ItemType item =  m.getItemTypeById(Integer.parseInt(catalog));
			System.out.println("item saadud");
			form.setType(item.getTypeName());
			System.out.println("enne listi");
			List<TypeAttribute> itemAttributes = m.getTypeAttributesByItemType(Integer.parseInt(catalog));
			System.out.println("peale lisi");
			for (TypeAttribute attribute : itemAttributes) {
				System.out.println("foris");
				AttributeModel attibute = new AttributeModel();
				attibute.setAttributeName(attribute.getItemAttributeType().getTypeName());
				form.getAttributes().put(attribute.getItemAttributeType().getItemAttributeType(), attibute);
			}
			System.out.println("peale for'i");
			request.setAttribute("form", form);
		}else{
			request.setAttribute("form", new SearchForm());
		}       
		System.out.println("enne forward");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/Search.jsp");
		SearchForm form = FormUtil.getSearchFormFromRequestMap(request.getParameterMap());
		LaduDAO dbUtil = new LaduDAO();
		System.out.println("otsing");
		List<Item> items = dbUtil.searchItems(form, request.getParameter("cat"));
		System.out.println("tulemused");
		request.setAttribute("items", items);
		request.setAttribute("form", form);
		view.forward(request, response);	
	}

}
