package model.dao;

import model.vo.Cliente;
import model.vo.Pessoa;
import model.vo.Sexo;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClienteDAO {

    public static void CadastrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into cliente (Nome_Cliente, Nascimento_Cliente, Email_Cliente, Senha_Cliente, Sexo_Cliente, CPF_Cliente, Telefone_Cliente)"
                + "   values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setString(1, cliente.getNome());
        stmt.setDate(2, Date.valueOf(cliente.getNascimento()));
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getSenha());
        stmt.setString(5, cliente.getSexoString());
        stmt.setString(6, cliente.getCpf());
        stmt.setString(7, cliente.getTelefone());


        stmt.execute();
        stmt.close();
        conexao.close();

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

    public static boolean ClienteLogin(String email, String senha) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from cliente where Email_Cliente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        boolean existe = rs.next();

        if (existe) {

            String senha_real = rs.getString("Senha_Cliente");

            if (senha.equals(senha_real)) {

                rs.close();
                stmt.close();
                conexao.close();
                return true;
            }

        }


        rs.close();
        stmt.close();
        conexao.close();
        return false;
    }

    public static Cliente BuscarCliente(String email) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from cliente where Email_Cliente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        Cliente cliente = new Cliente();

        if (rs.next()) {

            Integer id_Cliente = rs.getInt("ID_Cliente");
            String nome = rs.getString("Nome_Cliente");
            cliente.setNome(nome);
            cliente.setId(id_Cliente);

        }

        rs.close();
        stmt.close();
        conexao.close();
        return cliente;
    }

}
