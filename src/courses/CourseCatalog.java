package funksam;

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
		for(int i = 0; i < courseList.size(); i++) {
			System.out.println(courseList.get(i).getCourseName());
			System.out.println(courseList.get(i).getCourseCode());
			System.out.println();
		}
	}
	
	public void addCourse(Course course) {
		courseList.add(course);
	}
}
