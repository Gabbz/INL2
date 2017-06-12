package grades;

import java.util.ArrayList;

public class Uto5 implements GradeListInterface {

	private ArrayList<String> listOfGrades = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	public ArrayList getListOfGrades() {
		fillListOfGrades();
		 return this.listOfGrades;
	}

	public void fillListOfGrades() {
		this.listOfGrades.add("U");
		this.listOfGrades.add("3");
		this.listOfGrades.add("4");
		this.listOfGrades.add("5");
		
	}

}
