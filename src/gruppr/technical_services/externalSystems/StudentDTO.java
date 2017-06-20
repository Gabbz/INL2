package gruppr.technical_services.externalSystems;

public class StudentDTO {
	
	private String studentName;
	private String liuID;
	
	public StudentDTO(String studentName, String liuID) {
		super();
		this.studentName = studentName;
		this.liuID = liuID;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getLiuID() {
		return liuID;
	}
	public void setLiuID(String liuID) {
		this.liuID = liuID;
	}
	
	

}
