package test;

import funksam.Course; 

public class CourseTest {

	public static void main(String[] args) {
		Course course = new Course ("Java", "725G43", 6);
		
		//Ska skriva ut informationen kursen har
		course.describeCourse();
		
		//Ska hämta kursnamnet
		System.out.println(course.getCourseName());
		
		//Ska hämta kurskoden
		System.out.println(course.getCourseCode());
		
		//Ska hämta max poäng för kursen
		System.out.println(course.getMaxPoints());
		
		//Ska returnera ett objekt av datatypen StudentCatalog
		System.out.println(course.getCourseStudentCatalog().getClass());
		
		//Ska returnera ett objekt av datatypen AssignmentCatalog
		System.out.println(course.getCourseAssignmentCatalog().getClass());
		
	}

}
