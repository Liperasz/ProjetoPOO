package model.dao;

import model.vo.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidoDAO {

    public static void CadastrarPedido(Pedido pedido) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "insert into pedido (Valor, Entregue, Horario_Pedido, Pago, Cliente_ID_Cliente, Atendente_ID_Atendente) " +
                "values (?,?,?,?,?,?)";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, pedido.getValor());
        stmt.setBoolean(2, pedido.isEntregue());
        System.out.println("Horario do pedido: " + Timestamp.valueOf(pedido.getMomentopedido()));
        stmt.setTimestamp(3, Timestamp.valueOf(pedido.getMomentopedido().withNano(0)));
        stmt.setBoolean(4, pedido.isPago());
        if (pedido.getCliente() != null) {
            stmt.setInt(5, pedido.getCliente().getId());
        } else {
            stmt.setNull(5, java.sql.Types.INTEGER);
        }
        if (pedido.getFuncionario() != null) {
            stmt.setInt(6, pedido.getFuncionario().getId());
        } else {
            stmt.setNull(6, java.sql.Types.INTEGER);
        }

        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static Integer GetID_Pedido(Pedido pedido) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "SELECT ID_Pedido FROM pedido WHERE Valor = ? AND Horario_Pedido = ? AND (Cliente_ID_Cliente = ? OR (? IS NULL AND Cliente_ID_Cliente IS NULL)) " +
                "AND (Atendente_ID_Atendente = ? OR (? IS NULL AND Atendente_ID_Atendente IS NULL))";

        PreparedStatement stmt;

        System.out.println("Valor: " + pedido.getValor());
        System.out.println("Horario do pedido: " + Timestamp.valueOf(pedido.getMomentopedido()));
        stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, pedido.getValor());
        stmt.setTimestamp(2, Timestamp.valueOf(pedido.getMomentopedido().withNano(0)));
        if (pedido.getCliente() != null) {
            stmt.setInt(3, pedido.getCliente().getId());
            stmt.setInt(4, pedido.getCliente().getId());

        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setNull(4, java.sql.Types.INTEGER);

        }
        if (pedido.getFuncionario() != null) {
            stmt.setInt(5, pedido.getFuncionario().getId());
            stmt.setInt(6, pedido.getFuncionario().getId());

        } else {
            stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.setNull(6, java.sql.Types.INTEGER);

        }
        ResultSet rs = stmt.executeQuery();

        Integer id = -1;

        if (rs.next()) {
            id = rs.getInt("ID_Pedido");
            rs.close();
            stmt.close();
            conexao.close();
            return id;
        }

        throw new SQLException("Pedido não encontrado!");
    }

    public static Map<Integer, Pedido> GetPedidosAtuais(LocalDate dia) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "SELECT * \n" +
                "FROM pedido \n" +
                "INNER JOIN pedido_has_comida ON pedido.ID_Pedido = pedido_has_comida.Pedido_ID_Pedido \n" +
                "INNER JOIN comida ON pedido_has_comida.Comida_ID_Comida = comida.ID_Comida \n" +
                "LEFT JOIN cliente ON pedido.Cliente_ID_Cliente = cliente.ID_Cliente \n" +
                "LEFT JOIN atendente ON pedido.Atendente_ID_Atendente = atendente.ID_Atendente \n" +
                "WHERE DATE(pedido.Horario_Pedido ) = ? \n" +
                "ORDER BY pedido.ID_Pedido;\n";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setDate(1, Date.valueOf(dia));
        ResultSet rs = stmt.executeQuery();

        Map<Integer, Pedido> pedidos = new HashMap<>();

        while (rs.next()) {
            Integer id = rs.getInt("ID_Pedido");

            Pedido pedido = pedidos.getOrDefault(id, new Pedido());

            if (!pedidos.containsKey(id)) {
                pedido.setId_pedido(id);
                pedido.setMomentopedido(rs.getTimestamp("Horario_Pedido").toLocalDateTime());
                pedido.setValor(rs.getDouble("Valor"));
                pedido.setEntregue(rs.getBoolean("Entregue"));
                pedido.setPago(rs.getBoolean("Pago"));

                int id_cliente = rs.getInt("Cliente_ID_Cliente");
                if (!rs.wasNull()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(id_cliente);
                    String nome_cliente = rs.getString("Nome_Cliente");
                    if (nome_cliente != null) {
                        cliente.setNome(nome_cliente);
                    }
                    pedido.setCliente(cliente);
                }

                int id_atendente = rs.getInt("Atendente_ID_Atendente");
                if (!rs.wasNull()) {
                    Atendente atendente = new Atendente();
                    atendente.setId(id_atendente);
                    String nome_atendente = rs.getString("Nome_Atendente");
                    if (nome_atendente != null) {
                        atendente.setNome(nome_atendente);
                    }
                    pedido.setFuncionario(atendente);
                }


                pedidos.put(id, pedido);

                pedido.setPedidos(new ArrayList<>());
            }

            Comida comida = new Comida();
            comida.setNome(rs.getString("Nome_Comida"));

            pedido.getPedidos().add(comida);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return pedidos;
    }

    public static void AlterarPedido(Integer id_pedido, Pedido pedido) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "update pedido set Valor = ?, Entregue = ?, Horario_Pedido = ?, Pago = ?, Cliente_ID_Cliente = ?, Atendente_ID_Atendente = ? where ID_Pedido = ?";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setDouble(1, pedido.getValor());
        stmt.setBoolean(2, pedido.isEntregue());
        stmt.setTimestamp(3, Timestamp.valueOf(pedido.getMomentopedido().withNano(0)));
        stmt.setBoolean(4, pedido.isPago());
        if (pedido.getCliente() != null) {
            stmt.setInt(5, pedido.getCliente().getId());
        } else {
            stmt.setNull(5, Types.NULL);
        }
        if (pedido.getFuncionario() != null) {
            stmt.setInt(6, pedido.getFuncionario().getId());
        } else {
            stmt.setNull(6, Types.NULL);
        }
        stmt.setInt(7, id_pedido);


        stmt.execute();
        stmt.close();
        conexao.close();
        System.out.println("Pedido Alterado com sucesso");
    }

}
