package model.bo;

import model.dao.EstoqueDAO;
import model.dao.IngredienteDAO;
import model.vo.Estoque;
import model.vo.Ingrediente;

import java.sql.SQLException;

public class EstoqueBO {

    public static void CriarEstoque(Estoque estoque) throws SQLException, ClassNotFoundException {


        String ingrediente = estoque.getEstoqueingrediente().getNome();
        System.out.println(ingrediente);
        int id = IngredienteDAO.GetID_Ingrediente(ingrediente);
        EstoqueDAO.CadastrarEstoque(estoque, id);

    }
}
