import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

    static String host = "localhost";
    static int puerto = 5382;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket(host, puerto);
        ObjectInputStream leer = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream escribir = new ObjectOutputStream(socket.getOutputStream());
        Persona p = new Persona();
        System.out.println("Nombre de usuario");
        String nombre = br.readLine();
        p.setNombre(nombre);
        p.setMensaje(p.getNombre() + " se a conectado");

        escribir.writeObject(p);
        int opcion;
        do {
            System.out.println("Menu");
            System.out.println("1.Escribir en chat");
            System.out.println("2.Salir");
            opcion = Integer.parseInt(br.readLine());


            switch (opcion) {

                case 1:
                    System.out.println("Escribe algo:");
                    String paloma = br.readLine();
                    p.setMensaje(p.getNombre() + ": " + paloma);
                    escribir.writeObject(p);

                    break;


            }

        } while (opcion != 2);
        socket.close();


    }

}
