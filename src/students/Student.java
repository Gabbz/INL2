package funksam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
	private String studentName;
	private String liuID;
	private ArrayList<Assignment> courseAssignments = new ArrayList<>();
	private Map<Assignment, String> assignmentGrade = new HashMap<Assignment, String>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.courseAssignments) {
			if(a.getAssignmentName() != null && a.getAssignmentName().equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	
	public Student(String liuID, String studentName) { //HÄR MATAR MAN IN VILKEN STUDENT MAN VILL SKAPA EGENTLIGEN.
		this.studentName = studentName;
		this.liuID = liuID;
	}
	
	public void describeStudent() {
		System.out.println("Student name: " + this.studentName + ". Liu ID: " + this.liuID);
	}
	
	public int checkCourseCompletion() {
		int coursePoints = 0;
		for(int i=0; i < assignmentGrade.size(); i++) {
			if (assignmentGrade.get(courseAssignments.get(i)) == null) {
				System.out.println("Hela uppgiftskatalogen är ännu inte fullständig.");
				coursePoints = 0;
				break;
			} else if (assignmentGrade.get(courseAssignments.get(i)) == "U") {
				System.out.println("Studenten har en underkänd uppgift i katalogen, och "
						+ "är därför inte klar med kursen. \nUnderkänd uppgift: " +
						courseAssignments.get(i).getAssignmentName());
				break;
			} else if (assignmentGrade.get(courseAssignments.get(i)) == "G") {
				coursePoints += 1;
			} else coursePoints += 2;
		}
		return coursePoints;
	}


	public String getStudentName() {
		return this.studentName;
	}
	
	public String getLiuID() {
		return this.liuID;
	}


	public ArrayList<Assignment> getCourseAssignments() {
		return this.courseAssignments;
	}
	
	public Map<Assignment, String> getAssignmentGrade() {
		return this.assignmentGrade;
	}
	
	public void setAssignmentGrade(Assignment assignemnt, String grade) {
		this.assignmentGrade.put(assignemnt, grade);
		
	}
}
