package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO {

    //Função que verifica o login do adm
    public static boolean AdministradorLogin(String email, String senha) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from administrador where Email_Administrador = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        boolean existe = rs.next();

        if (existe) {

            String senha_real = rs.getString("Senha_Administrador");

            if (senha.equals(senha_real)) {

                rs.close();
                stmt.close();
                conexao.close();
                return true;
            }

        }


        rs.close();
        stmt.close();
        conexao.close();
        return false;
    }
}
