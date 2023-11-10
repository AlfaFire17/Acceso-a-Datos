import java.sql.*;

public class EmpleadoDAO {
    public static void crearEmpleado(String nif, String nombre, String apellido1, String apellido2, int idDepartamento) {
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement pst = conexion.prepareStatement(
                     "INSERT INTO empleado (nif, nombre, apellido1, apellido2, id_departamento) VALUES (?, ?, ?, ?, ?)")) {

            pst.setString(1, nif);
            pst.setString(2, nombre);
            pst.setString(3, apellido1);
            pst.setString(4, apellido2);
            pst.setInt(5, idDepartamento);

            pst.executeUpdate();
            System.out.println("Empleado creado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agrega métodos para leer, actualizar y eliminar empleados según tus necesidades.
}
