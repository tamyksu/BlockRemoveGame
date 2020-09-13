

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

	private  Connection conn;
	
	
	public  void establishDBConnection() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} 
		catch (Exception ex) {
			/* handle the error*/
			System.out.println("Driver definition failed");
		}

		try 
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/highest_grade?serverTimezone=IST","root","Aa123456");
			System.out.println("SQL connection succeed");
			String query="UPDATE grade_table SET grade = 0 WHERE (`index` = 1)";
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.execute();
		      
		     // conn.close();

		} catch (SQLException ex) 
		{/* handle any errors*/
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public int getData() throws SQLException {
		PreparedStatement  stmt;
		stmt = conn.prepareStatement("SELECT grade FROM highest_grade.grade_table");
		//String query="SELECT grade FROM highest_grade.grade_table";
		ResultSet  rs = stmt.executeQuery();
		//ResultSet rs=stmt.executeQuery(query);
		rs.next();
		   int result=rs.getInt(1);
		   return result;
	}
	}
	


