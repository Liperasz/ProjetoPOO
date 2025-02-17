package model.bo;

import lib.CNP;
import model.dao.AtendenteDAO;
import model.dao.ClienteDAO;
import model.dao.ConexionJDBC;
import model.vo.Cliente;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteBO {
    // Variável estática para armazenar o email do cliente que está logado atualmente
    private static String emaillogado;

    /**
     * Cadastra um cliente no sistema, verificando a idade mínima, CPF válido e se já está cadastrado.
     * @param cliente Objeto Cliente com os dados a serem cadastrados
     * @throws SQLException Caso ocorra erro de comunicação com o banco de dados
     * @throws ClassNotFoundException Caso a classe usada para conexão com o banco não seja encontrada
     */
    public static void cadastrarCliente(@NotNull Cliente cliente) throws SQLException, ClassNotFoundException, IOException {
        int erros = 0;

        // Verifica e imprime o CPF do cliente

        // Verifica se o cliente tem idade maior ou igual a 18 anos
        if (ChronoUnit.YEARS.between(cliente.getNascimento(), LocalDate.now()) < 18) {
            System.out.println("Verificando idade do cliente");
            erros++;
        }

        // Verifica se o CPF fornecido é válido
        if (!CNP.isValidCPF(cliente.getCpf())) {
            System.out.println("Verificando cpf do cliente");
            erros++;
        }

        // Verifica se o cliente já está cadastrado no sistema
        if (ClienteDAO.ClienteExiste(cliente)) {
            System.out.println("Verificando se o cliente ja esta cadastrado");
            erros++;
        }

        // Se não houver erros, realiza o cadastro do cliente através do DAO
        if (erros == 0) {
            System.out.println("Enviando para clienteDAO");
            ClienteDAO.CadastrarCliente(cliente);
        } else {
            throw new IOException("Erro ao cadastrar Cliente, algum dado invalido");
        }
    }

    /**
     * Realiza o login do cliente verificando email e senha.
     * @param email Email do cliente para autenticação
     * @param senha Senha do cliente para autenticação
     * @return True se o login foi bem-sucedido, false caso contrário
     * @throws SQLException Caso ocorra erro de comunicação com o banco de dados
     * @throws ClassNotFoundException Caso a classe usada para conexão com o banco não seja encontrada
     */
    public static boolean loginCliente(String email, String senha) throws SQLException, ClassNotFoundException {
        return ClienteDAO.ClienteLogin(email, senha);
    }

    /**
     * Define o email do cliente logado no sistema.
     * @param email Email do cliente logado
     */
    public static void setEmailLogado(String email) {
        emaillogado = email;
    }

    /**
     * Retorna o email do cliente logado no sistema.
     * @return Email do cliente atualmente logado
     */
    public static String getEmailLogado() {
        return emaillogado;
    }
}