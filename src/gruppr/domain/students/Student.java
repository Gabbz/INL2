package gruppr.domain.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import gruppr.domain.assignment.Assignment;
import gruppr.technical_services.externalSystems.StudentDTO;

public class Student extends Observable {
	private String studentName;
	private String liuID;
	private ArrayList<Assignment> courseAssignments = new ArrayList<>();
	private Map<Assignment, String> assignmentGrade = new HashMap<Assignment, String>();
	
	public Student(String liuID, String studentName) { //HÄR MATAR MAN IN VILKEN STUDENT MAN VILL SKAPA EGENTLIGEN.
		this.studentName = studentName;
		this.liuID = liuID;
	}
	
	public Student(StudentDTO dto) {
		this(dto.getLiuID(), dto.getStudentName());
	}

	public Assignment selectAssignment(String assignment) {
		for (Assignment a : this.courseAssignments) {
			if(a.getAssignmentName() != null && a.getAssignmentName().equals(assignment)) {
			return a;
			} 
		}
		return null;
	}
	
	public void describeStudent() {
		System.out.println("Studentens namn: " + this.studentName + ". Liu ID: " + this.liuID);
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
		for (String s : assignemnt.getGradeList().getListOfGrades()) {
			if (grade.equals(s)) {
				this.assignmentGrade.put(assignemnt, grade);
				break;
			}
			else this.assignmentGrade.put(assignemnt, "ERROR");
		}
		
		setChanged();
		notifyObservers("Observer har noterat att studenten: " + getStudentName() + " har fått betyget: " + this.assignmentGrade.get(assignemnt) +
				" på uppgift: " + assignemnt.getAssignmentName());
		
	}
}
