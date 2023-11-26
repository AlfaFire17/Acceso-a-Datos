package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String password = "ciclo";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // 1. Añadir columna 'Horas' a la tabla 'Asignaturas'
            String anyadirHorasSQL = "ALTER TABLE Asignaturas ADD COLUMN IF NOT EXISTS Horas INT";
            statement.executeUpdate(anyadirHorasSQL);

            // 2. Usar PreparedStatement para permitir al usuario introducir asignaturas
            Scanner scanner = new Scanner(System.in);
            String insertAsignaturaSQL = "INSERT INTO Asignaturas (Codigo, Nombre, Horas) VALUES (?, ?, ?)";
            System.out.println("Introduce asignaturas. Para finalizar, introduce un código nulo.");
            while (true) {
                System.out.print("Código (o nulo para finalizar): ");
                String codigo = scanner.nextLine();
                if (codigo.equalsIgnoreCase("nulo")) {
                    break;
                }

                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Horas: ");
                int horas = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después del entero

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertAsignaturaSQL)) {
                    preparedStatement.setString(1, codigo);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setInt(3, horas);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 3. Crear tabla 'cursos' y agregar columna 'curso' a 'asignaturas'
            String createCursosTableSQL = "CREATE TABLE Cursos (Código SERIAL, Nombre VARCHAR(90))";
            statement.executeUpdate(createCursosTableSQL);

            String insertCursosSQL = "INSERT INTO Cursos (Nombre) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertCursosSQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, "Desarrollo de aplicaciones multiplataforma");
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int cursoId = generatedKeys.getInt(1);
                    // Agregar columna 'curso' a 'asignaturas'
                    String alterAsignaturasTableSQL = "ALTER TABLE Asignaturas ADD COLUMN Curso INT";
                    statement.executeUpdate(alterAsignaturasTableSQL);
                    // Establecer la relación de clave ajena
                    String updateAsignaturasCursoSQL = "UPDATE Asignaturas SET Curso = ? WHERE Código = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateAsignaturasCursoSQL)) {
                        updateStatement.setInt(1, cursoId);
                        updateStatement.setString(2, "código_asignatura_a_actualizar");
                        // Actualiza el código de la asignatura que corresponda
                        updateStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
