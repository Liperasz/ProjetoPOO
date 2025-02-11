package model.dao;

import model.vo.Cliente;
import model.vo.Sexo;

import java.sql.*;
import java.time.LocalDate;

public class ClienteDAO {

    private static int ID_Cliente = 0;


    public static void CadastrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into cliente (ID_Cliente, Nome_Cliente, Nascimento_Cliente, Email_Cliente, Senha_Cliente, Sexo_Cliente, CPF_Cliente, Telefone_Cliente)"
                + "   values (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, ID_Cliente);
        stmt.setString(2, cliente.getNome());
        stmt.setDate(3, Date.valueOf(cliente.getNascimento()));
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, cliente.getSenha());
        stmt.setString(6, cliente.getSexoString());
        stmt.setString(7, cliente.getCpf());
        stmt.setString(8, cliente.getTelefone());


        stmt.execute();
        stmt.close();
        conexao.close();

        ID_Cliente++;
    }

    public static boolean ClienteExiste(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from cliente where CPF_Cliente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getCpf());

        ResultSet rs = stmt.executeQuery();
        boolean existe = rs.next();

        rs.close();
        stmt.close();
        conexao.close();
        return existe;
    }



}
