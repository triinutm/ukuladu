package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class DBConnection {
	
	public static Connection getConnection() {
		Connection db_connection=null  ;	
		String pwd = "";//"UQW2013";
		String usr = "";//"TUD104435";
		String url = "";//"jdbc:oracle:thin:@//imbi.ld.ttu.ee:1521/orcl.ld.ttu.ee";

		try
		{            
			ResourceBundle bundle = ResourceBundle.getBundle("DBConnection");
			Class.forName(bundle.getString("Driver"));
			url = bundle.getString("url");
			usr = bundle.getString("usr");
			pwd = bundle.getString("pwd");
			db_connection = DriverManager.getConnection(url, usr, pwd);

		}
		catch(Exception ex)
		{  
			//MyLogger.Log("dbconnection.getConnection():" , ex.getMessage());
		}
		return db_connection;
	}


	public static void close(final Connection conn) {

		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
//				MyLogger.Log("dbconnection.close()", ex.getMessage());
			}
		}

	}

	public static void closeStatement(final Statement stmt)
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch (SQLException ex)
			{
//				MyLogger.Log("dbconnection.closeStatement()", ex.getMessage());
			}
		}

	}


	public  static void closeResultSet(final ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException ex)
			{
//				MyLogger.Log("dbconnection.closeResult()", ex.getMessage());
			}
		}
	}

}
