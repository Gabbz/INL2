package funksam;

import java.util.Scanner;

public class Teacher {

	private static CourseCatalog allCourses = new CourseCatalog();
	
	public static void main(String[] args) {
		
		create();
		setGrade();

		//DEBUGGING
//		Course selectedCourse = allCourses.selectCourse(userInput());
//		if( selectedCourse != null) {
//			selectedCourse.describeCourse();
//			selectedCourse.courseStudents.getStudents();
//			selectedCourse.courseAssignments.getAssignments();
//		}
		
		
		//BETYGSÄTT DEN ALLMÄNNA UPPGIFTEN - FEL!
//		selectedCourse.courseAssignments.selectAssignment("INL1").gradeAssignment("G");
//		System.out.println(selectedCourse.courseAssignments.selectAssignment("INL1").assignmentGrade);
		
		
		//selectedCourse.courseStudents.selectStudent("jonbo488").courseAssignments.get()
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
	
	public static void setGrade() {
		Scanner scanner = new Scanner(System.in);
		//KURS
//		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
//		String course = scanner.nextLine();
		Course selectedCourse = allCourses.selectCourse("725G31");
		if (selectedCourse != null) {
			System.out.println("Vald Kurs: ");
			selectedCourse.describeCourse();
			//STUDENT
//			System.out.print("Välj en student (använd LiUID): ");
//			String student = scanner.nextLine();
			Student selectedStudent = selectedCourse.courseStudents.selectStudent("jonbo488");
			if (selectedStudent != null) {
				System.out.println("Vald student: ");
				selectedStudent.describeStudent();
				
				//UPPGIFT
//				System.out.print("Välj en uppgift: ");
//				String assignment = scanner.nextLine();
				Assignment selectedAssignment = selectedStudent.selectAssignment("INL1");
				if (selectedAssignment != null) {
					System.out.println("Vald uppgift: ");
					selectedAssignment.describeAssignment();
					
					//BETYG
//					System.out.print("Sätt ett betyg (U,G,VG): ");
//					String grade = scanner.nextLine().toUpperCase();
					selectedAssignment.gradeAssignment("G");
					System.out.println("");
				} else System.out.println("Uppgiften hittades inte.");
				//DEBUGGING
//				System.out.println("finns");
//				for (int i= 0; i < selectedStudent.courseAssignments.size(); i++) {
//					System.out.println("inne");
//					System.out.println(selectedStudent.courseAssignments.get(i));
//				}
//				System.out.println("inte");
			} else System.out.println("Studenten hittades inte.");
		} else System.out.println("Kursen hittades inte.");
		scanner.close();
	}
	
	public static void create() {
		Course C725G31 = new Course("Java", "725G31");					//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80");
		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		Assignment INL1 = new Assignment("INL1");
		Assignment INL2 = new Assignment("INL2");
		Assignment INL3 = new Assignment("INL3");
		
		C725G31.courseStudents.addStudent(jonbo488);
		C725G80.courseStudents.addStudent(roykr837);
		C725G31.courseAssignments.addAssignment(INL1, C725G31);
		C725G31.courseAssignments.addAssignment(INL3, C725G31);
		C725G80.courseAssignments.addAssignment(INL2, C725G80);
		
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
	}
}
