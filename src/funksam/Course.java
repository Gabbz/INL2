
package funksam;

public class Course {
	String courseName;
	String courseCode;
	StudentCatalog courseStudents = new StudentCatalog();
	AssignmentCatalog courseAssignments = new AssignmentCatalog();
	
	
	
	public Course(String courseName, String courseCode) { //HÄR MATAR MAN IN VILKEN KURS MAN VILL SKAPA EGENTLIGEN.
		this.courseName = courseName;
		this.courseCode = courseCode;
	}
	
	public void describeCourse() {
		System.out.print("Course code: " + this.courseCode + ". Course name: " + this.courseName);
	}
	
	//SKA HA ASSIGNMENT CATALOG
	//SKA HA STUDENTCATALOG
	

	
}