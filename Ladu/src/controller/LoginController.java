package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		LoginService loginService = new LoginService();
		DBUtil dbUtil= new DBUtil();
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		userAccount=dbUtil.getUserByUsername(userName);

		if(userAccount!=null){
			String hashedPassword=loginService.hashPassword(password);
			if(userAccount.getPassw().equals(hashedPassword)){
				request.getSession().setAttribute("user", userAccount);
				response.sendRedirect("/R11_ladu/");
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
