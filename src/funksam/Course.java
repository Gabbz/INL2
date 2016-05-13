
package funksam;

public class Course {
	String courseName;
	String courseCode;
	double maxPoints;
	StudentCatalog courseStudents = new StudentCatalog();
	AssignmentCatalog courseAssignments = new AssignmentCatalog();
	
	
	
	public Course(String courseName, String courseCode, double maxPoints) { //HÄR MATAR MAN IN VILKEN KURS MAN VILL SKAPA EGENTLIGEN.
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.maxPoints = maxPoints;
	}
	
	public void describeCourse() {
		System.out.println("Course code: " + this.courseCode + ". Course name: " + this.courseName);
	}
	
	//SKA HA ASSIGNMENT CATALOG
	//SKA HA STUDENTCATALOG
	

	
}