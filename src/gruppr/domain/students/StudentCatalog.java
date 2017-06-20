package gruppr.domain.students;

import java.util.ArrayList;

import gruppr.technical_services.externalSystems.StudentDTO;

public class StudentCatalog {
	
	private int size = 0;
	private ArrayList<Student> studentList = new ArrayList<>();
	
	public StudentCatalog(){}
	
	public StudentCatalog(ArrayList<StudentDTO> arrayList) {
		for (StudentDTO dto : arrayList) {
			studentList.add(new Student(dto));
		}
	}

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
		this.size++;
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
	
	public int getSize() {
		return this.size;
	}
}
