import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Clase principal Main
public class Main {

    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String opcion;

            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Agregar nuevo contacto");
                System.out.println("2. Mostrar lista de contactos");
                System.out.println("3. Buscar por nombre");
                System.out.println("4. Buscar por teléfono");
                System.out.println("5. Salir");
                System.out.println("6. ");
                System.out.print("Ingrese su opción: ");
                opcion = br.readLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Nombre: ");
                        String nombre = br.readLine();
                        System.out.print("Apellido: ");
                        String apellido = br.readLine();
                        System.out.print("Correo: ");
                        String correo = br.readLine();
                        System.out.print("Teléfono: ");
                        String telefono = br.readLine();
                        System.out.print("Descripción: ");
                        String descripcion = br.readLine();

                        Contacto nuevoContacto = new Contacto(nombre, apellido, correo, telefono, descripcion);
                        gestor.agregarContacto(nuevoContacto);
                        System.out.println("Contacto agregado exitosamente.");
                        break;

                    case "2":
                        gestor.mostrarContactos();
                        break;

                    case "3":
                        System.out.print("Ingrese el nombre a buscar: ");
                        String nombreBuscado = br.readLine();
                        Contacto contactoEncontradoPorNombre = gestor.buscarPorNombre(nombreBuscado);
                        if (contactoEncontradoPorNombre != null) {
                            System.out.println("Contacto encontrado: " + contactoEncontradoPorNombre);
                        } else {
                            System.out.println("Contacto no encontrado.");
                        }
                        break;

                    case "4":
                        System.out.print("Ingrese el teléfono a buscar: ");
                        String telefonoBuscado = br.readLine();
                        Contacto contactoEncontradoPorTelefono = gestor.buscarPorTelefono(telefonoBuscado);
                        if (contactoEncontradoPorTelefono != null) {
                            System.out.println("Contacto encontrado: " + contactoEncontradoPorTelefono);
                        } else {
                            System.out.println("Contacto no encontrado.");
                        }
                        break;

                    case "5":
                        gestor.guardarContactosEnArchivo();
                        System.out.println("¡Hasta luego!");
                        break;

                    case "6":
                        // Convertir a XML
                        UtilidadConversor.guardarContactosEnXML(gestor);
                        break;

                    case "7":
                        // Cargar desde XML
                        gestor = UtilidadConversor.cargarContactosDesdeXML();
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del 1 al 5.");
                        break;
                }

            } while (!opcion.equals("5"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
