package test;

import java.util.Scanner;

import funksam.Teacher;

public class TeacherTest {

	public static void main(String[] args) {
		
		Teacher teacher = new Teacher();
		
		//Metoden ska initiera programmet, ska klara av felaktig inmatning
		teacher.init();
		
		// Metoden �ver m�ste vara utkommenterad. Metoden ska fr�ga anv�ndaren vilken kurs den vill s�tta betyg p�.
		Scanner scanner = new Scanner(System.in);
		teacher.setGrade(scanner);
		scanner.close();
		
	}

}
