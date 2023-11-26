import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

public class UtilidadConversor {

    // Método para guardar los contactos en un archivo XML
    public static void guardarContactosEnXML(GestorContactos gestor) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("contactos.xml")))) {
            // Serializa la lista de contactos en formato XML
            xmlEncoder.writeObject(gestor);
            System.out.println("Contactos guardados en formato XML.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar los contactos desde un archivo XML
    public static GestorContactos cargarContactosDesdeXML() {
        try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("contactos.xml")))) {
            // Deserializa la lista de contactos desde el archivo XML
            GestorContactos gestor = (GestorContactos) xmlDecoder.readObject();
            System.out.println("Contactos cargados desde el archivo XML.");
            return gestor;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
