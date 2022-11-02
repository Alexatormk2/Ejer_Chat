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
//crea el server sockets
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                //acepta lo que venga del cliente
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            //llama al hilo
            new EchoThread(socket).start();
        }

    }
}

class EchoThread extends Thread {
    protected Socket socket;
    int contador =0;

    //constructor para recibir el socket
    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        //variable creadas de antemano para su posterior uso


        ObjectInputStream leer = null;

        //persona que se usara de placeholder
        Persona p = new Persona("","",0);
        try {

            leer = new ObjectInputStream(socket.getInputStream());

            //inicio del chat
            System.out.println("--------CHAT-------");


        } catch (IOException e) {
            return;
        }
        try {//lee el objeto que llegue desde el cliente
            //esta es la primera carga para que no empieze null
            p = (Persona) leer.readObject();
        } catch (IOException e) {
            //en caso de null o error se muestra uno de estos dos mensajes
            System.out.println("Usuario desconectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Usuario desconocido");
        }
        //loop del chat
        do {

            try {

        //printea el mensaje

                System.out.println(p.getMensaje());
             //busca si llego algo nuevo

                p = (Persona) leer.readObject();
            } catch (IOException e) {
                System.out.println("Usuario desconectado");
                //si el usuario se desconecta se suma +1 a contado que al llegar a 40 cierra el servidor
                contador++;
            } catch (ClassNotFoundException e) {
                System.out.println("Usuario desconocido");
            }





        }

        while (contador != 40);
        System.out.println("Servidor sobrecargado cerrando :c");




    }
}