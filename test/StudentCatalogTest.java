package test;

import funksam.StudentCatalog;
import funksam.Student;

public class StudentCatalogTest {

	public static void main(String[] args) {
		
		//Skapar nödvändiga objekt för testet
		Student student = new Student("tomso468", "Tomas");
		
		//Ska skapa ett objekt av klassen StudentCatalog
		StudentCatalog studCat = new StudentCatalog();
		System.out.println(studCat.getClass());
		
		//Ska lägga till en student i katalogen
		studCat.addStudent(student);
		System.out.println("Är listan tom? " + studCat.getStudentList().isEmpty());
		
		//Ska välja en student och returnera studentens objekt
		System.out.println("Vald student: " + studCat.selectStudent("tomso468").getStudentName());
		System.out.println(studCat.selectStudent("tomso468").getClass());
		
		//Ska skriva ut alla studenter
		studCat.getAllStudents();
		
		//Ska returnera lista med studenterna av datatypen ArrayList<Student>
		System.out.println(studCat.getStudentList().getClass());
	}

}
