package assignment;

import java.util.ArrayList;

public class AssignmentCatalog {
	
	private int size = 0;
	private ArrayList<Assignment> assignmentList = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.assignmentList) {
			if(a.getAssignmentName() != null && a.getAssignmentName().equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	public void addAssignment(Assignment assignment) {
		assignmentList.add(assignment);
		this.size++;
	}
	
	public void getAssignments() {
		for(int i = 0; i < assignmentList.size(); i++) {
			assignmentList.get(i).describeAssignment();
		}
	}
	
	public ArrayList<Assignment> getAssignmentList() {
		return this.assignmentList;
	}
	
	public int getSize() {
		return this.size;
	}
	
}
