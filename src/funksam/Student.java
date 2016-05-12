package funksam;

public class Student {
	String studentName;
	String liuID;
	
//	public String[] getInformation() {
//		String[] student = new String[]{this.name, this.liuID};
//		
//		return student;
//	}
	
	public Student(String studentName, String liuID) { //HÄR MATAR MAN IN VILKEN KURS MAN VILL SKAPA EGENTLIGEN.
		this.studentName = studentName;
		this.liuID = liuID;
	}
	
	public void describeStudent() {
		System.out.println(". Student name: " + this.studentName + "Liu ID: " + this.liuID);
	}
}
