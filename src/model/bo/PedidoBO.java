package model.bo;

import controller.TrocadorTelas;
import controller.Usuario;
import model.dao.*;
import model.vo.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidoBO {

    // Variável estática que mantém o valor atual associado ao pedido
    public static Double valor = 0.0;

    // Retorna uma lista de comidas disponíveis, obtendo os dados do DAO
    public static Map<Integer, Comida> ListarComidas() throws SQLException, ClassNotFoundException {
        Map<Integer, Comida> comidas = new HashMap<>();
        comidas = ComidaDAO.GetComidas();
        return comidas;
    }

    // Busca um atendente pelo email
    public static Atendente BuscarAtendentePedido(String email) throws SQLException, ClassNotFoundException {
        Atendente pessoa = new Atendente();
        pessoa = AtendenteDAO.BuscarAtendente(email);
        return pessoa;
    }

    // Busca um cliente pelo email
    public static Cliente BuscarClientePedido(String email) throws SQLException, ClassNotFoundException {
        Cliente pessoa = new Cliente();
        pessoa = ClienteDAO.BuscarCliente(email);
        return pessoa;
    }

    // Registra um novo pedido e associa os itens de comida ao pedido
    public static void CadastrarPedido(Pedido pedido, Map<Integer, Integer> comidas) throws SQLException, ClassNotFoundException {
        PedidoDAO.CadastrarPedido(pedido); // Cadastra o pedido no banco de dados
        Integer id = PedidoDAO.GetID_Pedido(pedido); // Obtém o ID do pedido recém-cadastrado

        // Associa cada comida (ID e quantidade) ao pedido no banco de dados
        comidas.forEach((k, v) -> {
            try {
                Pedido_has_ComidaDAO.CadastrarPedido_has_Comida(id, k, v);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Busca os pedidos atuais do dia
    public static Map<Integer, Pedido> BuscarPedidos() throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        LocalDate dia = LocalDate.now(); // Obtém a data atual
        pedidos = PedidoDAO.GetPedidosAtuais(dia);
        return pedidos;
    }

    // Altera o status de um pedido com base no ID e em um objeto Pedido atualizado
    public static void AlterarStatusPedido(Integer id_pedido, Pedido pedido) throws SQLException, ClassNotFoundException {
        PedidoDAO.AlterarPedido(id_pedido, pedido);
    }

    // Recupera o valor estático associado ao pedido
    public static Double getValor() {
        return valor;
    }

    // Define o valor estático associado ao pedido
    public static void setValor(Double valor) {
        PedidoBO.valor = valor;
    }

    // Lista o histórico de pedidos de um cliente logado
    public static Map<Integer, Pedido> ListarPedidosCliente() throws SQLException, ClassNotFoundException {
        Cliente cliente = ClienteDAO.BuscarCliente(ClienteBO.getEmailLogado()); // Obtém o cliente logado pelo email
        Map<Integer, Pedido> pedidos = new HashMap<>();
        pedidos = PedidoDAO.GetHistoricoPedidosCliente(cliente);
        return pedidos;
    }

    // Lista diversos relatórios relacionados a vendas
    public static ArrayList<ArrayList<String>> ListarRelatorios() throws SQLException, ClassNotFoundException {
        ArrayList<ArrayList<String>> relatorios = new ArrayList<>();
        // Obtém as comidas mais e menos vendidas no ano e no mês atual
        ArrayList<String> maisVendasAno = Pedido_has_ComidaDAO.ListarMaisVendasAno(LocalDate.now());
        ArrayList<String> maisVendasMes = Pedido_has_ComidaDAO.ListarMaisVendasMes(LocalDate.now());
        ArrayList<String> menosVendasAno = Pedido_has_ComidaDAO.ListarMenosVendasAno(LocalDate.now());
        ArrayList<String> menosVendasMes = Pedido_has_ComidaDAO.ListarMenosVendasMes(LocalDate.now());
        // Adiciona os relatórios à lista final
        relatorios.add(maisVendasMes);
        relatorios.add(maisVendasAno);
        relatorios.add(menosVendasMes);
        relatorios.add(menosVendasAno);
        return relatorios;
    }

    // Busca o histórico completo de pedidos
    public static Map<Integer, Pedido> BuscarHistoricoPedido() throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        pedidos = PedidoDAO.getHistoricoPedidos();
        return pedidos;
    }

    // Busca o histórico de pedidos que incluem uma comida específica
    public static Map<Integer, Pedido> BuscarHistoricoPedidoComidas(String comida) throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        pedidos = PedidoDAO.GetHistoricoPedidosComida(comida);
        return pedidos;
    }

    // Busca o histórico de pedidos relacionados a um cliente específico
    public static Map<Integer, Pedido> BuscarHistoricoPedidoCliente(String cliente) throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        pedidos = PedidoDAO.GetHistoricoPedidosCliente(cliente);
        return pedidos;
    }

    // Busca o histórico de pedidos relacionados a um atendente específico
    public static Map<Integer, Pedido> BuscarHistoricoPedidoAtendente(String atendente) throws SQLException, ClassNotFoundException {
        Map<Integer, Pedido> pedidos = new HashMap<>();
        pedidos = PedidoDAO.GetHistoricoPedidosAtendente(atendente);
        return pedidos;
    }
}
