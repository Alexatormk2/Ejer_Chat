import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(5382);
        Socket cliente = socket.accept();

        InputStreamReader leer = new InputStreamReader(cliente.getInputStream());
        OutputStreamWriter escribir = new OutputStreamWriter(cliente.getOutputStream());
        


    }
}