package ee.ttu.catering.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFunctions {
	
	public void createTestDiner(String description, String name){
		
		 Connection conn = null;
		 
		 ConnectToDB connection = new ConnectToDB();
		 
		 conn = connection.connectToDB();
		 
		 String sql = "INSERT INTO diner ( description, name)"
		 		+ " VALUES (?,?);";
		 
		 try {
			PreparedStatement ps = conn.prepareStatement(sql);
			 ps.setString(1, description);
			 ps.setString(2, name);
			 ps.executeUpdate();
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 
		 
		
	}
	
	public void deleteTestData(String id){
         Connection conn = null;
		 
		 ConnectToDB connection = new ConnectToDB();
		 
		 conn = connection.connectToDB();
		 
		 String sql = "DELETE FROM diner WHERE id = ?;";
		 
		 try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
