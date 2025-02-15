package model.dao;

import model.vo.Comida;
import model.vo.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Comida_has_IngredienteDAO {

    public static void CadastrarIngredientesComida(Integer id_comida, Integer id_ingrediente, Integer quantidade) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into comida_has_ingrediente (Comida_ID_Comida, Ingrediente_ID_Ingrediente, Quantidade)"
                + "   values (?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id_comida);
        stmt.setInt(2, id_ingrediente);
        stmt.setInt(3, quantidade);


        stmt.execute();
        stmt.close();
        conexao.close();
    }
}
