package gruppr.domain.courses;

import java.util.ArrayList;

public class CourseCatalog {

	private ArrayList<Course> courseList = new ArrayList<>();
	
	public Course selectCourse(String course) {
		for (Course c : this.courseList) {
			if(c.getCourseCode() != null && c.getCourseCode().equals(course)) {
			return c;
			} 
		}
		return null;
	}
	
	public void getCourses() {
		System.out.println("Tillgängliga kurser:");
		for(Course c : this.courseList) {
			c.describeCourse();
		}
	}
	
	public void addCourse(Course course) {
		courseList.add(course);
	}
}
