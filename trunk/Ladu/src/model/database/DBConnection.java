package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;



public class DBConnection {
	 static Logger logger = Logger.getLogger(DBConnection.class);
	
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
	
	 public static ResultSet execute(String sql){
         Connection connection = getConnection();
         try {
                 Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery(sql);
              
                 return result;
                
         } catch (SQLException e) {
                 return null;
//         }finally{
//                 try {
////                         connection.close();
//                	 System.out.println("hy");
//                 } catch (SQLException e) {
//                	 System.out.println("Ühendus kinni pandud!");
//                         logger.error("Yhenduse sulgemine eba6nnestus!");
//                 }
         }  
 }    

}
