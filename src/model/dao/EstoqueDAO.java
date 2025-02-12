package model.dao;

import model.vo.Estoque;
import model.vo.Ingrediente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstoqueDAO {

    private static int ID_Estoque = 0;

    public static void CadastrarEstoque(Estoque estoque, int ID_Ingrediente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into estoque (ID_Estoque, Validade, Quantidade, Lote, Ingrediente_ID_Ingrediente)"
                + "   values (?, ?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ID_Estoque);
        stmt.setDate(2, Date.valueOf(estoque.getValidade()));
        stmt.setInt(3, estoque.getQuantidade());
        stmt.setInt(4, estoque.getLote());
        stmt.setInt(5, ID_Ingrediente);


        stmt.execute();
        stmt.close();
        conexao.close();

        ID_Estoque++;
    }
}
