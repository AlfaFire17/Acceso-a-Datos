import java.io.FileInputStream;
import java.io.IOException;

public class LeerEncabezadoBMP {

    public static void main(String[] args) {
        try {
            String archivo = "Pon la ruta del archivo"; // Reemplaza con la ruta de tu archivo BMP
            leerEncabezadoBMP(archivo); // Llamada al método para leer el encabezado BMP
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerEncabezadoBMP(String archivo) throws IOException {
        FileInputStream fis = new FileInputStream(archivo); // Crea un objeto FileInputStream para leer el archivo
        byte[] encabezado = new byte[54]; // Crea un arreglo de bytes de tamaño 54 para almacenar el encabezado
        fis.read(encabezado);

        // Extrae información del encabezado BMP
        int tamanoArchivo = bytesAEntero(encabezado, 2);
        int ancho = bytesAEntero(encabezado, 18);
        int alto = bytesAEntero(encabezado, 22);
        int compresion = bytesAEntero(encabezado, 30);
        fis.close();

        // Imprime la información del encabezado
        System.out.println("Tamaño del archivo: " + tamanoArchivo + " bytes");
        System.out.println("Ancho de la imagen: " + ancho + " pixels");
        System.out.println("Alto de la imagen: " + alto + " pixels");
        System.out.println("Compresión: " + (compresion == 0 ? "No" : "Sí"));
    }

    public static int bytesAEntero(byte[] bytes, int offset) {
        int resultado = 0;
        for (int i = 0; i < 4; i++) {
            resultado |= (bytes[offset + i] & 0xFF) << (i * 8);
        }
        return resultado;
    }
}