import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploDeCopiaDeDatos {
    public static void main(String[] args) {
        final int bufferSize = 128;
        try (FileInputStream fIn = new FileInputStream("C:\\entrada.dat");
             FileOutputStream fOut = new FileOutputStream("C:\\salida.dat")) {
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = fIn.read(buffer)) != -1) {
                fOut.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}