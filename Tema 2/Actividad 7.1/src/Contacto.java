import java.io.Serializable;

// Definición de la clase Contacto
public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String descripcion;

    // Constructor de la clase Contacto
    public Contacto(String nombre, String apellido, String correo, String telefono, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    // Método para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido +
                ", Correo: " + correo + ", Teléfono: " + telefono +
                ", Descripción: " + descripcion;
    }

    public Object getNombre() {
        return nombre;
    }

    public Object getTelefono() {
        return telefono;
    }
}
