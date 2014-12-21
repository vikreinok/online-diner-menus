package ee.ttu.catering.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFunctions {
	
	public int createTestDiner(String created, String description, String modifyDate, String name){
		
		 int createdTestDinerId = 0;
		 
		 Connection conn = null;
		 
		 ConnectToDB connection = new ConnectToDB();
		 
		 conn = connection.connectToDB();
		 
		 String sql = "INSERT INTO diner ( created, description, modifyDate, name, picture)"
		 		+ " VALUES (?,?,?,?,-1);";
		 
		 try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			 ps.setString(1, created);
			 ps.setString(2, description);
			 ps.setString(3, modifyDate);
			 ps.setString(4, name);
			 ps.executeUpdate();
			 ResultSet generatedKeys = ps.getGeneratedKeys();
			 if(generatedKeys.next()){
				 createdTestDinerId = generatedKeys.getInt(1);
			 }
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return createdTestDinerId;
	}
	
	public int createTestMenu(String name){
		    int createdTestMenuId = 0;
		
            Connection conn = null;
		 
		    ConnectToDB connection = new ConnectToDB();
		 
		    conn = connection.connectToDB();
		 
		    String sql = "INSERT INTO menu ( name)"
		 		       + " VALUES (?);";
		 
			try {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				 ps.setString(1, name);
				 ps.executeUpdate();
				 ResultSet generatedKeys = ps.getGeneratedKeys();
				 if(generatedKeys.next()){
					 createdTestMenuId = generatedKeys.getInt(1);
				 }
				 conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return createdTestMenuId;
		
	}
	
	public void deleteTestData(String id, String menuOrDiner){
         Connection conn = null;
         
         String sql = "";
		 
		 ConnectToDB connection = new ConnectToDB();
		 
		 conn = connection.connectToDB();
		 
		 if(menuOrDiner.equals("MENU")){
			 sql = "DELETE FROM menu where id = ?";
		 }else{
			 sql = "DELETE FROM diner WHERE id = ?;";
		 }
		
		 
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
