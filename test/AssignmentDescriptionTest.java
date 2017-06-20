package test;

import funksam.Assignment;
import funksam.AssignmentDescription;

public class AssignmentDescriptionTest {

	public static void main(String[] args) {
		
		//Kod f�r testning
		Assignment assignment = new Assignment ("INL1", "En uppgift d�r syftet �r att g�ra klart uppgiften");
		Assignment assignment2 = new Assignment ("INL2", "En uppgift till d�r syftet �r att g�ra klart uppgiften");
		
		//Ska skapa en instans av AssignemntDescription
		AssignmentDescription assDesc = assignment.getAssignmentDescription();
		
		//Ska vara av datatypen AssignmentDescription
		System.out.println(assDesc.getClass());
		
		//Ska l�gga till en beskrivning
		assDesc.addDesc(assignment2.getAssignmentName(), assignment2.getAssignmentDescription().getDescription("INL2"));
		//Alternativt manuellt
		assDesc.addDesc("INL3", "En tredje");
		
		//Ska skriva ut de tre uppgifterna
		System.out.println(assDesc.getDescription("INL1"));
		System.out.println(assDesc.getDescription("INL2"));
		System.out.println(assDesc.getDescription("INL3"));
		
	}

}
