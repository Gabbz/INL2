package funksam;

import java.util.ArrayList;

public class Student {
	String studentName;
	String liuID;
	String courseGrade;
	ArrayList<Assignment> courseAssignments = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.courseAssignments) {
			if(a.assignmentName != null && a.assignmentName.equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	
	public Student(String liuID, String studentName) { //H�R MATAR MAN IN VILKEN STUDENT MAN VILL SKAPA EGENTLIGEN.
		this.studentName = studentName;
		this.liuID = liuID;
	}
	
	public void describeStudent() {
		System.out.println("Student name: " + this.studentName + ". Liu ID: " + this.liuID);
	}
	
	public int checkCourseCompletion() {
		int coursePoints = 0;
		for(int i=0; i < courseAssignments.size(); i++) {
			if (courseAssignments.get(i).assignmentGrade == null) {
				System.out.println("Hela uppgiftskatalogen �r �nnu inte fullst�ndig.");
				coursePoints = 0;
				break;
			} else if (courseAssignments.get(i).assignmentGrade == "U") {
				System.out.println("Studenten har en underk�nd uppgift i katalogen, och "
						+ "�r d�rf�r inte klar med kursen. \nUnderk�nd uppgift: " +
						courseAssignments.get(i).assignmentName);
				break;
			} else if (courseAssignments.get(i).assignmentGrade == "G") {
				coursePoints += 1;
			} else coursePoints += 2;
		}
		return coursePoints;
	}
}
