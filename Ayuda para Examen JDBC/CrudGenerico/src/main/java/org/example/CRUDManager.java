package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDManager {

    // Método para insertar un nuevo registro
    public static void insert(String nombre, int edad) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            String query = "INSERT INTO tu_tabla (nombre, edad) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, edad);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionBD.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener todos los registros
    public static List<Persona> getAll() {
        List<Persona> personas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            String query = "SELECT * FROM tu_tabla";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int edad = resultSet.getInt("edad");
                    personas.add(new Persona(id, nombre, edad));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionBD.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    // Método para actualizar un registro
    public static void update(int id, String nombre, int edad) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            String query = "UPDATE tu_tabla SET nombre = ?, edad = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, edad);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionBD.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para eliminar un registro
    public static void delete(int id) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            String query = "DELETE FROM tu_tabla WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConexionBD.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

