package org.gruppr.ui.start;

import java.util.InputMismatchException;
import java.util.Scanner;

import assignment.Assignment;
import courses.Course;
import courses.CourseCatalog;
import externalSystems.PersistentStorage;
import grades.GradeList;
import students.Student;


public class Teacher {
	
	private static CourseCatalog allCourses = new CourseCatalog();
	
	public static void main(String[] args) throws Exception {
		
		create();
		init();
		
	}
	
	public static void init() {
		int exit = 1;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			setGrade(scanner);
			System.out.println();
			System.out.println("S�tta fler betyg? (1 = Ja, 0 = Nej)");
			try {
				exit = scanner.nextInt();
			} catch (InputMismatchException ex){
				System.out.println();
				System.out.println("Enter a valid number");
				System.out.println();
			}
			scanner.nextLine();	//CLEARAR SCANNERN				
			if (exit == 0) break;
		}
		scanner.close();
		
	}
	
	public static void setGrade(Scanner scanner) {
		
		//KURS
		allCourses.getCourses();
		
		System.out.println();
		System.out.print("V�lj en kurs (OBS, skiftl�gesk�nsligt): ");
		String course = scanner.nextLine();
		Course selectedCourse = allCourses.selectCourse(course);		//V�LJER KURS
		if (selectedCourse != null) {
			System.out.print("Vald Kurs: ");
			selectedCourse.describeCourse();
			System.out.println();
			
			//STUDENT
			System.out.println("Studenter som tillh�r kursen:");
			for (int i = 0; i < selectedCourse.getCourseStudents().getSize(); i++) {
				selectedCourse.getCourseStudents().getStudentList().get(i).describeStudent();
			}
			
			System.out.println();
			System.out.print("V�lj en student (anv�nd LiUID): ");
			String student = scanner.nextLine();
			
			Student selectedStudent = selectedCourse.getCourseStudents().selectStudent(student);		//V�LJER STUDENT
			if (selectedStudent != null) {
				selectedStudent.addObserver(selectedCourse);		//Adding observer
				System.out.print("Vald student: ");
				selectedStudent.describeStudent();
				System.out.println();
				
				//UPPGIFT
				System.out.println("Uppgifter som tillh�r kursen och som studenten har:");
				for (Assignment a : selectedCourse.getCourseAssignmentCatalog().getAssignmentList()) {
					a.describeAssignment();
					System.out.print(". Tidigare satt betyg: " );
					System.out.println(selectedStudent.getAssignmentGrade().get(a));
				}
				System.out.println();
				System.out.print("V�lj en uppgift: ");
				String assignment = scanner.nextLine();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);		//V�LJER UPPGIFT
				if (selectedAssignment != null) {
					for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
						if (selectedAssignment == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
							System.out.println("Vald uppgift: " + selectedAssignment.getAssignmentName());
							System.out.println();
							
							//BETYG
							System.out.print("S�tt ett betyg (");
							for (int x = 0; x < selectedAssignment.getGradeList().getListOfGrades().size(); x++) {
								System.out.print(selectedAssignment.getGradeList().getListOfGrades().get(x));
								if (x < selectedAssignment.getGradeList().getListOfGrades().size()-1) {
									System.out.print(", ");
								}
							}
							System.out.println("): ");
							String grade = scanner.nextLine().toUpperCase();
							selectedStudent.setAssignmentGrade(selectedAssignment, grade);		//S�TTER BETYG
							System.out.println("Studenten " + selectedStudent.getStudentName() + " har f�tt betyget \"" 
							+ selectedStudent.getAssignmentGrade().get(selectedAssignment) + "\" p� uppgiften " + selectedAssignment.getAssignmentName() 
							+ " i kursen " + selectedCourse.getCourseName() + ".");
							checkCourseCompletion(selectedCourse, selectedStudent, selectedAssignment);
							break;
						} else if (i == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size()-1) {
							System.out.println("Uppgiften hittades, men tillh�r inte kursens katalog.");
						}
					}
				} else System.out.println("Uppgiften hittades inte.");
			} else System.out.println("Studenten hittades inte.");
		} else System.out.println("Kursen hittades inte.");
	}
	
	public static void checkCourseCompletion(Course selectedCourse, Student selectedStudent, Assignment selectedAssignment) {
		int gradePoints = 0;
		loop: for (Assignment a : selectedStudent.getCourseAssignments()) {
			for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
				if(a == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
					switch (selectedStudent.getAssignmentGrade().get(a)) {	
						case "NONE": System.out.println("Hela uppgiftskatalogen �r �nnu inte fullst�ndig.");
						gradePoints = 0;
							break loop;
						case "U": System.out.println("Studenten har en underk�nd uppgift i katalogen, och "
								+ "�r d�rf�r inte klar med kursen. \nUnderk�nd uppgift: " +
								a.getAssignmentName());
						gradePoints = 0;
							break loop;
						case "G": gradePoints += 1;
							break;
						case "VG": gradePoints += 2;
							break;
						default: 
							System.out.print("Betyget: " + selectedStudent.getAssignmentGrade().get(a) + " f�r uppgift " + 
									a.getAssignmentName() + " �r i felaktigt format och b�r �ndras. Giltiga format �r (");
							for (int x = 0; x < selectedAssignment.getGradeList().getListOfGrades().size(); x++) {
								System.out.print(selectedAssignment.getGradeList().getListOfGrades().get(x));
								if (x < selectedAssignment.getGradeList().getListOfGrades().size()-1) {
									System.out.print(", ");
								}
							}
							System.out.println(").");
					}
				}
			} 
		}
		if (gradePoints >= (selectedCourse.getMaxPoints()*0.82)) System.out.println("Studenten har f�tt betyget VG i kursen.");
		else if ((gradePoints >= (selectedCourse.getMaxPoints()*0.4)) && (gradePoints < (selectedCourse.getMaxPoints()*0.82))) 
		System.out.println("Studenten har f�tt betyget G i kursen.");
	}
	
	//ALLT UNDER DEN H�R LINJEN �R ENBART F�R ATT VISA ATT KODEN FUNGERAR MED SIMULERAD TESTDATA.
	//-------------------------------------------------------------------------------------------
	
	public static void create() throws Exception {
		
		Course C725G31 = new Course("Java", "725G31", 3.0);					//DUMMIES F�R INL2, G�RS EGENTLIGEN I EGET ANV�NDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Aff�rssystem", "725G80", 4.0);
		
		allCourses.addCourse(C725G80);
		allCourses.addCourse(C725G31);
		
		GradeList uToG = new GradeList("UtoG");
		GradeList uToVG = new GradeList("UtoVG");
		
		Assignment INL1 = new Assignment("INL1", createAssignmentDescription(1), uToVG);
		Assignment INL2 = new Assignment("INL2", createAssignmentDescription(2), uToG);
		Assignment INL3 = new Assignment("INL3", createAssignmentDescription(3), uToVG);
		Assignment INL4 = new Assignment("INL4", createAssignmentDescription(4), uToVG);
		
		C725G31.getCourseAssignmentCatalog().addAssignment(INL1);
		C725G31.getCourseAssignmentCatalog().addAssignment(INL2);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL3);
		C725G80.getCourseAssignmentCatalog().addAssignment(INL4);
		
		//Borttagna pg bd-st�d
//		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
//		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		
		
		PersistentStorage ps = new PersistentStorage();
		
		
		C725G31.setCourseStudents(ps.getStudents(C725G31.getCourseCode()).get(C725G31.getCourseCode()));
		C725G80.setCourseStudents(ps.getStudents(C725G80.getCourseCode()).get(C725G80.getCourseCode()));
		
		//Borttagna pga db-st�d
//		C725G31.getCourseStudents().addStudent(jonbo488);
//		C725G31.getCourseStudents().addStudent(roykr837);
//		C725G80.getCourseStudents().addStudent(jonbo488);
		
		C725G31.flush();
		C725G80.flush();

		
		
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
