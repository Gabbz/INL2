package grades;

import java.util.ArrayList;

public class GradeList {
	
	private ArrayList<String> listOfGrades;
	private GradeListInterface gradeInterface;
	
	@SuppressWarnings("unchecked")
	public GradeList(String grades) {
		
		gradeInterface = GradeListFactory.getInstance().getGradeListInterface(grades);
		listOfGrades = gradeInterface.getListOfGrades();
	}
	
	public ArrayList<String> getListOfGrades() {
		return listOfGrades;
	}

}
