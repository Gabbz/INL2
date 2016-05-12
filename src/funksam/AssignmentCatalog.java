package funksam;

import java.util.ArrayList;

public class AssignmentCatalog {
	
	private ArrayList<Assignment> assignmentList = new ArrayList<>();
	
	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.assignmentList) {
			if(a.assignmentName != null && a.assignmentName.equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	public void addAssignment(Assignment assignment, Course course) {
		assignmentList.add(assignment);
		for (Student s : course.courseStudents.studentList) {
			s.courseAssignments.add(assignment);
		}
	}
	
	public void getAssignments() {
		for(int i = 0; i < assignmentList.size(); i++) {
			assignmentList.get(i).describeAssignment();
			System.out.println();
		}
	}
	
}
