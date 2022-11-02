import java.io.Serializable;
import java.net.ServerSocket;

public class Persona implements Serializable {

    String nombre;
    String mensaje;
    int opcion;


    public Persona(String nombre, String mensaje,int opcion) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.opcion = opcion;
    }

    public Persona() {
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
