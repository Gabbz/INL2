package funksam;

import java.util.Scanner;

public class Teacher {

	private static CourseCatalog allCourses = new CourseCatalog();
	
	public static void main(String[] args) {
		
		create();
		init();
		
		//DEBUGGING
//		Course selectedCourse = allCourses.selectCourse("725G31");
//		if( selectedCourse != null) {
//			selectedCourse.describeCourse();
//			selectedCourse.courseStudents.getStudents();
//			selectedCourse.courseAssignments.getAssignments();
//		}
//		System.out.println(selectedCourse.courseStudents.selectStudent("jonbo488").selectAssignment("INL1").assignmentGrade);
		//BETYGS�TT DEN ALLM�NNA UPPGIFTEN - FEL!
//		selectedCourse.courseAssignments.selectAssignment("INL1").gradeAssignment("G");
//		System.out.println(selectedCourse.courseAssignments.selectAssignment("INL1").assignmentGrade);
//		selectedCourse.courseStudents.selectStudent("jonbo488").courseAssignments.get()
//		allCourses.getCourses();
		
		
	
	}
	
	public static void init() {
		int exit = 1;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			setGrade(scanner);
			System.out.println("S�tta fler betyg? (1 = Ja, 0 = Nej)");
			exit = scanner.nextInt();
			scanner.nextLine();	//CLEARAR SCANNERN
			if (exit == 0) break;
		}
		scanner.close();
		
	}
	
	public static void setGrade(Scanner scanner) {
		
		//KURS
		System.out.print("V�lj en kurs (OBS, skiftl�gesk�nsligt): ");
		String course = scanner.nextLine();
		Course selectedCourse = allCourses.selectCourse(course);
		if (selectedCourse != null) {
			System.out.print("Vald Kurs: ");
			selectedCourse.describeCourse();
			
			//STUDENT
			System.out.print("V�lj en student (anv�nd LiUID): ");
			String student = scanner.nextLine();
			Student selectedStudent = selectedCourse.getCourseStudentCatalog().selectStudent(student);
			if (selectedStudent != null) {
				System.out.print("Vald student: ");
				selectedStudent.describeStudent();
				
				//UPPGIFT
				System.out.print("V�lj en uppgift: ");
				String assignment = scanner.nextLine();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);
				if (selectedAssignment != null) {
					System.out.print("Vald uppgift: ");
					selectedAssignment.describeAssignment();
					
					//BETYG
					System.out.print("S�tt ett betyg (U,G,VG): ");
					String grade = scanner.nextLine().toUpperCase();
					selectedStudent.setAssignmentGrade(selectedAssignment, grade);
					System.out.println("Studenten " + selectedStudent.getStudentName() + " har f�tt betyget \"" 
					+ selectedStudent.getAssignmentGrade().get(selectedAssignment) + "\" p� uppgiften " + selectedAssignment.getAssignmentName() 
					+ " i kursen " + selectedCourse.getCourseName() + ".");
					
					int gradePoints = selectedStudent.checkCourseCompletion();
					if (gradePoints >= (selectedCourse.getMaxPoints()*0.82)) System.out.println("Studenten har f�tt betyget VG i kursen.");
					if ((gradePoints >= (selectedCourse.getMaxPoints()*0.4)) && (gradePoints < (selectedCourse.getMaxPoints()*0.82))) 
					System.out.println("Studenten har f�tt betyget G i kursen.");
					
				} else System.out.println("Uppgiften hittades inte.");
			} else System.out.println("Studenten hittades inte.");
		} else System.out.println("Kursen hittades inte.");
	}
	
	//ALLT UNDER DEN H�R LINJEN �R ENBART F�R ATT VISA ATT KODEN FUNGERAR MED SIMULERAD TESTDATA.
	//-------------------------------------------------------------------------------------------
	
	public static void create() {
		
		Course C725G31 = new Course("Java", "725G31", 8.0);					//DUMMIES F�R INL2, G�RS EGENTLIGEN I EGET ANV�NDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Aff�rssystem", "725G80", 2.0);
		
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
		
		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		
		Assignment INL1 = new Assignment("INL1", createAssignmentDescription(1));
		Assignment INL2 = new Assignment("INL2", createAssignmentDescription(2));
		Assignment INL3 = new Assignment("INL3", createAssignmentDescription(3));
		Assignment INL4 = new Assignment("INL4", createAssignmentDescription(4));
		
		C725G31.getCourseStudentCatalog().addStudent(jonbo488);
		C725G31.getCourseStudentCatalog().addStudent(roykr837);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL1, C725G31);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL2, C725G31);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL3, C725G31);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL4, C725G31);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL1, C725G80);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL2, C725G80);
		
		
	}
	
	public static String createAssignmentDescription(int moment) {
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
