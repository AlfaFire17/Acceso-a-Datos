import java.io.FileInputStream;
import java.io.IOException;

public class DetectarFormatoImagen {

    public static void main(String[] args) {
        try {
            String archivo = "Pon la ruta"; // Reemplaza con la ruta de tu archivo

            byte[] primerosBytes = leerPrimerosBytes(archivo);

            // Se verifica el formato de la imagen y se imprime un mensaje correspondiente
            if (esFormatoBMP(primerosBytes)) {
                System.out.println("El archivo es de formato BMP.");
            } else if (esFormatoGIF(primerosBytes)) {
                System.out.println("El archivo es de formato GIF.");
            } else if (esFormatoICO(primerosBytes)) {
                System.out.println("El archivo es de formato ICO.");
            } else if (esFormatoJPEG(primerosBytes)) {
                System.out.println("El archivo es de formato JPEG.");
            } else if (esFormatoPNG(primerosBytes)) {
                System.out.println("El archivo es de formato PNG.");
            } else {
                System.out.println("El formato del archivo no pudo ser determinado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] leerPrimerosBytes(String archivo) throws IOException {
        byte[] buffer = new byte[8]; // Leemos los primeros 8 bytes para asegurarnos de tener suficiente información
        FileInputStream fis = new FileInputStream(archivo);
        fis.read(buffer);
        fis.close();
        return buffer;
    }

    public static boolean esFormatoBMP(byte[] primerosBytes) {
        // Reciben los primeros bits y verifican si corresponde con el formato BMP
        return primerosBytes[0] == (byte) 0x42 && primerosBytes[1] == (byte) 0x4D;
    }

    public static boolean esFormatoGIF(byte[] primerosBytes) {
        // Reciben los primeros bits y verifican si corresponde con el formato GIF
        return (primerosBytes[0] == (byte) 0x47 && primerosBytes[1] == (byte) 0x49 &&
                primerosBytes[2] == (byte) 0x46 && primerosBytes[3] == (byte) 0x38 &&
                primerosBytes[4] == (byte) 0x39 && primerosBytes[5] == (byte) 0x61) ||
                (primerosBytes[0] == (byte) 0x37 && primerosBytes[1] == (byte) 0x61);
    }

    public static boolean esFormatoICO(byte[] primerosBytes) {
        // Reciben los primeros bits y verifican si corresponde con el formato ICO
        return primerosBytes[0] == (byte) 0x00 && primerosBytes[1] == (byte) 0x00 &&
                primerosBytes[2] == (byte) 0x01 && primerosBytes[3] == (byte) 0x00;
    }

    public static boolean esFormatoJPEG(byte[] primerosBytes) {
        // Reciben los primeros bits y verifican si corresponde con el formato JPEG
        return primerosBytes[0] == (byte) 0xFF && primerosBytes[1] == (byte) 0xD8 &&
                primerosBytes[2] == (byte) 0xFF;
    }

    public static boolean esFormatoPNG(byte[] primerosBytes) {
        // Reciben los primeros bits y verifican si corresponde con el formato PNG
        return primerosBytes[0] == (byte) 0x89 && primerosBytes[1] == (byte) 0x50 &&
                primerosBytes[2] == (byte) 0x4E && primerosBytes[3] == (byte) 0x47;
    }
}