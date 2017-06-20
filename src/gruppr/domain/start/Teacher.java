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
		Course selectedCourse = allCourses.selectCourse(course);									//V�LJER KURS
		if (selectedCourse != null) {
			console.printChoosenCourse(selectedCourse);
			
																									//STUDENT
			String student = console.chooseStudent();
			Student selectedStudent = selectedCourse.getCourseStudents().selectStudent(student);	//V�LJER STUDENT
			if (selectedStudent != null) {
				console.printChoosenStudent(selectedStudent, selectedCourse);
				
																									//UPPGIFT
				String assignment = console.chooseAssignment();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);		//V�LJER UPPGIFT
				if (selectedAssignment != null) {
					console.printChoosenAssignment(selectedAssignment);
					for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
						if (selectedAssignment == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
							
																									//BETYG
							String grade = console.chooseGrade(selectedAssignment);
							selectedStudent.setAssignmentGrade(selectedAssignment, grade);			//S�TTER BETYG
							
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
	
	//ALLT UNDER DEN H�R LINJEN �R ENBART F�R ATT VISA ATT KODEN FUNGERAR MED SIMULERAD TESTDATA.
	//-------------------------------------------------------------------------------------------
	
	public void create() throws Exception {
		
		Course C725G31 = new Course("Java", "725G31", 3.0);					//DUMMIES F�R INL4, G�RS EGENTLIGEN I EGET ANV�NDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Aff�rssystem", "725G80", 4.0);
		
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
		
		//Borttagna pg bd-st�d
//		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
//		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		
		StudentCatalog studentCat1 =  new StudentCatalog(ps.getStudents(C725G31.getCourseCode()).get(C725G31.getCourseCode()));
		C725G31.setCourseStudents(studentCat1);
		studentCat1 =  new StudentCatalog(ps.getStudents(C725G80.getCourseCode()).get(C725G80.getCourseCode()));
		C725G80.setCourseStudents(studentCat1);
		
		//Borttagna pga db-st�d
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
				"\n1. Inledningsvis skall de olika projektgrupperna v�lja minst tre omr�den att f�rdjupa sig i. "
				+ "\nDessa val av omr�den utf�rs vid kursstart, m�ndag den 5 augusti och skall presenteras p� seminarium den 14 augusti."
				+ "\nTeorier avseende de olika f�rdjupningsomr�dena finns i kursboken Projektledning av Bo Tonnquist. "
				+ "\n\n2. Utf�r d�refter en individuell f�rdjupning �ver vad agilt ledarskap kan inneb�ra,"
				+ "\noch skriv ett referat fr�n minst tv� b�cker samt reflektera �ver hur man kan arbeta agilt i det projektarbete som ni genomf�r. "
				+ "\n\n3. Referatet och reflektionen dokumenteras p� ca fem A4 sidor och skickas till Grupp R, "
				+ "\nvia e-post senast m�ndag den 23 augusti kl. 17.00. Detta arbete genomf�rs individuellt av varje student.";
		
		moment2 ="\n\n\nMoment 2" + "\nUppgift avseende initial kartl�ggning [2HP] \n\nBeskrivning av projektuppgiften:" +
				"\nUppgiften best�r i att utifr�n en angel�gen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
				"\nresonera kring aff�rssystem som fenomen och dess m�te med organisationer. " +
				"\nInspiration till och st�d f�r resonemang kan ocks� s�kas utanf�r kurslitteraturen, i t.ex. resurslista i Funksam" + 
				"\nunder Kursdokument eller litteratur som s�ks p� egen hand." +
				"\n\n1. Var noga med att anv�nda k�llor, s�v�l tryckta som elektroniska anges tydligt med sedvanlig referenshantering "
				+ "\noch listas (ut�ver de angivna sidorna).Minst fem k�llor av akademisk karakt�r." +
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nInl�mning sker senast tisdag 16e september via Funksam.";
		
		moment3 = "\n\n\nMoment 3" + "\nUppgift utredningsprojekt[6 HP] \n\nBeskrivning av projektuppgiften:" +
				"\nSyftet med projektarbetet �r att skaffa sig kunskap om olika perspektiv p� aff�rssystem och organisering. "
				+ "Arbetet skall i f�rsta hand bedrivas i grupper om fyra studenter. "
				+ "Projektuppgiften kan �vergripande beskrivas som m�jlig att till stor del anpassa till projektgruppens intresse "
				+ "med avseende p� sitt inneh�ll inom kursens ramar."
				+ "Uppgiften best�r i att utifr�n en angel�gen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
				"\n\n1. Var noga med att anv�nda k�llor, s�v�l tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (ut�ver de angivna sidorna).Minst tio k�llor av akademisk karakt�r." +
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nProjektarbetet l�mnas in vid tre tillf�llen under kursens g�ng enligt schema. "
				+ "\nTv� g�nger inf�r handledning samt en slutinl�mning. Samtliga inl�mningar sker via FUNKSAM.";
		
		moment4 = "\n\n\n\nMoment 4" + "\nUppgift avseende Design och v�rdering av en e-tj�nst [4HP] \n\nBeskrivning av projektuppgiften:"
				+ "\nUppgiften inneb�r att design och utv�rdering skall ske kriteriebaserat utifr�n av gruppen formulerade kriterier. "
				+ "\nE-tj�nsten skall visualiseras p� det s�tt gruppen v�ljer; t.ex. genom skisser �ver tj�nsten, gr�nssnitt, grafisk prototyp eller liknande. "
				+ "\n\n1.Var noga med att anv�nda k�llor, s�v�l tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (ut�ver de angivna sidorna).Minst tio k�llor av akademisk karakt�r." 
				+"\n\n2. Projektarbetet l�mnas in vid tre tillfällen under kursens g�ng enligt schema. \nTv� g�nger inf�r handledning samt en slutinlämning. "
				+ "Samtliga inlämningar sker senast fredagen den 15 september kl. 08.00.";
		
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
