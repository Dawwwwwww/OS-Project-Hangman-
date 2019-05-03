import java.util.Scanner;

public class Process {

	final static String[] words = {"AVENGER", "AVATAR", "TOYSTORY", "DORAEMON", "ARROW","ANNABELL","CONAN","HARRYPOTTER","GOOSEBUMP","STARWAR" , "SHAZAM" , "THEMATRIX" , "TRANFORMERS" };
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
public static void hangmanImage() {
  if (count == 1) {
   System.out.println("Wrong guess, try again");
   System.out.println();
   System.out.println();
   System.out.println();
   System.out.println();
   System.out.println("___|___");
   System.out.println();
  }
  if (count == 2) {
   System.out.println("Wrong guess, try again");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("___|___");
  }
  if (count == 3) {
   System.out.println("Wrong guess, try again");
   System.out.println("   ____________");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   | ");
   System.out.println("___|___");
  }
  if (count == 4) {
   System.out.println("Wrong guess, try again");
   System.out.println("   ____________");
   System.out.println("   |          _|_");
   System.out.println("   |         /   \\");
   System.out.println("   |        |     |");
   System.out.println("   |         \\_ _/");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("   |");
   System.out.println("___|___");
  }
  if (count == 5) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 6) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (count == 7) {
			System.out.println("GAME OVER!");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
			System.out.println("GAME OVER! The word was " + word);
		}
}

