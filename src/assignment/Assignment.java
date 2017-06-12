package funksam;

public class Assignment {
	private String assignmentName;
	private AssignmentDescription assDesc;
	
	public Assignment(String assignmentName, String description) { 
		
		this.assignmentName = assignmentName;
		this.assDesc = new AssignmentDescription(assignmentName, description);
	}
	
	public void describeAssignment() {
		System.out.println("Assignment name: " + this.assignmentName);
	}
		
	public String getAssignmentName() {
		return this.assignmentName;
	}
	
	public AssignmentDescription getAssignmentDescription() {
		return assDesc;
		
	}
}
