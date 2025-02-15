package model.dao;

import model.vo.Comida;

import java.sql.*;

public class ComidaDAO {


    public static void CadastrarComida(Comida comida) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into comida (Preco, Descricao, Nome_Comida)"
                + "   values (?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setDouble(1, comida.getPreco());
        stmt.setString(2, comida.getDescricao());
        stmt.setString(3, comida.getNome());


        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static int GetID_Comida(Comida comida) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "SELECT ID_Comida FROM comida WHERE Nome_Comida = ? and Preco = ? and Descricao = ?";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, comida.getNome());
        stmt.setDouble(2, comida.getPreco());
        stmt.setString(3, comida.getDescricao());
        ResultSet rs = stmt.executeQuery();

        Integer id = -1;

        if (rs.next()) {
        id = rs.getInt("ID_Comida");
            rs.close();
            stmt.close();
            conexao.close();
            return id;
        }

        throw new SQLException("Comida não encontrada!");
    }
}
