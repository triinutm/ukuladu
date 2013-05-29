package controller;

import hibernate.HibernateDBConnection;
import hibernate.ItemType;
import hibernate.UserAccount;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.TreeView;

/**
 * Servlet implementation class MainPageController
 */
@WebServlet("/")
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainPageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/MainPage.jsp");
		UserAccount userAccount = null;
		userAccount = (UserAccount) request.getSession().getAttribute("user");
		if(userAccount != null) {
			long cat = -1;
			if (request.getParameter("cat") != null){
				cat = Long.valueOf(request.getParameter("cat")).longValue();
				HibernateDBConnection hbConn = new HibernateDBConnection();
				ItemType selItem = hbConn.getItemTypeById((int) cat);
				Set<ItemType> selItemSubs = selItem.getItemTypes();
				if ( selItemSubs != null && selItemSubs.size() == 0 ) {
					request.setAttribute("editCat", cat);
				}
			}
			TreeView treeView = new TreeView();
			String treeViewHTML = treeView.drawTree(cat);
			request.setAttribute("cat", cat);
			request.setAttribute("treeView", treeViewHTML);
			view.forward(request, response);
		} else {
			response.sendRedirect("/Ladu/login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
