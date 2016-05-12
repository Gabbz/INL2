package funksam;

public class Student {
	String name;
	String liuID;
	
	public String[] getInformation() {
		String[] student = new String[]{this.name, this.liuID};
		
		return student;
	}
}
