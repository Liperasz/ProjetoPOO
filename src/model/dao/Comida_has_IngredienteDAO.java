package model.dao;

import model.vo.Comida;
import model.vo.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Comida_has_IngredienteDAO {

    public static void CadastrarIngredientesComida(Comida comida, Map<Integer, Ingrediente> ListaIngredientes) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into comida ()"
                + "   values (?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);




        stmt.execute();
        stmt.close();
        conexao.close();


    }
}
