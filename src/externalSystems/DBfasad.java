package externalSystems;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DBfasad {   

	public DBfasad(){}
	
	public ResultSet returnStudents(String selectedCourse) throws Exception {
		ResultSet studentInfo = null;
		try {			
         
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();      
			String connectionUrl = "jdbc:sqlserver://IDASQL.ad.liu.se;database=725G79;";
			Connection conn = DriverManager.getConnection(connectionUrl, "725G79L5jonbo488", "fkwFNnI4Z");
			
			System.out.println("Initierar hämtning av data från databas");
			try {
			
			PreparedStatement stmt = conn.prepareStatement("SELECT Students.liuID, Students.name " +
														"FROM Students " +
														"INNER JOIN CourseHasStudent ON CourseHasStudent.studentLiuID = Students.liuID " +
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
