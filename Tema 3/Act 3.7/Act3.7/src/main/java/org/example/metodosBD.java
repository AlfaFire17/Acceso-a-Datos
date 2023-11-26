package org.example;

import java.sql.*;

public class metodosBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/Empleados";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ciclo";
    private Connection connection;
    private Statement statement;

    public void conexionBD() {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
                this.statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void queryBD(String sql, String mensaje) {
        conexionBD();
        try {
            ResultSet rs = this.statement.executeQuery(sql);
            System.out.println(mensaje);

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            cerrarBD(statement,rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing database query", e);
        }
    }

    public void cerrarBD(Statement conn, ResultSet rs) {
        try {
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}