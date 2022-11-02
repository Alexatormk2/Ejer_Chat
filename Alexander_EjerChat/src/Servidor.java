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
    int contador =0;
    String linea;
    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {

        ObjectOutputStream escribir = null;

        InputStreamReader le = null;
        ObjectInputStream leer = null;
        int opcion = 0;
        Persona p = new Persona("","",0);
        try {
linea = "hola";
            escribir = new ObjectOutputStream(socket.getOutputStream());
            leer = new ObjectInputStream(socket.getInputStream());
            le = new InputStreamReader(socket.getInputStream());

            System.out.println("--------CHAT-------");


        } catch (IOException e) {
            return;
        }
        try {
            p = (Persona) leer.readObject();
        } catch (IOException e) {
            System.out.println("Usuario desconectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Usuario desconocido");
        }
        do {

            try {
               // p = (Persona) leer.readObject();
                String mensaje = p.getMensaje();
                //linea =  p.getMensaje();
                System.out.println(p.getMensaje());
                System.out.println("segunda lectura  ");

                p = (Persona) leer.readObject();
            } catch (IOException e) {
                System.out.println("Usuario desconectado");
                contador++;
            } catch (ClassNotFoundException e) {
                System.out.println("Usuario desconocido");
            }





        }

        while (contador != 40);
        System.out.println("Servidor sobrecargado cerrando :c");




    }
}