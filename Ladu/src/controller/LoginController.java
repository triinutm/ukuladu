package controller;

import hibernate.HibernateDBConnection;
import hibernate.UserAccount;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PWDecoder;
import model.database.LaduDAO;

public class LoginController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8647481938452087512L;

	@Override
	protected void doOnPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
		request.setCharacterEncoding("UTF-8");
		UserAccount userAccount = new UserAccount();
		PWDecoder PWDecoder = new PWDecoder();
		HibernateDBConnection ladu= new HibernateDBConnection();
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		userAccount=ladu.getUserByUserName(userName);

		if(userAccount!=null){
			String hashedPassword=PWDecoder.hashPassword(password);
			if(userAccount.getPassw().equals(hashedPassword)){
				request.getSession().setAttribute("user", userAccount);
				response.sendRedirect("/Ladu_ukuolla");
				System.out.println("I am sexmaniac");
				return;
			}else{
				request.setAttribute("wrongpass", "Vale parool!");
			}
		}else{
			request.setAttribute("wronguser", "Vale kasutaja!");
		}
		view.forward(request, response); 

	}

	@Override
	protected void doOnGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
		view.forward(request, response); 

	}

}
