package model.bo;

import model.dao.EstoqueDAO;
import model.dao.IngredienteDAO;
import model.vo.Estoque;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EstoqueBO {

    public static void CriarEstoque(Estoque estoque) throws SQLException, ClassNotFoundException {


        String ingrediente = estoque.getEstoqueingrediente().getNome();
        System.out.println(ingrediente);
        int id = IngredienteDAO.GetID_Ingrediente(ingrediente);
        EstoqueDAO.CadastrarEstoque(estoque, id);

    }

    public static Map<Integer, Estoque> ListarEstoque() throws SQLException, ClassNotFoundException {

        Map<Integer, Estoque> listaEstoque = new HashMap<Integer, Estoque>();

        listaEstoque = EstoqueDAO.ListarEstoque();
        return listaEstoque;
    }

    public static void ManipularEstoque(Estoque estoque, Integer id_estoque) throws SQLException, ClassNotFoundException {
        System.out.println("Chegou no Problema (Maniopular Estoque)");
        System.out.println("Quantidade: " + estoque.getQuantidade());
        System.out.println("ID_Estoque " + id_estoque);

        EstoqueDAO.AlterarQuantEstoque(estoque, id_estoque);
    }

    public static void deletarEstoque(Integer id_estoque) throws SQLException, ClassNotFoundException {
        EstoqueDAO.DeletarEstoque(id_estoque);
    }
}
