import java.io.*;

public class MostrarContenido {

    public static void main(String[] args) {
        try {
            // Pedir al usuario el nombre del archivo
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre del archivo: ");
            String nombreArchivo = br.readLine();

            // Leer el archivo
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));

            String linea;
            int contador = 0;

            // Leer y mostrar el contenido del archivo
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                contador++;

                // Detenerse cada 23 líneas
                if (contador % 23 == 0) {
                    System.out.println("Presione 'Enter' para continuar...");
                    br.readLine(); // Esperar a que el usuario presione una tecla
                }
            }

            // Cerrar el BufferedReader
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}