package gruppr.technical_services.externalSystems;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import gruppr.technical_services.externalSystems.StudentDTO;

public class PersistentStorage {
	
	private DBfasad dbFasad;
	
	private ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
  
	public PersistentStorage() { 
		dbFasad = new DBfasad(); 
	}
	
	public Map<String, ArrayList<StudentDTO>> getStudents(String selectedCourse) throws Exception {
		
		ResultSet studentInfo = this.dbFasad.returnStudents(selectedCourse);
		
		
		Map<String, ArrayList<StudentDTO>> returnMap = new HashMap<String, ArrayList<StudentDTO>>();
		
		while (studentInfo.next()) {
			
			String studentLiID = studentInfo.getString("liuID");
			String studentName = studentInfo.getString("name");
			
			StudentDTO currentStudent = new StudentDTO(studentLiID, studentName);
			students.add(currentStudent);
		}
		
		returnMap.put(selectedCourse, students);
		return returnMap;
	}

}