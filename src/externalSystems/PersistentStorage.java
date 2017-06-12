package externalSystems;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import students.Student;
import students.StudentCatalog;

public class PersistentStorage {
	
	private DBfasad dbFasad;
  
	public PersistentStorage() { 
		dbFasad = new DBfasad(); 
	}
	
	public Map<String, StudentCatalog> getStudents(String selectedCourse) throws Exception {
		
		ResultSet studentInfo = this.dbFasad.returnStudents(selectedCourse);
		
		StudentCatalog returnStudentCatalog = new StudentCatalog();
		Map<String, StudentCatalog> returnMap = new HashMap<>();
		
		while (studentInfo.next()) {
			
			String studentLiID = studentInfo.getString("liuID");
			String studentName = studentInfo.getString("name");
			
			Student currentStudent = new Student(studentLiID, studentName);
			returnStudentCatalog.addStudent(currentStudent);
		}
		
		returnMap.put(selectedCourse, returnStudentCatalog);
		return returnMap;
	}

}