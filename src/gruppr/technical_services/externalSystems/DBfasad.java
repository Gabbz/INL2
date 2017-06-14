package gruppr.technical_services.externalSystems;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DBfasad {   

	public DBfasad(){}
	
	public ResultSet returnStudents(String selectedCourse) throws Exception {
		ResultSet studentInfo = null;
		try {			
         
			
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			String url = "jdbc:mysql://localhost:3306/personal_AkiraDatabase";
//			String username = "xxxx";
//			String password = "xxxxx"
//			Connection connection = null;
//			try {
//			    System.out.println("Connecting database...");
//			    connection = DriverManager.getConnection(url, username, password);
//			    System.out.println("Database connected!");
//			} catch (SQLException e) {	
//			    throw new RuntimeException("Cannot connect the database!", e);
//			} finally {
//			    System.out.println("Closing the connection.");
//			    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
//			}
//			
			
			
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/725g79";
			Connection conn = DriverManager.getConnection(connectionUrl, "725G79L5jonbo488", "fkwFNnI4Z");
			System.out.println("yoigen");
			
			System.out.println("Initierar hämtning av data från databas");
			try {
			
			PreparedStatement stmt = conn.prepareStatement("SELECT students.liuID, students.name " +
														"FROM students " +
														"INNER JOIN coursehasstudent ON coursehasstudent.studentLiuID = students.liuID " +
														"WHERE courseCode = ?");

			stmt.setString(1, "C" + selectedCourse);		
			studentInfo = stmt.executeQuery();
			System.out.println("Hämtning av data lyckades");
			
			} catch (SQLServerException e) {
				if(studentInfo == null) System.out.println("Något gick fel vid hämtningen " + e);
			}
		}
		catch (ClassNotFoundException cnfe) {
			//Problem med att ladda drivern?
			System.err.println ("ERROR: " + cnfe.toString());
			System.exit(1);
		}
		catch (SQLException sqle) {
			//Problem kommunicera med DB (ingen anslutning, ogiltigt login ...)
			System.err.println ("ERROR: " + sqle.toString());
			System.exit(1);
		}
		return studentInfo;
	}
}
