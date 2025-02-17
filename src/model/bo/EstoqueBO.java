package model.bo;

import model.dao.EstoqueDAO;
import model.dao.IngredienteDAO;
import model.vo.Estoque;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueBO {

    // Método para criar um estoque a partir de um objeto Estoque.
    // Obtém o nome do ingrediente, busca seu ID no banco através do IngredienteDAO,
    // e utiliza o EstoqueDAO para cadastrar o estoque.
    public static void CriarEstoque(Estoque estoque) throws SQLException, ClassNotFoundException {
        String ingrediente = estoque.getEstoqueingrediente().getNome();
        int id = IngredienteDAO.GetID_Ingrediente(ingrediente);
        EstoqueDAO.CadastrarEstoque(estoque, id);
    }

    // Método que lista os estoques cadastrados.
    // Faz uso do EstoqueDAO para retornar um Map de estoques.
    public static Map<Integer, Estoque> ListarEstoque() throws SQLException, ClassNotFoundException {
        Map<Integer, Estoque> listaEstoque = new HashMap<Integer, Estoque>();
        listaEstoque = EstoqueDAO.ListarEstoque();
        return listaEstoque;
    }

    // Método para alterar a quantidade de um estoque específico.
    // Utiliza o EstoqueDAO para realizar a operação indicando o ID do estoque.
    public static void ManipularEstoque(Estoque estoque, Integer id_estoque) throws SQLException, ClassNotFoundException {
        EstoqueDAO.AlterarQuantEstoque(estoque, id_estoque);
    }

    // Método para deletar um estoque com base no ID.
    // Utiliza o EstoqueDAO para realizar a operação.
    public static void deletarEstoque(Integer id_estoque) throws SQLException, ClassNotFoundException {
        EstoqueDAO.DeletarEstoque(id_estoque);
    }

    // Método que lista todos os nomes de ingredientes cadastrados.
    // Faz uso do IngredienteDAO para retornar uma lista de Strings.
    public static List<String> ListarIngrediente() throws SQLException, ClassNotFoundException {
        return IngredienteDAO.ListaIngrediente();
    }
}