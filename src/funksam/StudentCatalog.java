package funksam;

import java.util.ArrayList;

public class StudentCatalog {
	
	ArrayList<Student> studentList = new ArrayList<>();
	
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
	
	public void getStudents() {
		for(int i = 0; i < studentList.size(); i++) {
			studentList.get(i).describeStudent();
			System.out.println();
		}
	}
}
