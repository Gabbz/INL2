package test;


import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		ArrayList<Integer> userInput = new ArrayList<>();
		System.out.println("Mata in 3x3 siffror:");
		System.out.println("B�geriasasd Royski");
		Scanner kbd = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			userInput.add(kbd.nextInt());
		}
		kbd.close();
		checkMagic(userInput);
	}

	private static void checkMagic(ArrayList<Integer> userInput) {
		
		if (magicOrNot(rowsAndColumns(userInput))) {
			System.out.println("Detta �r en magisk kvadrat!");
		} else  System.out.println("Detta �r bara en vanlig kvadrat");
		
	}
		
	private static boolean magicOrNot(ArrayList<Integer> sums) {
		//KOLLAR IFALL KOLUMNERNA OCH RADERNAS V�RDE �R SAMMA.
		return ((sums.get(0) == sums.get(1) && sums.get(0) == sums.get(2)) && 
				(sums.get(3) == sums.get(4) && sums.get(3) == sums.get(5) && 
				sums.get(0) == sums.get(3)));
	}
	
	private static ArrayList<Integer> rowsAndColumns(ArrayList<Integer> userInput) {
		//SUMMAN F�R RADER OCH KOLUMNER SPARAS H�R.
		ArrayList<Integer> sums = new ArrayList<>();
		int sumHolder;
		
		//ADDERAR IHOP KOLUMNERNAS V�RDE.
		for (int y = 0; y < 3; y++) {
			sumHolder = 0;
			for (int i = y; i < userInput.size(); i += 3) {
				sumHolder += userInput.get(i);
			}
			sums.add(sumHolder);
		}
		
		//ADDERAR IHOP RADERNAS V�RDE.
		for (int y = 0; y < userInput.size(); y += 3) {
			sumHolder = 0;
			for (int i = 0; i < 3; i++) {
				sumHolder += userInput.get(i);
			}
			sums.add(sumHolder);
		}
		return sums;
	}

}
