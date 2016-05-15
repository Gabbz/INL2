package funksam;

import java.util.ArrayList;

public class AssignmentCatalog {
	
	private ArrayList<Assignment> assignmentList = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.assignmentList) {
			if(a.getAssignmentName() != null && a.getAssignmentName().equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	public void addAssignment(Assignment assignment, Course course) {
		assignmentList.add(assignment);
		for (Student s : course.getCourseStudentCatalog().getStudentList()) {
			s.getCourseAssignments().add(assignment);
			s.getAssignmentGrade().put(assignment, null);
			
		}
	}
	
	public void getAssignments() {
		for(int i = 0; i < assignmentList.size(); i++) {
			assignmentList.get(i).describeAssignment();
			System.out.println();
		}
	}
	
}
