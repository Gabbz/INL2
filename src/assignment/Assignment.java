package assignment;

import grades.GradeList;

public class Assignment {
	
	private String assignmentName;
	private AssignmentDescription assignmentDescription;
	private GradeList listOfGrades;
	
	public Assignment(String assignmentName, String description, GradeList listOfGrades) { 
		
		this.listOfGrades = listOfGrades;
		this.assignmentName = assignmentName;
		this.assignmentDescription = new AssignmentDescription(assignmentName, description);
	}
	
	public void describeAssignment() {
		System.out.print("Namn på uppgiften: " + this.assignmentName);
	}
		
	public String getAssignmentName() {
		return this.assignmentName;
	}
	
	public GradeList getGradeList() {
		return this.listOfGrades;
	}
	
	public AssignmentDescription getAssignmentDescription() {
		return assignmentDescription;
		
	}
}
