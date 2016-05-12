package funksam;

import java.util.ArrayList;

public class StudentCatalog {
	
	private ArrayList<Student> studentList = new ArrayList<>();
	
	public Student selectStudent(String student) {
		for (Student s : this.studentList) {
			if(s.liuID != null && s.liuID.equals(student)) {
			return s;
			} 
		}
		return null;
	}
	
	public void addStudent(Student student) {
		studentList.add(student);
	}
}
