import java.io.*;
import java.util.ArrayList;

// Definición de la clase GestorContactos
public class GestorContactos {
    private ArrayList<Contacto> listaContactos;

    // Constructor de la clase GestorContactos
    public GestorContactos() {
        listaContactos = new ArrayList<>();
        cargarContactosDesdeArchivo();
    }

    // Método para agregar un contacto a la lista
    public void agregarContacto(Contacto contacto) {
        listaContactos.add(contacto);
    }

    // Método para mostrar la lista de contactos
    public void mostrarContactos() {
        for (int i = 0; i < listaContactos.size(); i++) {
            System.out.println("Contacto " + (i + 1) + ": " + listaContactos.get(i));
        }
    }

    // Método para buscar un contacto por nombre completo
    public Contacto buscarPorNombre(String nombre) {
        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equals(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    // Método para buscar un contacto por teléfono
    public Contacto buscarPorTelefono(String telefono) {
        for (Contacto contacto : listaContactos) {
            if (contacto.getTelefono().equals(telefono)) {
                return contacto;
            }
        }
        return null;
    }

    // Método para guardar la lista de contactos en un archivo
    public void guardarContactosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contactos.obj"))) {
            oos.writeObject(listaContactos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la lista de contactos desde un archivo
    private void cargarContactosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contactos.obj"))) {
            listaContactos = (ArrayList<Contacto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // No es un error grave si no se encuentra el archivo al inicio
            // simplemente se creará uno nuevo al guardar
            System.out.println("No se encontró el archivo de contactos.");
        }
    }
}
