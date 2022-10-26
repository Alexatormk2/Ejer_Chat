import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

    static String host = "localhost";
    static int puerto = 8000;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(host, puerto);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream escribir = new ObjectOutputStream(socket.getOutputStream());
        OutputStreamWriter escri = new OutputStreamWriter(socket.getOutputStream()) ;
        ObjectInputStream leer = new ObjectInputStream(socket.getInputStream());

        Persona p = new Persona();
        System.out.println("Nombre de usuario");
        String nombre = br.readLine();
        p.setNombre(nombre);
        p.setMensaje(p.getNombre() + " se a conectado");
        int opcion =0;
        escribir.writeObject(p);
      escri.write(opcion);

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
                case 2:
                    p.setMensaje("salir");

                    break;


            }

        } while (opcion != 2);
        socket.close();


    }

}
