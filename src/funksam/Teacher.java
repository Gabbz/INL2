package funksam;

import java.util.Scanner;

public class Teacher {

	public static void main(String[] args) {
		
		CourseCatalog allCourses = new CourseCatalog();
		
		Course C725G31 = new Course("Java", "725G31");			//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80");
		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		Assignment INL1 = new Assignment();
		Assignment INL2 = new Assignment();
		
		C725G31.courseStudents.addStudent(jonbo488);
		C725G80.courseStudents.addStudent(roykr837);
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
		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
		String input = scanner.nextLine();
		scanner.close();
		return input;
	}
}
