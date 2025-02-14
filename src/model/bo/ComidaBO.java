package model.bo;

import model.dao.ComidaDAO;
import model.dao.IngredienteDAO;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ComidaBO {

    public static Map<Integer, Ingrediente> SelecionarIngredientes() throws SQLException, ClassNotFoundException {
        Map<Integer, Ingrediente> listaIngredientes = new HashMap<Integer, Ingrediente>();
        listaIngredientes = IngredienteDAO.ListaIngredienteMap();
        return listaIngredientes;
    }

    public static void CadastrarComida(Comida comida, Integer id_ingrediente) throws SQLException, ClassNotFoundException {

        System.out.println("Ingredientes");
        System.out.println(comida.getIngredientes());

    }
}
