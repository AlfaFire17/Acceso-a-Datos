import java.io.*;

public class OrdenarFicheros {

    public static void main(String[] args) {
        try {
            // Pedir al usuario los nombres de los archivos
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre del primer archivo: ");
            String nombreArchivo1 = br.readLine();
            System.out.print("Ingrese el nombre del segundo archivo: ");
            String nombreArchivo2 = br.readLine();

            // Leer los contenidos de los archivos
            BufferedReader reader1 = new BufferedReader(new FileReader(nombreArchivo1));
            BufferedReader reader2 = new BufferedReader(new FileReader(nombreArchivo2));

            // Crear un BufferedWriter para escribir en el archivo ordenado.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("ordenado.txt"));

            // Leer y combinar las líneas de ambos archivos
            String linea1 = reader1.readLine();
            String linea2 = reader2.readLine();

            while (linea1 != null && linea2 != null) {
                // Comparar las líneas y escribir en el archivo ordenado.txt
                if (linea1.compareTo(linea2) < 0) {
                    writer.write(linea1 + "\n");
                    linea1 = reader1.readLine();
                } else {
                    writer.write(linea2 + "\n");
                    linea2 = reader2.readLine();
                }
            }

            // Copiar el resto de líneas del primer archivo (si quedan)
            while (linea1 != null) {
                writer.write(linea1 + "\n");
                linea1 = reader1.readLine();
            }

            // Copiar el resto de líneas del segundo archivo (si quedan)
            while (linea2 != null) {
                writer.write(linea2 + "\n");
                linea2 = reader2.readLine();
            }

            // Cerrar los lectores y el escritor
            reader1.close();
            reader2.close();
            writer.close();

            System.out.println("Archivos ordenados correctamente en 'ordenado.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
