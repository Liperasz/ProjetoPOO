package model.dao;

import model.vo.Comida;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ComidaDAO {

    //Função que cadastra uma comida
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


    //Função que pega o id de uma comida
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

    //Função que retorna um mapa com todas as comidas
    public static Map<Integer, Comida> GetComidas() throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from comida";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Map<Integer, Comida> comidas = new HashMap<>();

        while (rs.next()) {
            Comida comida = new Comida();
            Integer id = rs.getInt("ID_Comida");
            String nome = rs.getString("Nome_Comida");
            Double preco = rs.getDouble("Preco");
            String descricao = rs.getString("Descricao");
            comida.setNome(nome);
            comida.setPreco(preco);
            comida.setDescricao(descricao);

            comidas.put(id, comida);
        }

        rs.close();
        stmt.close();
        conexao.close();
        return comidas;
    }

    //Função que retorna se uma comida ja existe
    public static Boolean ComidaExiste(Comida comida) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from comida where Nome_Comida = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, comida.getNome());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            rs.close();
            stmt.close();
            conexao.close();
            return true;
        }
        rs.close();
        stmt.close();
        conexao.close();
        return false;

    }
}
