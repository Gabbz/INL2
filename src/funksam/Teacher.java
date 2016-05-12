package funksam;

import java.util.Scanner;

public class Teacher {

	public static void main(String[] args) {
		
		CourseCatalog allCourses = new CourseCatalog();
		
		Course C725G31 = new Course("Java", "725G31");			//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80");
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
		
		
		Course selectedCourse = allCourses.selectCourse(userInput());
		if( selectedCourse != null) {
			selectedCourse.describeCourse();
		}
		
		//DEBUGGING
		//allCourses.getCourses();
		
	
	}
	
	public static String userInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ange en kurskod: ");
		String input = scanner.nextLine();
		scanner.close();
		return input;
	}
}
