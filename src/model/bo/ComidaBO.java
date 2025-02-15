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


    public static Map<Integer, Ingrediente> SelecionarIngredientes() throws SQLException, ClassNotFoundException {
        Map<Integer, Ingrediente> listaIngredientes = new HashMap<Integer, Ingrediente>();
        System.out.println("ComidaBO.SelecionarIngredientes");
        listaIngredientes = IngredienteDAO.ListaIngredienteMap();
        return listaIngredientes;
    }

    public static void CadastrarComida(Comida comida, Map<Integer, Integer> ingredientes) throws SQLException, ClassNotFoundException {

        System.out.println("Chegou na função ComidaBO.CadastrarComida");

        System.out.println("Comida: " + comida.getNome() + " "  + comida.getPreco() + " " + comida.getDescricao());

        ComidaDAO.CadastrarComida(comida);
        System.out.println("Passou de ComidaDAO.CadastrarComida");
        Integer id = ComidaDAO.GetID_Comida(comida);
        System.out.println("Passou de ComidaDAO.GetID_Comida");

        ingredientes.forEach((key, value) -> {
            try {
                Comida_has_IngredienteDAO.CadastrarIngredientesComida(id, key, value);
                System.out.println("Passou de Comidahasingrediente.DAO.CadastrarIngredientesComida");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
