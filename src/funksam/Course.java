
package funksam;

public class Course {				
	String courseName;
	String courseCode;
	
	public Course(String courseName, String courseCode) { //H�R MATAR MAN IN VILKEN KURS MAN VILL SKAPA EGENTLIGEN.
		this.courseName = courseName;
		this.courseCode = courseCode;
	}
	
	public void describeCourse() {
		System.out.println("Course code: " + this.courseCode + ". Course name: " + this.courseName);
	}
	
	//SKA HA ASSIGNMENT CATALOG
	
}