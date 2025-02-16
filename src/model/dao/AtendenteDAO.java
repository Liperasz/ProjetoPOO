package model.dao;

import model.vo.Atendente;
import model.vo.Cliente;
import model.vo.Sexo;
import model.vo.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AtendenteDAO {


    public static void CadastrarAtendente(Atendente atendente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();


        String sql = "insert into atendente (Nome_Atendente, Nascimento_Atendente, Email_Atendente, Senha_Atendente, Sexo_Atendente, CPF_Atendente, Telefone_Atendente, Status_Atendente)"
                + "   values (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setString(1, atendente.getNome());
        stmt.setDate(2, Date.valueOf(atendente.getNascimento()));
        stmt.setString(3, atendente.getEmail());
        stmt.setString(4, atendente.getSenha());
        stmt.setString(5, atendente.getSexoString());
        stmt.setString(6, atendente.getCpf());
        stmt.setString(7, atendente.getTelefone());
        stmt.setString(8, atendente.getStatus().toString());


        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public static boolean AtendenteExiste(Atendente atendente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from atendente where CPF_Atendente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, atendente.getCpf());

        ResultSet rs = stmt.executeQuery();
        boolean existe = rs.next();

        rs.close();
        stmt.close();
        conexao.close();
        return existe;
    }

    public static boolean AtendenteLogin(String email, String senha) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from atendente where Email_Atendente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        boolean existe = rs.next();

        if (existe) {

            String senha_real = rs.getString("Senha_Atendente");

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

    public static Map<Integer, Atendente> ListaAtendente() throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from  atendente";

        System.out.println("Chegou no ListaAtendente() do AtendenteDAO");

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        Map<Integer, Atendente> atendentes = new HashMap<>();

        while (rs.next()) {

            Integer id = rs.getInt("ID_Atendente");
            String nome = rs.getString("Nome_Atendente");
            LocalDate nascimento = rs.getDate("Nascimento_Atendente").toLocalDate();
            Sexo sexo = Sexo.valueOf(rs.getString("Sexo_Atendente"));
            String cpf = rs.getString("CPF_Atendente");
            String email = rs.getString("Email_Atendente");
            String senha = rs.getString("Senha_Atendente");
            String telefone = rs.getString("Telefone_Atendente");
            Status status = Status.valueOf(rs.getString("Status_Atendente"));

            Atendente atendente = new Atendente(nome, nascimento, email, sexo, cpf, telefone, senha, status);
            atendentes.put(id, atendente);
        }
        System.out.println("Saindo do ListaAtendente() do AtendenteDAO");

        rs.close();
        stmt.close();
        conexao.close();
        return atendentes;
    }

    public static void AlterarAtendente(Integer ID_Atendente, Atendente atendente) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        System.out.println("Chegou em alterar Atendente() do AtendenteDAO");

        String sql = "update atendente set Nome_Atendente = ?, Nascimento_Atendente = ?, Email_Atendente = ?, Sexo_Atendente = ?, CPF_Atendente = ?, Telefone_Atendente = ?, Senha_Atendente = ?, " +
                "Status_Atendente = ? where ID_Atendente = ?";

        PreparedStatement stmt;

        stmt = conexao.prepareStatement(sql);

        stmt.setString(1, atendente.getNome());
        stmt.setDate(2, Date.valueOf(atendente.getNascimento()));
        stmt.setString(3, atendente.getEmail());
        stmt.setString(4, atendente.getSexoString());
        stmt.setString(5, atendente.getCpf());
        stmt.setString(6, atendente.getTelefone());
        stmt.setString(7, atendente.getSenha());
        stmt.setString(8, atendente.getStatus().toString());
        stmt.setInt(9, ID_Atendente);


        stmt.execute();
        stmt.close();
        conexao.close();
        System.out.println("Atendente Alterado com sucesso");
    }

    public static Atendente BuscarAtendente(String email) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexionJDBC.getConexion();

        String sql = "select * from atendente where Email_Atendente = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        Atendente atendente = new Atendente();

        if (rs.next()) {

            Integer id_Atendente = rs.getInt("ID_Atendente");
            String nome = rs.getString("Nome_Atendente");
            atendente.setNome(nome);
            atendente.setId(id_Atendente);

        }

        rs.close();
        stmt.close();
        conexao.close();
        return atendente;
    }
}
