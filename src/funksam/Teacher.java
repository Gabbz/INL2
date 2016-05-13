package funksam;

import java.util.Scanner;

public class Teacher {

	private static CourseCatalog allCourses = new CourseCatalog();
	
	public static void main(String[] args) {
		
		create();
		init();
		
		//DEBUGGING
//		Course selectedCourse = allCourses.selectCourse("725G31");
//		if( selectedCourse != null) {
//			selectedCourse.describeCourse();
//			selectedCourse.courseStudents.getStudents();
//			selectedCourse.courseAssignments.getAssignments();
//		}
//		System.out.println(selectedCourse.courseStudents.selectStudent("jonbo488").selectAssignment("INL1").assignmentGrade);
		//BETYGSÄTT DEN ALLMÄNNA UPPGIFTEN - FEL!
//		selectedCourse.courseAssignments.selectAssignment("INL1").gradeAssignment("G");
//		System.out.println(selectedCourse.courseAssignments.selectAssignment("INL1").assignmentGrade);
//		selectedCourse.courseStudents.selectStudent("jonbo488").courseAssignments.get()
//		allCourses.getCourses();
		
		
	
	}
	
	public static String userInput() {
		Scanner scanner1 = new Scanner(System.in);
		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
		String input = scanner1.nextLine();
		scanner1.close();
		return input;
	}
	
	public static void init() {
		int exit = 1;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			setGrade(scanner);
			System.out.println("Sätta fler betyg? (1 = Ja, 0 = Nej)");
			exit = scanner.nextInt();
			scanner.nextLine();	//CLEARAR SCANNERN
			if (exit == 0) break;
		}
		scanner.close();
		
	}
	
	public static void setGrade(Scanner scanner) {
		
		//KURS
//		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
//		String course = scanner.nextLine();
		Course selectedCourse = allCourses.selectCourse("725G31");
		if (selectedCourse != null) {
			System.out.print("Vald Kurs: ");
			selectedCourse.describeCourse();
			//STUDENT
//			System.out.print("Välj en student (använd LiUID): ");
//			String student = scanner.nextLine();
			Student selectedStudent = selectedCourse.courseStudents.selectStudent("jonbo488");
			if (selectedStudent != null) {
				System.out.print("Vald student: ");
				selectedStudent.describeStudent();
				
				//UPPGIFT
				System.out.print("Välj en uppgift: ");
				String assignment = scanner.nextLine();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);
				if (selectedAssignment != null) {
					System.out.print("Vald uppgift: ");
					selectedAssignment.describeAssignment();
					
					//BETYG
					System.out.print("Sätt ett betyg (U,G,VG): ");
					String grade = scanner.nextLine().toUpperCase();
					selectedAssignment.gradeAssignment(grade);
					System.out.println("Studenten " + selectedStudent.studentName + " har fått betyget \"" 
					+ selectedAssignment.assignmentGrade + "\" på uppgiften " + selectedAssignment.assignmentName 
					+ " i kursen " + selectedCourse.courseName + ".");
					int gradePoints = selectedStudent.checkCourseCompletion();
					if (gradePoints >= (selectedCourse.maxPoints*0.82)) System.out.println("Studenten har fått betyget VG i kursen.");
					if ((gradePoints >= (selectedCourse.maxPoints*0.4)) && (gradePoints < (selectedCourse.maxPoints*0.82))) 
					System.out.println("Studenten har fått betyget G i kursen.");
					
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
	}
	
	public static void create() {
		Course C725G31 = new Course("Java", "725G31", 8.0);					//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80", 2.0);
		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		Assignment INL1 = new Assignment("INL1");
		Assignment INL2 = new Assignment("INL2");
		Assignment INL3 = new Assignment("INL3");
		Assignment INL4 = new Assignment("INL4");
		
		C725G31.courseStudents.addStudent(jonbo488);
		C725G80.courseStudents.addStudent(roykr837);
		C725G31.courseAssignments.addAssignment(INL1, C725G31);
		C725G31.courseAssignments.addAssignment(INL2, C725G31);
		C725G31.courseAssignments.addAssignment(INL3, C725G31);
		C725G31.courseAssignments.addAssignment(INL4, C725G31);
		C725G80.courseAssignments.addAssignment(INL1, C725G80);
		C725G80.courseAssignments.addAssignment(INL2, C725G80);
		
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
	}
}
