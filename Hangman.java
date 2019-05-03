import java.util.Scanner;

public class Hangman {
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

		Process p = new Process();		
		while (p.count < 7 && p.asterisk.contains("*")) {
			System.out.println("Guess any letter in the word");
			System.out.println(p.asterisk);
			String guess = sc.next();
			p.hang(guess);
		}
		sc.close();
		}
		
}
