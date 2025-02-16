package model.dao;

import model.vo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Pedido_has_ComidaDAO {

    public static void CadastrarPedido_has_Comida(Integer id_pedido, Integer id_comida, Integer quantidade) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into pedido_has_comida (Comida_ID_Comida, Pedido_ID_Pedido, Quantidade)"
                + "   values (?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id_comida);
        stmt.setInt(2, id_pedido);
        stmt.setInt(3, quantidade);


        stmt.execute();
        stmt.close();
        conexao.close();
    }
}
