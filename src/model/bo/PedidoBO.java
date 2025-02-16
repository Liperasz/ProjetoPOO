package model.bo;

import controller.TrocadorTelas;
import controller.Usuario;
import model.dao.*;
import model.vo.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PedidoBO {

    public static Map<Integer, Comida> ListarComidas() throws SQLException, ClassNotFoundException {

        Map<Integer, Comida> comidas = new HashMap<Integer, Comida>();
        comidas = ComidaDAO.GetComidas();
        return comidas;
    }

    public static Atendente BuscarAtendentePedido(String email) throws SQLException, ClassNotFoundException {

        Atendente pessoa = null;
        pessoa = AtendenteDAO.BuscarAtendente(email);
        return pessoa;
    }

    public static Cliente BuscarClientePedido(String email) throws SQLException, ClassNotFoundException {

        Cliente pessoa = null;
        pessoa = ClienteDAO.BuscarCliente(email);
        return pessoa;
    }

    public static void CadastrarPedido (Pedido pedido, Map<Integer, Integer> comidas) throws SQLException, ClassNotFoundException {
        PedidoDAO.CadastrarPedido(pedido);
        Integer id = PedidoDAO.GetID_Pedido(pedido);

        comidas.forEach( (k, v) -> {

            try {
                Pedido_has_ComidaDAO.CadastrarPedido_has_Comida(id, k, v);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
