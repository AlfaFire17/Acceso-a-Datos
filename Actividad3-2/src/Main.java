import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionDB.obtenerConexion();
             Statement statement = conexion.createStatement()) {

            // Lista el primer apellido de todos los empleados
            ResultSet resultSet = statement.executeQuery("SELECT apellido1 FROM empleado");
            while (resultSet.next()) {
                System.out.println("Primer apellido: " + resultSet.getString("apellido1"));
            }

            // Lista el primer apellido de los empleados eliminando los apellidos repetidos
            resultSet = statement.executeQuery("SELECT DISTINCT apellido1 FROM empleado");
            while (resultSet.next()) {
                System.out.println("Primer apellido (sin repetir): " + resultSet.getString("apellido1"));
            }

            // Devuelve una lista con el nombre y el gasto de los 2 departamentos que tienen menor gasto
            resultSet = statement.executeQuery("SELECT nombre, gastos FROM departamento ORDER BY gastos LIMIT 2");
            while (resultSet.next()) {
                System.out.println("Nombre del departamento: " + resultSet.getString("nombre") +
                        ", Gasto: " + resultSet.getDouble("gastos"));
            }

            // Devuelve una lista con el nombre y el presupuesto de los departamentos con presupuesto mayor o igual a 150000
            resultSet = statement.executeQuery("SELECT nombre, presupuesto FROM departamento WHERE presupuesto >= 150000");
            while (resultSet.next()) {
                System.out.println("Nombre del departamento: " + resultSet.getString("nombre") +
                        ", Presupuesto: " + resultSet.getDouble("presupuesto"));
            }

            // Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno
            resultSet = statement.executeQuery("SELECT e.*, d.nombre AS nombre_departamento " +
                    "FROM empleado e INNER JOIN departamento d ON e.id_departamento = d.id");
            while (resultSet.next()) {
                System.out.println("Empleado: " + resultSet.getString("nombre") + " " +
                        resultSet.getString("apellido1") + ", Departamento: " +
                        resultSet.getString("nombre_departamento"));
            }

            // Devuelve un listado con los empleados y los datos de los departamentos ordenados por nombre del departamento y apellidos de los empleados
            resultSet = statement.executeQuery("SELECT e.*, d.nombre AS nombre_departamento " +
                    "FROM empleado e INNER JOIN departamento d ON e.id_departamento = d.id " +
                    "ORDER BY nombre_departamento, apellido1, nombre");
            while (resultSet.next()) {
                System.out.println("Empleado: " + resultSet.getString("nombre") + " " +
                        resultSet.getString("apellido1") + ", Departamento: " +
                        resultSet.getString("nombre_departamento"));
            }

            // Devuelve un listado con el identificador y el nombre del departamento, solamente de aquellos departamentos que tienen empleados
            resultSet = statement.executeQuery("SELECT d.id, d.nombre FROM departamento d " +
                    "WHERE EXISTS (SELECT 1 FROM empleado e WHERE e.id_departamento = d.id)");
            while (resultSet.next()) {
                System.out.println("ID del departamento: " + resultSet.getInt("id") +
                        ", Nombre del departamento: " + resultSet.getString("nombre"));
            }

            // Devuelve el nombre del departamento donde trabaja el empleado con nif 38382980M
            PreparedStatement preparedStatement = conexion.prepareStatement(
                    "SELECT d.nombre FROM empleado e INNER JOIN departamento d ON e.id_departamento = d.id WHERE e.nif = ?");
            preparedStatement.setString(1, "38382980M");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Departamento del empleado: " + resultSet.getString("nombre"));
            }

            // Calcula la suma del presupuesto de todos los departamentos
            resultSet = statement.executeQuery("SELECT SUM(presupuesto) AS suma_presupuesto FROM departamento");
            while (resultSet.next()) {
                System.out.println("Suma del presupuesto de todos los departamentos: " +
                        resultSet.getDouble("suma_presupuesto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
-- Crea la tabla departamento
CREATE TABLE departamento (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  presupuesto DOUBLE PRECISION NOT NULL,
  gastos DOUBLE PRECISION NOT NULL
);

-- Crea la tabla empleado
CREATE TABLE empleado (
  id SERIAL PRIMARY KEY,
  nif VARCHAR(9) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellido1 VARCHAR(100) NOT NULL,
  apellido2 VARCHAR(100),
  id_departamento INT,
  FOREIGN KEY (id_departamento) REFERENCES departamento(id)
);
*/