import java.io.*;

public class BlocDeNotas {

    public static void main(String[] args) {
        try {
            // Pedir al usuario el nombre del archivo
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el nombre del archivo: ");
            String nombreArchivo = br.readLine();

            // Verificar si el archivo ya existe
            File archivo = new File(nombreArchivo);
            boolean existe = archivo.exists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

            // Verificar si se desea sobrescribir el archivo
            if (existe) {
                System.out.print("El archivo ya existe. ¿Desea sobrescribirlo? (s/n): ");
                String respuesta = br.readLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    writer.close();
                    return;
                }
            }

            // Leer oraciones del usuario y escribir en el archivo
            int numeroLinea = 1;
            while (true) {
                System.out.print("Ingrese una oración (o escriba 'fin' para terminar): ");
                String oracion = br.readLine();
                if (oracion.equalsIgnoreCase("fin")) break;
                writer.write(numeroLinea + ". " + oracion + "\n");
                numeroLinea++;
            }

            // Cerrar el BufferedWriter
            writer.close();

            System.out.println("Las oraciones se han guardado en el archivo.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}