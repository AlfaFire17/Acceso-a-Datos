import java.io.*;

public class BuscarCadenaEnArchivo {

    public static void main(String[] args) {
        try {
            // Pedir al usuario el nombre del archivo y la cadena a buscar
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre del archivo: ");
            String nombreArchivo = br.readLine();
            System.out.print("Ingrese la cadena a buscar: ");
            String cadenaBuscada = br.readLine();

            // Verificar si el archivo existe
            File archivo = new File(nombreArchivo);
            if (!archivo.exists()) {
                System.out.println("El archivo no existe.");
                return;
            }

            // Leer el archivo y buscar la cadena
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;
            boolean encontrada = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains(cadenaBuscada)) {
                    System.out.println("Línea " + numeroLinea + ": " + linea);
                    encontrada = true;
                }
                numeroLinea++;
            }

            // Si no se encontró la cadena, imprimir un mensaje
            if (!encontrada) {
                System.out.println("La cadena no se encontró en el archivo.");
            }

            // Cerrar el BufferedReader
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
