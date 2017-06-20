package test;

import funksam.Course;
import funksam.CourseCatalog;

public class CourseCatalogTest {

	public static void main(String[] args) {
		
		//Skapar n�dv�ndig data f�r test
		Course course = new Course ("Java", "725G43", 6);
		Course course2 = new Course ("C#", "725G44", 7);
		
		//Ska skapa en kurskatalog av datatypen CourseCatalog
		CourseCatalog courseCat = new CourseCatalog();
		System.out.println(courseCat.getClass());
		
		//Ska l�gga till de tv� kurserna i kurskatalogen
		courseCat.addCourse(course);
		courseCat.addCourse(course2);
		
		//Ska returnera vald kurs av datatypen Course
		System.out.println(courseCat.selectCourse("725G43").getClass());

		//Ska skriva ut alla kurser i kurskatalogen med namn och kurskod
		courseCat.getCourses();
		
	}

}
