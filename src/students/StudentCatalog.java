package funksam;

import java.util.ArrayList;

public class StudentCatalog {
	
	private ArrayList<Student> studentList = new ArrayList<>();
	
	public Student selectStudent(String student) {
		for (Student s : this.studentList) {
			if(s.getLiuID() != null && s.getLiuID().equals(student)) {
			return s;
			} 
		}
		return null;
	}
	
	public void addStudent(Student student) {
		studentList.add(student);
	}
	
	public void getAllStudents() {
		for(int i = 0; i < studentList.size(); i++) {
			studentList.get(i).describeStudent();
			System.out.println();
		}
	}
	
	public ArrayList<Student> getStudentList() {
		return this.studentList;
	}
}
