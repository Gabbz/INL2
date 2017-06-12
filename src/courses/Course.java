
package funksam;

public class Course {
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
		System.out.println("Course code: " + this.courseCode + ". Course name: " + this.courseName);
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
	
	public StudentCatalog getCourseStudentCatalog() {
		return this.courseStudents;
	}
	
	public AssignmentCatalog getCourseAssignmentCatalog() {
		return this.courseAssignments;
	}
}