package grades;

public class GradeListFactory {
	
	private static GradeListFactory instance;
	
	@SuppressWarnings("unused")
	private GradeListInterface gradeInterface;
	
	public static synchronized GradeListFactory getInstance() {
		instance = new GradeListFactory();
		return instance;
	}
	
	public GradeListInterface getGradeListInterface(String grades) {
		if (grades == "UtoG") return gradeInterface = new UtoG();
		else if (grades == "Uto5") return gradeInterface = new Uto5();
		else return null;
		
	}
}
