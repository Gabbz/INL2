package funksam;


public class Grade {

	//ARRAY OCH STORLEK SÄTT STORLEKEN PÅ ARRAYEN
	private static String[] grades = {"U","G","VG"};

	
	public static String getGrade(String grade) {
		String returnGrade = "U";
		switch (grade) {	
			case "U": returnGrade = grades[0];
				break;
			case "G": returnGrade = grades[1];
				break;
			case "VG": returnGrade = grades[2];
				break;
		}
		return returnGrade;
	}
}

