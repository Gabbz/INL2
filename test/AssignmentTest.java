package test;

import funksam.Assignment;

public class AssignmentTest {
	
	public static void main(String[] args) {
		
		//Ska skapa en uppgift
		Assignment assignment = new Assignment ("INL1", "En uppgift d�r syftet �r att g�ra klart uppgiften");
		
		//Ska skriva ut en beskrivning av uppgiften (Egen str�ng f�r utskrift av uppgiftsnamnet)
		assignment.describeAssignment();
		
		//Ska skriva ut namnet p� uppgiften
		System.out.println(assignment.getAssignmentName());
		
		//Ska returnera ett objekt av typen AssignemntDescription
		System.out.println(assignment.getAssignmentDescription().getClass());

	}

}
