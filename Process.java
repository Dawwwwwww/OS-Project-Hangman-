import java.util.Scanner;

public class Process {

	final static String[] words = {"Avenger", "Avater", "IRONMAN", "DORAEMON", "ARROW" ,"STARWAR" , "SHAZAM" , "THEMATRIX" };
	private static String word = words[(int) (Math.random() * words.length)];
	public static String asterisk = new String(new char[word.length()]).replace("\0", "*");
	public static int count = 0;

	public static void hang(String guess) {
		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(0)) {
				newasterisk += guess.charAt(0);
			} else if (asterisk.charAt(i) != '*') {
				newasterisk += word.charAt(i);
			} else {
				newasterisk += "*";
			}
		}

		if (asterisk.equals(newasterisk)) {
			count++;
			hangmanImage();
		} else {
			asterisk = newasterisk;
		}
		if (asterisk.equals(word)) {
			System.out.println("Correct! You win! The word was " + word);
		}
	}
	
}
