package model.dao;

import model.vo.Pedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public static ArrayList<String> ListarMaisVendasMes(LocalDate mes) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "SELECT c.Nome_Comida, SUM(phc.Quantidade) AS Total_Quantidade, SUM(phc.Quantidade * c.Preco) AS Total_Valor " +
                "FROM Restaurante.Pedido_has_Comida phc " +
                "JOIN Restaurante.Comida c ON phc.Comida_ID_Comida = c.ID_Comida " +
                "JOIN Restaurante.Pedido p ON phc.Pedido_ID_Pedido = p.ID_Pedido " +
                "WHERE MONTH(p.Horario_Pedido) = ? " +
                "AND YEAR(p.Horario_Pedido) = ? " +
                "GROUP BY c.Nome_Comida " +
                "ORDER BY Total_Quantidade DESC;";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, mes.getMonthValue());
        stmt.setInt(2, mes.getYear());

        ResultSet rs = stmt.executeQuery();

        ArrayList<String> lista = new ArrayList<>();
        while (rs.next()) {
            String string = new String();
            string = "Comida: " + rs.getString("Nome_Comida") + " | Quantidade vendida: " + rs.getString("Total_Quantidade") + " | Valor total das vendas: " + rs.getString("Total_Valor");
            lista.add(string);
        }


        stmt.close();
        conexao.close();
        return lista;
    }

    public static ArrayList<String> ListarMenosVendasMes(LocalDate mes) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "SELECT c.Nome_Comida, SUM(phc.Quantidade) AS Total_Quantidade, SUM(phc.Quantidade * c.Preco) AS Total_Valor " +
                "FROM Restaurante.Pedido_has_Comida phc " +
                "JOIN Restaurante.Comida c ON phc.Comida_ID_Comida = c.ID_Comida " +
                "JOIN Restaurante.Pedido p ON phc.Pedido_ID_Pedido = p.ID_Pedido " +
                "WHERE MONTH(p.Horario_Pedido) = ? " +
                "AND YEAR(p.Horario_Pedido) = ? " +
                "GROUP BY c.Nome_Comida " +
                "ORDER BY Total_Quantidade ASC;";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, mes.getMonthValue());
        stmt.setInt(2, mes.getYear());

        ResultSet rs = stmt.executeQuery();

        ArrayList<String> lista = new ArrayList<>();
        while (rs.next()) {
            String string = new String();
            string = "Comida: " + rs.getString("Nome_Comida") + " | Quantidade vendida: " + rs.getString("Total_Quantidade") + " | Valor total das vendas: " + rs.getString("Total_Valor");
            lista.add(string);
        }


        stmt.close();
        conexao.close();
        return lista;
    }

    public static ArrayList<String> ListarMaisVendasAno(LocalDate ano) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "SELECT c.Nome_Comida, SUM(phc.Quantidade) AS Total_Quantidade, SUM(phc.Quantidade * c.Preco) AS Total_Valor " +
                "FROM Restaurante.Pedido_has_Comida phc " +
                "JOIN Restaurante.Comida c ON phc.Comida_ID_Comida = c.ID_Comida " +
                "JOIN Restaurante.Pedido p ON phc.Pedido_ID_Pedido = p.ID_Pedido " +
                "WHERE YEAR(p.Horario_Pedido) = ? " +
                "GROUP BY c.Nome_Comida " +
                "ORDER BY Total_Quantidade DESC;";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ano.getYear());

        ResultSet rs = stmt.executeQuery();

        ArrayList<String> lista = new ArrayList<>();
        while (rs.next()) {
            String string = new String();
            string = "Comida: " + rs.getString("Nome_Comida") + " | Quantidade vendida: " + rs.getString("Total_Quantidade") + " | Valor total das vendas: " + rs.getString("Total_Valor");
            lista.add(string);
        }


        stmt.close();
        conexao.close();
        return lista;
    }

    public static ArrayList<String> ListarMenosVendasAno(LocalDate ano) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "SELECT c.Nome_Comida, SUM(phc.Quantidade) AS Total_Quantidade, SUM(phc.Quantidade * c.Preco) AS Total_Valor " +
                "FROM Restaurante.Pedido_has_Comida phc " +
                "JOIN Restaurante.Comida c ON phc.Comida_ID_Comida = c.ID_Comida " +
                "JOIN Restaurante.Pedido p ON phc.Pedido_ID_Pedido = p.ID_Pedido " +
                "WHERE YEAR(p.Horario_Pedido) = ? " +
                "GROUP BY c.Nome_Comida " +
                "ORDER BY Total_Quantidade ASC;";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ano.getYear());

        ResultSet rs = stmt.executeQuery();

        ArrayList<String> lista = new ArrayList<>();
        while (rs.next()) {
            String string = new String();
            string = "Comida: " + rs.getString("Nome_Comida") + " | Quantidade vendida: " + rs.getString("Total_Quantidade") + " | Valor total das vendas: " + rs.getString("Total_Valor");
            lista.add(string);
        }


        stmt.close();
        conexao.close();
        return lista;
    }
}
