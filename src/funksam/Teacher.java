package funksam;

public class Teacher {

	public static void main(String[] args) {
		
		CourseCatalog allCourses = new CourseCatalog();
		
		Course C725G31 = new Course("Java", "725G31");			//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80");
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
		
		
		Course selectedCourse = allCourses.selectCourse("725G80"); //INGEN FELHANTERING
		selectedCourse.describeCourse();
		
		allCourses.getCourses();
		
	
	}
}
