package br.com.sidtmcafe.database;

import br.com.sidtmcafe.interfaces.Constants;

import java.sql.*;

public class ConnectionFactory implements Constants {

    public static Connection getConnection() {

        try {
            Class.forName(BD_DRIVER);
            return DriverManager.getConnection(BD_URL, BD_USER, BD_PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    public static void closeConnection(Connection con) {

        try {
            if (con != null) con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {
            if (stmt != null) stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {
            if (rs != null) rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
