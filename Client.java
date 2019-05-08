import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
public class Client {

    private static int MAX_TRY = 7;
    private static int INIT_PORT = 8888;
    private static String HOST = "server";
    private static Socket socket = null;
    private static ObjectOutputStream socketOutput = null;
    private static ObjectInputStream socketInput = null;

    public static String OUTPUT = "PRINT";
    public static String INPUT = "INPUT";

    public static void main( String[] args ) {
        Scanner keyboard;
        System.out.println("Connect to server " + HOST + ":" + INIT_PORT);
        try {
            // init socket with init port
            socket = new Socket(HOST, INIT_PORT);
            // send "start" to get new port
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            socketOutput.writeObject("start");

            // get new port to change connection
            socketInput = new ObjectInputStream(socket.getInputStream());
            String newPort = (String) socketInput.readObject();
            System.out.println("new Port: " + newPort);
            keyboard = new Scanner(System.in);

            //// switch to new port
            socket = new Socket(HOST, Integer.parseInt(newPort));
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            socketInput = new ObjectInputStream(socket.getInputStream());

            boolean isRunning = true;
            while(isRunning){
                String response = (String) socketInput.readObject();
                String[] tmp = response.split("@");

                String text = tmp[1];
                String action = tmp[0];
                if(action.equals(OUTPUT)){
                    System.out.println(text);
                }else if(action.equals(INPUT)){
                    System.out.print(text);
                    String input = keyboard.nextLine();
                    socketOutput.writeObject(input);
                }

            }

            // send "exit" for tell server thread to close socket
            socketOutput.writeObject("exit");

            // close socket
            socketOutput.close();
            socketInput.close();
            socket.close();

        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

