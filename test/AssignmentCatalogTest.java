package test;

import funksam.Assignment;
import funksam.AssignmentCatalog;
import funksam.Course;

public class AssignmentCatalogTest {

	public static void main(String[] args) {
		
		//Skapar n�dv�ndig data f�r att testa funktionerna i uppgiftskatalogen
		Assignment assignment1 = new Assignment ("INL1", "En uppgift d�r syftet �r att g�ra klart uppgiften");
		Assignment assignment2 = new Assignment ("INL2", "En uppgift d�r syftet �r att g�ra klart uppgiften");
		Course C725G80 = new Course("Aff�rssystem", "725G80", 4.0);
		
		//Ska skapa en ny uppgiftskatalog
		AssignmentCatalog assignmentCatalog = new AssignmentCatalog();
		
		//Ska l�gga till uppgifter i katalogen
		assignmentCatalog.addAssignment(assignment1, C725G80);
		assignmentCatalog.addAssignment(assignment2, C725G80);
		
		//Ska v�lja en uppgift efter namnet p� uppgiften som string.
		System.out.println(assignmentCatalog.selectAssignment("INL1").getAssignmentName());
		
		//Ska skriva ut alla uppgifter i en uppgiftskatalog
		assignmentCatalog.getAssignments();

	}

}
