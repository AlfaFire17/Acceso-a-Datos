package jugadores;

import java.io.*;
import java.util.ArrayList;

public class ListaJugadores extends ArrayList<Jugador> {
    private static final String FORMATO_XML = "xml";
    private static final String FORMATO_OBJ = "obj";

    private String formatoActual = FORMATO_XML;

    public void leerListaJugadores(String fileName) throws IOException, ClassNotFoundException {
        File miFichero = new File(fileName);
        if (miFichero.exists()) {
            if (FORMATO_XML.equals(formatoActual)) {
                // Leer en formato XML
                ObjectInputStream objectFichero = new ObjectInputStream(new FileInputStream(miFichero));
                int numJugadores = objectFichero.readInt();
                for (; numJugadores > 0; numJugadores--) {
                    add((Jugador) objectFichero.readObject());
                }
                objectFichero.close();
            } else if (FORMATO_OBJ.equals(formatoActual)) {
                // Leer en formato de objeto
                ObjectInputStream objectFichero = new ObjectInputStream(new FileInputStream(miFichero));
                ListaJugadores listaJugadores = (ListaJugadores) objectFichero.readObject();
                addAll(listaJugadores);
                objectFichero.close();
            }
        }
    }

    public void escribirListaJugadores(String fileName) throws IOException {
        if (FORMATO_XML.equals(formatoActual)) {
            // Escribir en formato XML
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Jugador jugador : this) {
                writer.write(jugador.toString());
                writer.newLine();
            }
            writer.close();
        } else if (FORMATO_OBJ.equals(formatoActual)) {
            // Escribir en formato de objeto
            ObjectOutputStream objectFichero = new ObjectOutputStream(new FileOutputStream(fileName));
            objectFichero.writeInt(size());
            for (Jugador jugador : this) {
                objectFichero.writeObject(jugador);
            }
            objectFichero.close();
        }
    }

    public void cambiarFormatoArchivo() {
        if (FORMATO_XML.equals(formatoActual)) {
            formatoActual = FORMATO_OBJ;
            System.out.println("Formato cambiado a OBJ");
        } else if (FORMATO_OBJ.equals(formatoActual)) {
            formatoActual = FORMATO_XML;
            System.out.println("Formato cambiado a XML");
        }
    }
}
