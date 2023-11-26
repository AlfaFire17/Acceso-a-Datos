import jugadores.Jugador;
import jugadores.ListaJugadores;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static ListaJugadores listaJugadores;

    private static int menu() {
        int opcion = 0;

        System.out.println("Menu de jugadores");
        System.out.println("-----------------");
        System.out.println("1º Añadir Jugador");
        System.out.println("2º Mostrar lista");
        System.out.println("3º Buscar jugador");
        System.out.println("0. Salir");
        while (!scanner.hasNextInt()) {
            System.out.println("Elige la opción que quieras");
            scanner.next();
        }

        opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    public static Jugador aniadirJugador() {
        Jugador jugador = new Jugador();
        System.out.print("Nombre: ");
        jugador.setNombre(scanner.nextLine());
        System.out.print("Apodo: ");
        jugador.setApodo(scanner.nextLine());
        System.out.print("Puesto: ");
        jugador.setPuesto(scanner.nextLine());
        System.out.print("Dorsal: ");
        jugador.setDorsal(scanner.nextLine());
        System.out.print("Descripcion: ");
        jugador.setDescripcion(scanner.nextLine());
        System.out.println();

        return jugador;
    }

    public static void verJugadores (Jugador jugador) {
        System.out.println(jugador.getNombre());
        System.out.println(jugador.getApodo());
        System.out.println(jugador.getPuesto());
        System.out.println(jugador.getDorsal());
        System.out.println(jugador.getDescripcion());
    }

    public static Jugador buscarJugador() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apodo: ");
        String apodo = scanner.nextLine();
        System.out.print("Dorsal: ");
        String dorsal = scanner.nextLine();
        System.out.println();

        for (Jugador j : listaJugadores) {
            if (j.getNombre().contains(nombre) && j.getApodo().contains(apodo) && j.getDorsal().contains(dorsal)) {
                return j;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        listaJugadores = new ListaJugadores();
        scanner = new Scanner(System.in);
        int opcion = 0;

        listaJugadores.leerListaJugadores("jugadores.txt");

        opcion = menu();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    listaJugadores.add(aniadirJugador());
                    break;
                case 2:
                    for (Jugador jugador : listaJugadores) {
                        verJugadores(jugador);
                    }
                    break;
                case 3:
                    Jugador j = buscarJugador();
                    if (j != null) {
                        verJugadores(j);
                    } else {
                        System.out.println("Este jugador no existe");
                    }
            }
            opcion = menu();
        }

        listaJugadores.escribirListaJugadores("jugadores.txt");

    }
}