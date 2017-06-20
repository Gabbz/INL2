package gruppr.domain.courses;

import java.util.Observable;
import java.util.Observer;

import gruppr.domain.assignment.Assignment;
import gruppr.domain.assignment.AssignmentCatalog;
import gruppr.domain.students.Student;
import gruppr.domain.students.StudentCatalog;

public class Course implements Observer{
	private String courseName;
	private String courseCode;
	private double maxPoints;
	private StudentCatalog courseStudents = new StudentCatalog();
	private AssignmentCatalog courseAssignments = new AssignmentCatalog();
	
	public Course(String courseName, String courseCode, double maxPoints) { //HÄR MATAR MAN IN VILKEN KURS MAN VILL SKAPA EGENTLIGEN.
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.maxPoints = maxPoints;
	}
	
	public void describeCourse() {
		System.out.println("Kurskod: " + this.courseCode + ". Kursnamn: " + this.courseName);
	}

	public String getCourseName() {
		return this.courseName;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}

	public double getMaxPoints() {
		return this.maxPoints;
	}
	
	public StudentCatalog getCourseStudents() {
		return this.courseStudents;
	}
	
	public void setCourseStudents(StudentCatalog studentCatalog) {
		this.courseStudents = studentCatalog;
	}
	
	public AssignmentCatalog getCourseAssignmentCatalog() {
		return this.courseAssignments;
	}
	public void flush() {
		for (Student s : getCourseStudents().getStudentList()) {
			for (Assignment a : getCourseAssignmentCatalog().getAssignmentList()) {
				s.getCourseAssignments().add(a);
				s.getAssignmentGrade().put(a, "NONE");
			}
			
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
	}
}