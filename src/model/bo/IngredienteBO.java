package model.bo;

import model.dao.IngredienteDAO;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public class IngredienteBO {

    public static void CriarIngrediente(Ingrediente ingrediente) throws SQLException, ClassNotFoundException {

        if (!IngredienteDAO.IngredienteExiste(ingrediente)) {
            IngredienteDAO.CadastrarIngrediente(ingrediente);
        }
    }

    public static List<String> ListarIngrediente() throws SQLException, ClassNotFoundException {

        return IngredienteDAO.ListaIngrediente();
    }

}
