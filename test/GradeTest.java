package test;

import funksam.Grade;

public class GradeTest {

	public static void main(String[] args) {
		
		//Ska skapa ett objekt av klassen Grade
		Grade grade = new Grade();
		System.out.println(grade.getClass());
		
		//Ska returnera strängen U
		System.out.println(grade.getGrade("U"));
		
		//Ska returnera strängen G
		System.out.println(grade.getGrade("G"));

		//Ska returnera strängen VG
		System.out.println(grade.getGrade("VG"));
	}

}
