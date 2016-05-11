package funksam;

import java.util.ArrayList;


public class Grade {
	
	public void getGrade(String student) {
	
	//Skapa array
	String[] grades;
	
	//Sätter storlek på Arrayen
	grades = new String[3];
	
	//Initiera elementen
	grades[0] = "U";
	grades[1] = "G";
	grades[2] = "VG";

	}
	
	private String getGrade(int grade) {
		return this.grades[grade];
		
	}
}
