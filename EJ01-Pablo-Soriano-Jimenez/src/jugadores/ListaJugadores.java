package jugadores;

import java.io.*;
import java.util.ArrayList;

public class ListaJugadores extends ArrayList<Jugador> {
    public void leerListaJugadores(String fileName) throws IOException, ClassNotFoundException {
        File miFichero = new File(fileName);
        if (miFichero.exists()) {
            ObjectInputStream objectFichero = new ObjectInputStream(new FileInputStream(miFichero));
            int numJugadores = objectFichero.read();
            for (; numJugadores > 0; numJugadores--) {
                add((Jugador) objectFichero.readObject());
            }
            objectFichero.close();
        }
    }

    public void escribirListaJugadores(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Jugador jugador : this) {
            writer.write(jugador.toString());
            writer.newLine();
        }
        writer.close();
    }
}