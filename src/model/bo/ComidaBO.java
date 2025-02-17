package model.bo;

import model.dao.ComidaDAO;
import model.dao.Comida_has_IngredienteDAO;
import model.dao.IngredienteDAO;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ComidaBO {

    // Método responsável por obter uma lista de ingredientes a partir do banco de dados.
    public static Map<Integer, Ingrediente> SelecionarIngredientes() throws SQLException, ClassNotFoundException {
        Map<Integer, Ingrediente> listaIngredientes = new HashMap<Integer, Ingrediente>();
        listaIngredientes = IngredienteDAO.ListaIngredienteMap(); // Busca os ingredientes do banco utilizando DAO
        return listaIngredientes; // Retorna o mapa de ingredientes obtido
    }

    // Método responsável pelo cadastro de uma comida e sua associação com ingredientes.
    public static void CadastrarComida(Comida comida, Map<Integer, Integer> ingredientes) throws SQLException, ClassNotFoundException {

        // Verifica se a comida já está cadastrada no banco de dados.
        if (!ComidaDAO.ComidaExiste(comida)) {

            // Caso a comida não exista, cadastra a comida utilizando DAO.
            ComidaDAO.CadastrarComida(comida);

            // Obtém o ID da comida recém-cadastrada.
            Integer id = ComidaDAO.GetID_Comida(comida);

            // Associa os ingredientes à comida no banco de dados.
            ingredientes.forEach((key, value) -> {
                try {
                    // Chama o DAO para cadastrar cada ingrediente vinculado à comida.
                    Comida_has_IngredienteDAO.CadastrarIngredientesComida(id, key, value);
                } catch (SQLException e) {
                    // Tratamento de exceção para falhas de SQL
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    // Tratamento de exceção para falhas relacionadas a classes não encontradas
                    throw new RuntimeException(e);
                }
            });

        } else {
            // Lança uma exceção caso a comida já esteja cadastrada no banco de dados.
            throw new RuntimeException("Comida já existe");
        }
    }
}
