import java.sql.*;

public class DepartamentoDAO {
    public static void crearDepartamento(String nombre, double presupuesto, double gastos) {
        try (Connection conexion = ConexionDB.obtenerConexion();
             PreparedStatement pst = conexion.prepareStatement(
                     "INSERT INTO departamento (nombre, presupuesto, gastos) VALUES (?, ?, ?)")) {

            pst.setString(1, nombre);
            pst.setDouble(2, presupuesto);
            pst.setDouble(3, gastos);

            pst.executeUpdate();
            System.out.println("Departamento creado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agrega métodos para leer, actualizar y eliminar departamentos según tus necesidades.
}
