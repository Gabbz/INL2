package funksam;

public class Assignment {
	String assignmentName;
	String assignmentGrade;
	
	public Assignment(String assignmentName) { //H�R MATAR MAN IN VILKEN UPPGIFT MAN VILL SKAPA EGENTLIGEN.
		this.assignmentName = assignmentName;
	}
	
	public void describeAssignment() {
		System.out.println("Assignment name: " + this.assignmentName);
	}
	
	public void gradeAssignment(String grade) {
		assignmentGrade = Grade.getGrade(grade);
	}
}
