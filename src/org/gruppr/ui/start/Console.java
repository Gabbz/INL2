package org.gruppr.ui.start;

import java.util.InputMismatchException;
import java.util.Scanner;

import assignment.Assignment;
import courses.Course;
import students.Student;

public class Console {
	
	private Scanner scanner;

	public Console() {
		this.scanner = new Scanner(System.in);
		
	}
	
	public int keepAlive(int exit) {
		System.out.println();
		System.out.println("Sätta fler betyg? (1 = Ja, 0 = Nej)");
		try {
			exit = this.scanner.nextInt();
		} catch (InputMismatchException ex){
			System.out.println();
			System.out.println("Enter a valid number");
			System.out.println();
		}
		this.scanner.nextLine();	//CLEARAR SCANNERN				
		return exit;
		
	}
	
	public void closeScanner() {
		this.scanner.close();
		
	}
	
	//SELECTIONS
	public String chooseCourse() {
		System.out.println();
		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
		return scanner.nextLine();
		
	}
	
	public String chooseStudent() {
		System.out.println();
		System.out.print("Välj en student (använd LiUID): ");
		return scanner.nextLine();
		
	}

	
	public String chooseAssignment() {
		System.out.println();
		System.out.print("Välj en uppgift: ");
		return scanner.nextLine();
		
	}
	
	public String chooseGrade(Assignment selectedAssignment) {
		System.out.print("Sätt ett betyg (");
		for (int x = 0; x < selectedAssignment.getGradeList().getListOfGrades().size(); x++) {
			System.out.print(selectedAssignment.getGradeList().getListOfGrades().get(x));
			if (x < selectedAssignment.getGradeList().getListOfGrades().size()-1) {
				System.out.print(", ");
			}
		}
		System.out.println("): ");
		return scanner.nextLine().toUpperCase();
		
	}

	//PRINTS
	public void printChoosenCourse(Course selectedCourse) {
		System.out.print("Vald Kurs: ");
		selectedCourse.describeCourse();
		System.out.println();
		
		System.out.println("Studenter som tillhör kursen:");
		for (int i = 0; i < selectedCourse.getCourseStudents().getSize(); i++) {
			selectedCourse.getCourseStudents().getStudentList().get(i).describeStudent();
		}
		
	}
	
	public void printChoosenStudent(Student selectedStudent, Course selectedCourse) {
		selectedStudent.addObserver(selectedCourse);		//Adding observer
		System.out.print("Vald student: ");
		selectedStudent.describeStudent();
		System.out.println();
		
		System.out.println("Uppgifter som tillhör kursen och som studenten har:");
		for (Assignment a : selectedCourse.getCourseAssignmentCatalog().getAssignmentList()) {
			a.describeAssignment();
			System.out.print(". Tidigare satt betyg: " );
			System.out.println(selectedStudent.getAssignmentGrade().get(a));
		}
		
	}
	
	public void printChoosenAssignment(Assignment selectedAssignment) {
		System.out.println("Vald uppgift: " + selectedAssignment.getAssignmentName());
		System.out.println();
		
	}
	
	public void printSetGrade(Assignment selectedAssignment, Student selectedStudent, Course selectedCourse) {
		System.out.println("Studenten " + selectedStudent.getStudentName() + " har fått betyget \"" 
				+ selectedStudent.getAssignmentGrade().get(selectedAssignment) + "\" på uppgiften " + 
				selectedAssignment.getAssignmentName() + " i kursen " + selectedCourse.getCourseName() + ".");
		
	}

	//ERRORS
	public void assignErr() {
		System.out.println("Uppgiften hittades inte.");
		
	}

	public void studErr() {
		System.out.println("Studenten hittades inte.");
		
	}

	public void coursErr() {
		System.out.println("Kursen hittades inte.");
		
	}

	public void foundButErr() {
		System.out.println("Uppgiften hittades, men tillhör inte kursens katalog.");
		
	}

	//Grade related output
	public void gradeCheckNone() {
		System.out.println("Hela uppgiftskatalogen är ännu inte fullständig.");
		
	}

	public void gradeCheckU(Assignment a) {
		System.out.println("Studenten har en underkänd uppgift i katalogen, och "
				+ "är därför inte klar med kursen. \nUnderkänd uppgift: " +
				a.getAssignmentName());
		
	}

	public void gradeCheckErr(Student selectedStudent, Assignment a, Assignment selectedAssignment) {
		System.out.print("Betyget: " + selectedStudent.getAssignmentGrade().get(a) + " för uppgift " + 
				a.getAssignmentName() + " är i felaktigt format och bör ändras. Giltiga format är (");
		for (int x = 0; x < selectedAssignment.getGradeList().getListOfGrades().size(); x++) {
			System.out.print(selectedAssignment.getGradeList().getListOfGrades().get(x));
			if (x < selectedAssignment.getGradeList().getListOfGrades().size()-1) {
				System.out.print(", ");
			}
		}
		System.out.println(").");
	}

	public void courseGradeVG() {
		System.out.println("Studenten har fått betyget VG i kursen.");
		
	}

	public void courseGradeG() {
		System.out.println("Studenten har fått betyget G i kursen.");
		
	}
	
}
