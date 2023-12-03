package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú de CRUD de Alumnos");
            System.out.println("1. Mostrar Alumnos");
            System.out.println("2. Agregar Alumnos");
            System.out.println("3. Actualizar Alumnos");
            System.out.println("4. Eliminar Alumnos");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    List<Persona> personas = CRUDManager.getAll();
                    System.out.println("Registros en la base de datos:");
                    for (Persona persona : personas) {
                        System.out.println(persona);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del alumno: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese la edad del alumno: ");
                    int edad = scanner.nextInt();
                    CRUDManager.insert(nombre, edad);
                    System.out.println("Alumno agregado con éxito.");
                    break;
                case 3:
                    System.out.print("Ingrese el ID del alumno a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    System.out.print("Ingrese el nuevo nombre del alumno: ");
                    String nuevoNombre = scanner.next();
                    System.out.print("Ingrese la nueva edad del alumno: ");
                    int nuevaEdad = scanner.nextInt();
                    CRUDManager.update(idActualizar, nuevoNombre, nuevaEdad);
                    System.out.println("Alumno actualizado con éxito.");
                    break;
                case 4:
                    System.out.print("Ingrese el ID del alumno a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    CRUDManager.delete(idEliminar);
                    System.out.println("Alumno eliminado con éxito.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}

