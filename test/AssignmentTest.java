package test;

import funksam.Assignment;

public class AssignmentTest {
	
	public static void main(String[] args) {
		
		//Ska skapa en uppgift
		Assignment assignment = new Assignment ("INL1", "En uppgift där syftet är att göra klart uppgiften");
		
		//Ska skriva ut en beskrivning av uppgiften (Egen sträng för utskrift av uppgiftsnamnet)
		assignment.describeAssignment();
		
		//Ska skriva ut namnet på uppgiften
		System.out.println(assignment.getAssignmentName());
		
		//Ska returnera ett objekt av typen AssignemntDescription
		System.out.println(assignment.getAssignmentDescription().getClass());

	}

}
