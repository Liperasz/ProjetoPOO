package model.dao;

import model.vo.Comida;
import model.vo.Pedido;

import java.sql.*;
import java.time.LocalDate;

public class PedidoDAO {

    public static void CadastrarPedido(Pedido pedido) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "insert into pedido (Valor, Entregue, Horario_Pedido, Pago, Cliente_ID_Cliente, Atendente_ID_Atendente) " +
                "values (?,?,?,?,?,?)";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, pedido.getValor());
        stmt.setBoolean(2, pedido.isEntregue());
        stmt.setTimestamp(3, Timestamp.valueOf(pedido.getMomentopedido()));
        stmt.setBoolean(4, pedido.isPago());
        stmt.setInt(5, pedido.getCliente().getId());
        stmt.setInt(6, pedido.getFuncionario().getId());

        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static int GetID_Pedido(Pedido pedido) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "SELECT ID_Pedido FROM pedido WHERE Valor = ? and Horario_Pedido = ? and Cliente_ID_Cliente = ? and Atendente_ID_Atendente = ?";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, pedido.getValor());
        stmt.setTimestamp(2, Timestamp.valueOf(pedido.getMomentopedido()));
        stmt.setInt(3, pedido.getCliente().getId());
        stmt.setInt(4, pedido.getFuncionario().getId());
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
}
