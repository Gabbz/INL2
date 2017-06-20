package test;

import funksam.Assignment;
import funksam.AssignmentCatalog;
import funksam.Course;

public class AssignmentCatalogTest {

	public static void main(String[] args) {
		
		//Skapar nödvändig data för att testa funktionerna i uppgiftskatalogen
		Assignment assignment1 = new Assignment ("INL1", "En uppgift där syftet är att göra klart uppgiften");
		Assignment assignment2 = new Assignment ("INL2", "En uppgift där syftet är att göra klart uppgiften");
		Course C725G80 = new Course("Affärssystem", "725G80", 4.0);
		
		//Ska skapa en ny uppgiftskatalog
		AssignmentCatalog assignmentCatalog = new AssignmentCatalog();
		
		//Ska lägga till uppgifter i katalogen
		assignmentCatalog.addAssignment(assignment1, C725G80);
		assignmentCatalog.addAssignment(assignment2, C725G80);
		
		//Ska välja en uppgift efter namnet på uppgiften som string.
		System.out.println(assignmentCatalog.selectAssignment("INL1").getAssignmentName());
		
		//Ska skriva ut alla uppgifter i en uppgiftskatalog
		assignmentCatalog.getAssignments();

	}

}
