package funksam;

import java.util.ArrayList;

public class Student {
	String studentName;
	String liuID;
	ArrayList<Assignment> courseAssignments = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.courseAssignments) {
			if(a.assignmentName != null && a.assignmentName.equals(assignment)) {
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
		System.out.print("Student name: " + this.studentName + ". Liu ID: " + this.liuID);
	}
}
