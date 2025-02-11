package model.dao;

import java.sql.*;

public class ConexionJDBC {

    public static Connection getConexion() throws ClassNotFoundException, SQLException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conexão com o banco de dados");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/restaurante", "root", "uhsxtm65");
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}