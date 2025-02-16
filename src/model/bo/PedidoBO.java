package model.bo;

import controller.TrocadorTelas;
import controller.Usuario;
import model.dao.*;
import model.vo.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PedidoBO {

    public static Double valor = 0.0;

    public static Map<Integer, Comida> ListarComidas() throws SQLException, ClassNotFoundException {

        Map<Integer, Comida> comidas = new HashMap<Integer, Comida>();
        comidas = ComidaDAO.GetComidas();
        return comidas;
    }

    public static Atendente BuscarAtendentePedido(String email) throws SQLException, ClassNotFoundException {

        Atendente pessoa = new Atendente();
        pessoa = AtendenteDAO.BuscarAtendente(email);
        return pessoa;
    }

    public static Cliente BuscarClientePedido(String email) throws SQLException, ClassNotFoundException {

        Cliente pessoa = new Cliente();
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

    public static Map<Integer, Pedido> BuscarPedidos () throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        LocalDate dia = LocalDate.now();
        pedidos = PedidoDAO.GetPedidosAtuais(dia);
        return pedidos;
    }

    public static void AlterarStatusPedido (Integer id_pedido, Pedido pedido) throws SQLException, ClassNotFoundException {
        PedidoDAO.AlterarPedido(id_pedido, pedido);

    }

    public static Double getValor() {
        return valor;
    }

    public static void setValor(Double valor) {
        PedidoBO.valor = valor;
    }

}
