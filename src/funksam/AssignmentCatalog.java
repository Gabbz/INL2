package funksam;

import java.util.ArrayList;

public class AssignmentCatalog {
	
	private ArrayList<Assignment> assignmentList = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.assignmentList) {
			if(a.liuID != null && a.liuID.equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	public void addAssignment(Assignment assignment) {
		assignmentList.add(assignment);
	}
}
