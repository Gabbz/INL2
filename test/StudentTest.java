package test;

import funksam.Assignment;
import funksam.Course;
import funksam.Student;

public class StudentTest {
	
	public static void main(String[] args) {
		
		//Skapar nödvändiga objekt för testen
		Course C725G31 = new Course("Java", "725G31", 8.0);
		
		//Ska skapa ett objekt av klassen Student
		Student student = new Student("tomso468", "Tomas");
		System.out.println(student.getClass());
		
		//Fler nödvändiga objekt
		Assignment assignment = new Assignment ("INL1", "En uppgift där syftet är att göra klart uppgiften");
		C725G31.getCourseStudentCatalog().addStudent(student);
		C725G31.getCourseAssignmentCatalog().addAssignment(assignment, C725G31);
		
		//Ska skriva ut information om studenten
		student.describeStudent();
		
		/*	Kontrollerar om studenten har klarat kursen. Här har studenten inte det. 
		 * 	Funktionen ska returnera 0 och skriva ut en sträng	*/
		System.out.println(student.checkCourseCompletion());
		
		//Ska skriva ut studentens namn
		System.out.println(student.getStudentName());
		
		//Ska skriva ut studentens LiuID
		System.out.println(student.getLiuID());
		
		//Ska returnera ett objekt av datatypen Assignment
		System.out.println(student.selectAssignment("INL1").getClass());
		
		//Ska returnera ett objekt av datatypen ArrayList<Assignment>
		System.out.println(student.getCourseAssignments().getClass());
		
		//Ska skriva ut de uppgifter som studenten har
		student.getCourseAssignments().get(0).describeAssignment();
		
		//Ska returnera ett objekt av datatypen HashMap<Assignment, String>
		System.out.println(student.getAssignmentGrade().getClass());
		
		//Ska returnera null eftersom studenten inte har ett betyg ännu.
		System.out.println(student.getAssignmentGrade().get(assignment));
		
		//Ska sätta studentens uppgift "assignment"'s betyg till "G"
		student.setAssignmentGrade(assignment, "G");
		
		//Ska skriva ut uppdaterat betyg på uppgiften
		System.out.println(student.getAssignmentGrade().get(assignment));
		
	}
}
