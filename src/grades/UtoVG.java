package grades;

import java.util.ArrayList;

public class UtoVG implements GradeListInterface {

	private ArrayList<String> listOfGrades = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	public ArrayList getListOfGrades() {
		fillListOfGrades();
		 return this.listOfGrades;
	}

	public void fillListOfGrades() {
		this.listOfGrades.add("U");
		this.listOfGrades.add("G");
		this.listOfGrades.add("VG");
		
	}

}
