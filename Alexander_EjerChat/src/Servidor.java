import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket socket = new ServerSocket(5382);
        Socket cliente = socket.accept();


        ObjectInputStream leer = new ObjectInputStream(cliente.getInputStream());
        OutputStreamWriter escribir = new OutputStreamWriter(cliente.getOutputStream());

        Persona p;
        System.out.println("--------CHAT-------");
        p = (Persona) leer.readObject();
        System.out.println(p.getMensaje());


    }
}