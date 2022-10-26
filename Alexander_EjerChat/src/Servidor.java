import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Objects;

public class Servidor {


    static final int PORT = 8000;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new EchoThread(socket).start();
        }

    }
}

class EchoThread extends Thread {
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {

        ObjectOutputStream escribir = null;

        InputStreamReader le = null;
        ObjectInputStream leer = null;
        int opcion = 0;
        Persona p = new Persona("","");
        try {

            escribir = new ObjectOutputStream(socket.getOutputStream());
            leer = new ObjectInputStream(socket.getInputStream());
            le = new InputStreamReader(socket.getInputStream());

            System.out.println("--------CHAT-------");


        } catch (IOException e) {
            return;
        }

        while (Objects.equals(p.getMensaje(), "salir")) {
            try {
                p = (Persona) leer.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(p.getMensaje());
            System.out.println("segunda lectura");
            try {
                opcion = le.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}