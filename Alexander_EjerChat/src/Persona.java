import java.io.Serializable;
import java.net.ServerSocket;

public class Persona implements Serializable {

    String nombre;
    String mensaje;



    public Persona(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    public Persona() {
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
