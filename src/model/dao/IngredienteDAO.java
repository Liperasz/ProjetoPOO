package model.dao;

import model.vo.Cliente;
import model.vo.Ingrediente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {

    private static int ID_Ingrediente = 0;

    public static void CadastrarIngrediente(Ingrediente ingrediente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into ingrediente (ID_Ingrediente, Nome_Ingrediente)"
                + "   values (?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ID_Ingrediente);
        stmt.setString(2, ingrediente.getNome());


        stmt.execute();
        stmt.close();
        conexao.close();

        ID_Ingrediente++;
    }

    public static boolean IngredienteExiste(Ingrediente ingrediente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from ingrediente where Nome_Ingrediente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, ingrediente.getNome());

        ResultSet rs = stmt.executeQuery();
        boolean existe = rs.next();

        rs.close();
        stmt.close();
        conexao.close();
        return existe;
    }

    public static List<String> ListaIngrediente() throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        List<String> listaIngrediente = new ArrayList<String>();

        String sql = "select * from ingrediente";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            String i = rs.getString("Nome_Ingrediente");
            listaIngrediente.add(i);
        }

        rs.close();
        stmt.close();
        conexao.close();
        return listaIngrediente;
    }

    public static int GetID_Ingrediente(String nomeingrediente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "SELECT ID_Ingrediente FROM Ingrediente WHERE Nome_Ingrediente = ?";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, nomeingrediente);
        ResultSet rs = stmt.executeQuery();

        Integer id = -1;

        if (rs.next()) {
            id = rs.getInt("ID_Ingrediente");
            rs.close();
            stmt.close();
            conexao.close();
            return id;
        }

        throw new SQLException("Ingrediente não encontrado!");
    }
}
