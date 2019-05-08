import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProtocal implements Runnable {
    private ServerSocket server;
    private int port;
    private ObjectOutputStream socketOutput;
    private ObjectInputStream socketInput;

    public String OUTPUT = "PRINT";
    public String INPUT = "INPUT";
    

    final static String[] words = { "AVENGER", "AVATAR", "TOYSTORY", "DORAEMON", "ARROW", "ANNABELL", "CONAN",
            "HARRYPOTTER", "GOOSEBUMP", "STARWAR", "SHAZAM", "THEMATRIX", "TRANFORMERS","ARRIETTY","UP","JUMANJI" };
    private static String word = words[(int) (Math.random() * words.length)];
    public static String asterisk = new String(new char[word.length()]).replace("\0", "*");
    public static int count = 0;

    private int MAX_TRY = 7;

    public ServerProtocal(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            Socket socket = server.accept();
            socketInput = new ObjectInputStream(socket.getInputStream());
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            boolean running = true;
            while (running) {
                send_client_print("Guess any letter in the word");
                send_client_print(this.asterisk);
                String guess = send_client_get_input("Guess:");
                hang(guess);
                if(this.count >= 7 || !this.asterisk.contains("*")){
                    running = false;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void hang(String guess) {
        System.out.println("hang Got " + guess);
        String newasterisk = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.toUpperCase().charAt(0)) {
                newasterisk += guess.toUpperCase().charAt(0);
            } else if (asterisk.charAt(i) != '*') {
                newasterisk += word.charAt(i);
            } else {
                newasterisk += "*";
            }
        }

        System.out.println("newasterisk " + newasterisk);


        if (asterisk.equals(newasterisk)) {
            count++;
            hangmanImage();
        } else {
            asterisk = newasterisk.toUpperCase();
        }
        if (asterisk.equals(word)) {
            send_client_print("Correct! You win! The word was " + word);
            System.exit(0);
        }
    }

    public void hangmanImage() {
        if (count == 1) {
            send_client_print("Wrong guess, try again");
            send_client_print(" ");
            send_client_print(" ");
            send_client_print(" ");
            send_client_print(" ");
            send_client_print("___|___");
            send_client_print(" ");
        }
        if (count == 2) {
            send_client_print("Wrong guess, try again");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("___|___");
        }
        if (count == 3) {
            send_client_print("Wrong guess, try again");
            send_client_print("   ____________");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   | ");
            send_client_print("___|___");
        }
        if (count == 4) {
            send_client_print("Wrong guess, try again");
            send_client_print("   ____________");
            send_client_print("   |          _|_");
            send_client_print("   |         /   \\");
            send_client_print("   |        |     |");
            send_client_print("   |         \\_ _/");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("   |");
            send_client_print("___|___");
        }
        if (count == 5) {
            send_client_print("Wrong guess, try again");
            send_client_print("   ____________");
            send_client_print("   |          _|_");
            send_client_print("   |         /   \\");
            send_client_print("   |        |     |");
            send_client_print("   |         \\_ _/");
            send_client_print("   |           |");
            send_client_print("   |           |");
            send_client_print("   |");
            send_client_print("___|___");
        }
        if (count == 6) {
            send_client_print("Wrong guess, try again");
            send_client_print("   ____________");
            send_client_print("   |          _|_");
            send_client_print("   |         /   \\");
            send_client_print("   |        |     |");
            send_client_print("   |         \\_ _/");
            send_client_print("   |           |");
            send_client_print("   |           |");
            send_client_print("   |          / \\ ");
            send_client_print("___|___      /   \\");
        }
        if (count == 7) {
            send_client_print("GAME OVER!");
            send_client_print("   ____________");
            send_client_print("   |          _|_");
            send_client_print("   |         /   \\");
            send_client_print("   |        |     |");
            send_client_print("   |         \\_ _/");
            send_client_print("   |          _|_");
            send_client_print("   |         / | \\");
            send_client_print("   |          / \\ ");
            send_client_print("___|___      /   \\");
            send_client_print("GAME OVER! The word was " + word);
            System.exit(0);
        }
    }

    private void send_client_print(String str) {
        try {
        socketOutput.writeObject(OUTPUT + "@" + str);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String send_client_get_input(String str) {
        try{
            socketOutput.writeObject(INPUT + "@" + str);
            String recv = (String) socketInput.readObject();
            return recv;
        } catch (Exception e) {
            System.err.println(e);
            return "error";
        }
    }
}
