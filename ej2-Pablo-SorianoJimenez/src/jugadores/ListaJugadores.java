package jugadores;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class ListaJugadores extends ArrayList<Jugador> {

    public void leerListaJugadores(String fileName) throws IOException {
        File archivo = new File(fileName);

        if (archivo.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";"); // Utiliza un separador (por ejemplo, ";")
                if (parts.length == 5) {
                    Jugador jugador = new Jugador(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    add(jugador);
                }
            }
        }
        if (fileName.toLowerCase().endsWith(".xml")) {
            try (XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)))) {
                ArrayList<Jugador> jugadores = (ArrayList<Jugador>) xmlDecoder.readObject();
                addAll(jugadores);
            }
        }
    }

    public void escribirListaJugadores(String fileName) throws IOException {
        // Añade el código para escribir en formato XML si el archivo tiene extensión ".xml"
        if (fileName.toLowerCase().endsWith(".xml")) {
            try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                xmlEncoder.writeObject(this);
            }
        } else {
            // El código existente para escribir en formato TXT
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Jugador jugador : this) {
                    String line = "Nombre: " + jugador.getNombre() + "; " + "Apodo: " + jugador.getApodo() + "; " +
                            "Puesto: " + jugador.getPuesto() + "; " + "Dorsal: " + jugador.getDorsal() + "; " + "Descripcion: " + jugador.getDescripcion();
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}