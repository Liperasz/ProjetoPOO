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
}
