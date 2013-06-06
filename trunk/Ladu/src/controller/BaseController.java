package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			doOnGet(request, response);
		}catch (Exception e){
			response.sendRedirect("/Ladu_ukuolla/viga");
			
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 

		try{
			doOnPost(req,resp);
		}catch (Exception e){
			resp.sendRedirect("/Ladu_ukuolla/viga");
			e.printStackTrace();
		}

	}

	protected abstract void doOnPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
	protected abstract void doOnGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;

}



