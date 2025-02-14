package model.dao;

import model.vo.Estoque;
import model.vo.Ingrediente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueDAO {


    public static void CadastrarEstoque(Estoque estoque, int ID_Ingrediente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into estoque (Validade, Quantidade, Lote, Ingrediente_ID_Ingrediente)"
                + "   values (?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setDate(1, Date.valueOf(estoque.getValidade()));
        stmt.setInt(2, estoque.getQuantidade());
        stmt.setInt(3, estoque.getLote());
        stmt.setInt(4, ID_Ingrediente);


        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static Map<Integer, Estoque> ListarEstoque() throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        Map<Integer, Estoque> listaEstoque = new HashMap<Integer, Estoque>();

        String sql = "select * from estoque inner join ingrediente where ID_Ingrediente = Ingrediente_ID_Ingrediente";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("ID_Estoque");
            LocalDate validade = rs.getDate("Validade").toLocalDate();
            int quantidade = rs.getInt("Quantidade");
            int lote = rs.getInt("Lote");
            String ingrediente = rs.getString("Nome_Ingrediente");

            Ingrediente i = new Ingrediente(ingrediente);
            Estoque estoque = new Estoque(i, validade, quantidade, lote);
            listaEstoque.put(id, estoque);
        }

        rs.close();
        stmt.close();
        conexao.close();
        return listaEstoque;
    }

    public static void AlterarQuantEstoque(Estoque estoque, int ID_Estoque) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "update estoque set Quantidade = ? where ID_Estoque = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, estoque.getQuantidade());
        stmt.setInt(2, ID_Estoque);


        stmt.execute();
        stmt.close();
        conexao.close();
        System.out.println("Estoque Alterado com sucesso!");
    }

    public static void DeletarEstoque(Integer ID_Estoque) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "delete from estoque where ID_Estoque = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, ID_Estoque);

        stmt.execute();
        stmt.close();
        conexao.close();
        System.out.println("Estoque Deletado com sucesso!");
    }
}
