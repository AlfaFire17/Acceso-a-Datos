import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
                Connection conn = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
                    String user = "Pablo";
                    String password = "ciclo";
                    conn = DriverManager.getConnection(url, user, password);
                    System.out.println("Conexion establecida");
                } catch (ClassNotFoundException e) {
                    System.out.println("No se pudo cargar el comtrolador.");
                } catch (SQLException e) {
                    System.out.println("Error al conectar la BD.");
                }
                finally {
                    if (conn != null) conn.close();
                }
    }
}