import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    //crear los datos de conexion del servidor
    static String host = "localhost";
    static int puerto = 8000;


    public static void main(String[] args) throws IOException {
        //creamos e inicializamos variables
        Socket socket = new Socket(host, puerto);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream escribir = new ObjectOutputStream(socket.getOutputStream());
        OutputStreamWriter escri = new OutputStreamWriter(socket.getOutputStream());
        ObjectInputStream leer = new ObjectInputStream(socket.getInputStream());

        Persona p = new Persona();
        System.out.println("Nombre de usuario");
        String nombre = br.readLine();
        p.setNombre(nombre);
        //el primer mensaje que se ejecuta en el servidor
        p.setMensaje(p.getNombre() + " se a conectado");
        int opcion = 0;
        escribir.writeObject(p);

        do {
            //mostramos menu con opciones
            System.out.println("Menu");
            System.out.println("1.Escribir en chat");
            System.out.println("2.Salir");

            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error se esperaba numero");
            } catch (IOException e) {
                System.out.println("Error inesperado por favor revise");
            }


            switch (opcion) {

                case 1:

                    //se  escribe le mensaje que se mandara
                    System.out.println("Escribe algo:");
                    String paloma = br.readLine();

                    p.setMensaje(p.getNombre() + ": " + paloma);
                    p.setOpcion(opcion);
                    //se manda el mensaje al servidor
                    escribir.writeObject(p = new Persona(nombre, paloma, opcion));

                    break;
                case 2:
                    p.setMensaje("salir");
                    p.setOpcion(1);
                    break;


            }

        } while (opcion != 2);

        socket.close();


    }

}
