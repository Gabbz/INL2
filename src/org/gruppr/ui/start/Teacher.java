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
			System.out.println("Sätta fler betyg? (1 = Ja, 0 = Nej)");
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
		System.out.print("Välj en kurs (OBS, skiftlägeskänsligt): ");
		String course = scanner.nextLine();
		Course selectedCourse = allCourses.selectCourse(course);		//VÄLJER KURS
		if (selectedCourse != null) {
			System.out.print("Vald Kurs: ");
			selectedCourse.describeCourse();
			System.out.println();
			
			//STUDENT
			System.out.println("Studenter som tillhör kursen:");
			for (int i = 0; i < selectedCourse.getCourseStudents().getSize(); i++) {
				selectedCourse.getCourseStudents().getStudentList().get(i).describeStudent();
			}
			
			System.out.println();
			System.out.print("Välj en student (använd LiUID): ");
			String student = scanner.nextLine();
			
			Student selectedStudent = selectedCourse.getCourseStudents().selectStudent(student);		//VÄLJER STUDENT
			if (selectedStudent != null) {
				selectedStudent.addObserver(selectedCourse);		//Adding observer
				System.out.print("Vald student: ");
				selectedStudent.describeStudent();
				System.out.println();
				
				//UPPGIFT
				System.out.println("Uppgifter som tillhör kursen och som studenten har:");
				for (Assignment a : selectedCourse.getCourseAssignmentCatalog().getAssignmentList()) {
					a.describeAssignment();
					System.out.print(". Tidigare satt betyg: " );
					System.out.println(selectedStudent.getAssignmentGrade().get(a));
				}
				System.out.println();
				System.out.print("Välj en uppgift: ");
				String assignment = scanner.nextLine();
				Assignment selectedAssignment = selectedStudent.selectAssignment(assignment);		//VÄLJER UPPGIFT
				if (selectedAssignment != null) {
					for (int i = 0; i < selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size(); i++) {
						if (selectedAssignment == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().get(i)) {
							System.out.println("Vald uppgift: " + selectedAssignment.getAssignmentName());
							System.out.println();
							
							//BETYG
							System.out.print("Sätt ett betyg (");
							for (int x = 0; x < selectedAssignment.getGradeList().getListOfGrades().size(); x++) {
								System.out.print(selectedAssignment.getGradeList().getListOfGrades().get(x));
								if (x < selectedAssignment.getGradeList().getListOfGrades().size()-1) {
									System.out.print(", ");
								}
							}
							System.out.println("): ");
							String grade = scanner.nextLine().toUpperCase();
							selectedStudent.setAssignmentGrade(selectedAssignment, grade);		//SÄTTER BETYG
							System.out.println("Studenten " + selectedStudent.getStudentName() + " har fått betyget \"" 
							+ selectedStudent.getAssignmentGrade().get(selectedAssignment) + "\" på uppgiften " + selectedAssignment.getAssignmentName() 
							+ " i kursen " + selectedCourse.getCourseName() + ".");
							checkCourseCompletion(selectedCourse, selectedStudent, selectedAssignment);
							break;
						} else if (i == selectedCourse.getCourseAssignmentCatalog().getAssignmentList().size()-1) {
							System.out.println("Uppgiften hittades, men tillhör inte kursens katalog.");
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
						case "NONE": System.out.println("Hela uppgiftskatalogen är ännu inte fullständig.");
						gradePoints = 0;
							break loop;
						case "U": System.out.println("Studenten har en underkänd uppgift i katalogen, och "
								+ "är därför inte klar med kursen. \nUnderkänd uppgift: " +
								a.getAssignmentName());
						gradePoints = 0;
							break loop;
						case "G": gradePoints += 1;
							break;
						case "VG": gradePoints += 2;
							break;
						default: 
							System.out.print("Betyget: " + selectedStudent.getAssignmentGrade().get(a) + " för uppgift " + 
									a.getAssignmentName() + " är i felaktigt format och bör ändras. Giltiga format är (");
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
		if (gradePoints >= (selectedCourse.getMaxPoints()*0.82)) System.out.println("Studenten har fått betyget VG i kursen.");
		else if ((gradePoints >= (selectedCourse.getMaxPoints()*0.4)) && (gradePoints < (selectedCourse.getMaxPoints()*0.82))) 
		System.out.println("Studenten har fått betyget G i kursen.");
	}
	
	//ALLT UNDER DEN HÄR LINJEN ÄR ENBART FÖR ATT VISA ATT KODEN FUNGERAR MED SIMULERAD TESTDATA.
	//-------------------------------------------------------------------------------------------
	
	public static void create() throws Exception {
		
		Course C725G31 = new Course("Java", "725G31", 3.0);					//DUMMIES FÖR INL2, GÖRS EGENTLIGEN I EGET ANVÄNDNINGSFALL OCH KLASS
		Course C725G80 = new Course("Affärssystem", "725G80", 4.0);
		
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
		
		//Borttagna pg bd-stöd
//		Student roykr837 = new Student("roykr837", "Roy Kronemberg");			
//		Student jonbo488 = new Student("jonbo488", "Jonas Borg");
		
		
		PersistentStorage ps = new PersistentStorage();
		
		
		C725G31.setCourseStudents(ps.getStudents(C725G31.getCourseCode()).get(C725G31.getCourseCode()));
		C725G80.setCourseStudents(ps.getStudents(C725G80.getCourseCode()).get(C725G80.getCourseCode()));
		
		//Borttagna pga db-stöd
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
