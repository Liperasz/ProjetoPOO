package model.dao;

import model.vo.Atendente;
import model.vo.Cliente;

import java.sql.*;

public class AtendenteDAO {

    private static int ID_Atendente = 0;

    public static void CadastrarAtendente(Atendente atendente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into atendente (ID_Atendente, Nome_Atendente, Nascimento_Atendente, Email_Atendente, Senha_Atendente, Sexo_Atendente, CPF_Atendente, Telefone_Atendente, Status_Atendente)"
                + "   values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ID_Atendente);
        stmt.setString(2, atendente.getNome());
        stmt.setDate(3, Date.valueOf(atendente.getNascimento()));
        stmt.setString(4, atendente.getEmail());
        stmt.setString(5, atendente.getSenha());
        stmt.setString(6, atendente.getSexoString());
        stmt.setString(7, atendente.getCpf());
        stmt.setString(8, atendente.getTelefone());
        stmt.setString(9, atendente.getStatus().toString());


        stmt.execute();
        stmt.close();
        conexao.close();

        ID_Atendente++;
    }

    public static boolean AtendenteExiste(Atendente atendente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from atendente where CPF_Atendente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, atendente.getCpf());

        ResultSet rs = stmt.executeQuery();
        boolean existe = rs.next();

        rs.close();
        stmt.close();
        conexao.close();
        return existe;
    }

    public static boolean AtendenteLogin(String email, String senha) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from atendente where Email_Atendente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        boolean existe = rs.next();

        if (existe) {

            String senha_real = rs.getString("Senha_Atendente");

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
