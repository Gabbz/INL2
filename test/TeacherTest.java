package test;

import java.util.Scanner;

import funksam.Teacher;

public class TeacherTest {

	public static void main(String[] args) {
		
		Teacher teacher = new Teacher();
		
		//Metoden ska initiera programmet, ska klara av felaktig inmatning
		teacher.init();
		
		// Metoden över måste vara utkommenterad. Metoden ska fråga användaren vilken kurs den vill sätta betyg på.
		Scanner scanner = new Scanner(System.in);
		teacher.setGrade(scanner);
		scanner.close();
		
	}

}
