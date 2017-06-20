package gruppr.domain.start;

import gruppr.domain.assignment.Assignment;
import gruppr.domain.courses.Course;
import gruppr.domain.courses.CourseCatalog;
import gruppr.technical_services.externalSystems.PersistentStorage;
import gruppr.ui.console.Console;
import gruppr.domain.grades.GradeList;
import gruppr.domain.students.Student;
import gruppr.domain.students.StudentCatalog;

public class Teacher {
	
	private CourseCatalog allCourses = new CourseCatalog();
	private PersistentStorage ps = new PersistentStorage();
	
	public void init(Console console) {
		int exit = 1;
		while (true) {
			setGrade(console);
			exit = console.keepAlive(exit);
			if (exit == 0) break;
		}
		console.closeScanner();
		
	}
	
	public void setGrade(Console console) {
		
		//KURS
		allCourses.getCourses();
		
		String course = console.chooseCourse();
		Course selectedCourse = allCourses.selectCourse(course);									//VÄLJER KURS
		if (selectedCourse != null) {
			console.printChoosenCourse(selectedCourse);
			
																									//STUDENT
			String student = console.chooseStudent();
			Student selectedStudent = selectedCourse.getCourseStudents().selectStudent(student);	//VÄLJER STUDENT
			if (selectedStudent != null) {
				console.printChoosenStudent(selectedStudent, selectedCourse);
				
																									//UPPGIFT
				String assignment = console.chooseAssignment();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);		//VÄLJER UPPGIFT
				if (selectedAssignment != null) {
					console.printChoosenAssignment(selectedAssignment);
					for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
						if (selectedAssignment == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
							
																									//BETYG
							String grade = console.chooseGrade(selectedAssignment);
							selectedStudent.setAssignmentGrade(selectedAssignment, grade);			//SÄTTER BETYG
							
							checkCourseCompletion(selectedCourse, selectedStudent, selectedAssignment, console);
							break;
						
						} else if (i == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size()-1) {
							console.foundButErr();
						}
					}
				} else console.assignErr(); 
			} else console.studErr();
		} else console.coursErr();
	}
	
	public void checkCourseCompletion(Course selectedCourse, Student selectedStudent, Assignment selectedAssignment, Console console) {
		int gradePoints = 0;
		loop: for (Assignment a : selectedStudent.getCourseAssignments()) {
			for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
				if(a == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
					switch (selectedStudent.getAssignmentGrade().get(a)) {	
						case "NONE": console.gradeCheckNone();
						gradePoints = 0;
							break loop;
						case "U": console.gradeCheckU(a);
						gradePoints = 0;
							break loop;
						case "G": gradePoints += 1;
							break;
						case "3": gradePoints += 1;
							break;
						case "4": gradePoints += 1.5;
							break;
						case "5": gradePoints += 2;
							break;
						default: 
							console.gradeCheckErr(selectedStudent, a, selectedAssignment);
					}
				}
			} 
		}
		if (gradePoints >= (selectedCourse.getMaxPoints()*0.82)) console.courseGradeVG();
		else if ((gradePoints >= (selectedCourse.getMaxPoints()*0.4)) && (gradePoints < (selectedCourse.getMaxPoints()*0.82))) 
		console.courseGradeG();
	}
	
	//ALLT UNDER DEN HÄR LINJEN ÄR ENBART FÖR ATT VISA ATT KODEN FUNGERAR MED SIMULERAD TESTDATA.
	//-------------------------------------------------------------------------------------------
	
	public void create() throws Exception {
		
		Course C725G31 = new Course("Java", "725G31", 3.0);					//DUMMIES FÖR INL4, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80", 4.0);
		
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
		
		GradeList uToG = new GradeList("UtoG");
		GradeList uTo5 = new GradeList("Uto5");
		
		Assignment INL1 = new Assignment("INL1", createAssignmentDescription(1), uTo5);
		Assignment INL2 = new Assignment("INL2", createAssignmentDescription(2), uToG);
		Assignment INL3 = new Assignment("INL3", createAssignmentDescription(3), uTo5);
		Assignment INL4 = new Assignment("INL4", createAssignmentDescription(4), uTo5);
		
		C725G31.getCourseAssignmentCatalog().addAssignment(INL1);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL2);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL3);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL4);
		
		//Borttagna pg bd-stöd
//		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
//		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		
		StudentCatalog studentCat1 =  new StudentCatalog(ps.getStudents(C725G31.getCourseCode()).get(C725G31.getCourseCode()));
		C725G31.setCourseStudents(studentCat1);
		studentCat1 =  new StudentCatalog(ps.getStudents(C725G80.getCourseCode()).get(C725G80.getCourseCode()));
		C725G80.setCourseStudents(studentCat1);
		
		//Borttagna pga db-stöd
//		C725G31.getCourseStudents().addStudent(jonbo488);
//		C725G31.getCourseStudents().addStudent(roykr837);
//		C725G80.getCourseStudents().addStudent(jonbo488);
		
		C725G31.flush();
		C725G80.flush();

		
		
	}
	
	public String createAssignmentDescription(int moment) {
		String moment1, moment2, moment3, moment4;
		String returnmoment = null;
		
		moment1 = "Moment 1" + "\nUppgift avseende gruppsykologi i projekt[3 HP] \n\nBeskrivning av projektuppgiften:" +
				"\n1. Inledningsvis skall de olika projektgrupperna välja minst tre områden att fördjupa sig i. "
				+ "\nDessa val av områden utförs vid kursstart, måndag den 5 augusti och skall presenteras på seminarium den 14 augusti."
				+ "\nTeorier avseende de olika fördjupningsområdena finns i kursboken Projektledning av Bo Tonnquist. "
				+ "\n\n2. Utför därefter en individuell fördjupning över vad agilt ledarskap kan innebära,"
				+ "\noch skriv ett referat från minst två böcker samt reflektera över hur man kan arbeta agilt i det projektarbete som ni genomför. "
				+ "\n\n3. Referatet och reflektionen dokumenteras på ca fem A4 sidor och skickas till Grupp R, "
				+ "\nvia e-post senast måndag den 23 augusti kl. 17.00. Detta arbete genomförs individuellt av varje student.";
		
		moment2 ="\n\n\nMoment 2" + "\nUppgift avseende initial kartläggning [2HP] \n\nBeskrivning av projektuppgiften:" +
				"\nUppgiften består i att utifrån en angelägen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
				"\nresonera kring affärssystem som fenomen och dess möte med organisationer. " +
				"\nInspiration till och stöd för resonemang kan också sökas utanför kurslitteraturen, i t.ex. resurslista i Funksam" + 
				"\nunder Kursdokument eller litteratur som söks på egen hand." +
				"\n\n1. Var noga med att använda källor, såväl tryckta som elektroniska anges tydligt med sedvanlig referenshantering "
				+ "\noch listas (utöver de angivna sidorna).Minst fem källor av akademisk karaktär." +
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nInlämning sker senast tisdag 16e september via Funksam.";
		
		moment3 = "\n\n\nMoment 3" + "\nUppgift utredningsprojekt[6 HP] \n\nBeskrivning av projektuppgiften:" +
				"\nSyftet med projektarbetet är att skaffa sig kunskap om olika perspektiv på affärssystem och organisering. "
				+ "Arbetet skall i första hand bedrivas i grupper om fyra studenter. "
				+ "Projektuppgiften kan övergripande beskrivas som möjlig att till stor del anpassa till projektgruppens intresse "
				+ "med avseende på sitt innehåll inom kursens ramar."
				+ "Uppgiften består i att utifrån en angelägen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
				"\n\n1. Var noga med att använda källor, såväl tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (utöver de angivna sidorna).Minst tio källor av akademisk karaktär." +
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nProjektarbetet lämnas in vid tre tillfällen under kursens gång enligt schema. "
				+ "\nTvå gånger inför handledning samt en slutinlämning. Samtliga inlämningar sker via FUNKSAM.";
		
		moment4 = "\n\n\n\nMoment 4" + "\nUppgift avseende Design och värdering av en e-tjänst [4HP] \n\nBeskrivning av projektuppgiften:"
				+ "\nUppgiften innebär att design och utvärdering skall ske kriteriebaserat utifrån av gruppen formulerade kriterier. "
				+ "\nE-tjänsten skall visualiseras på det sätt gruppen väljer; t.ex. genom skisser över tjänsten, gränssnitt, grafisk prototyp eller liknande. "
				+ "\n\n1.Var noga med att använda källor, såväl tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (utöver de angivna sidorna).Minst tio källor av akademisk karaktär." 
				+"\n\n2. Projektarbetet lämnas in vid tre tillfÃ¤llen under kursens gång enligt schema. \nTvå gånger inför handledning samt en slutinlÃ¤mning. "
				+ "Samtliga inlÃ¤mningar sker senast fredagen den 15 september kl. 08.00.";
		
		switch (moment) {	
			case 1: returnmoment =  moment1;
				break;
			case 2: returnmoment =  moment2;
				break;
			case 3: returnmoment =  moment3;
				break;
			case 4: returnmoment = moment4;
				break;
		}
		return returnmoment;
	}

}
