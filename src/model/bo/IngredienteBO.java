package model.bo;

import model.dao.IngredienteDAO;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public class IngredienteBO {

    //Função pra criar ingrediente
    public static void CriarIngrediente(Ingrediente ingrediente) throws SQLException, ClassNotFoundException {

        //Se o ingrediente não existe, ele cria o ingrediente
        if (!IngredienteDAO.IngredienteExiste(ingrediente)) {
            IngredienteDAO.CadastrarIngrediente(ingrediente);
        }
    }

}
