package model.bo;

import lib.CNP;
import model.dao.AtendenteDAO;
import model.dao.ClienteDAO;
import model.vo.Atendente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AtendenteBO {

    // Armazena o email do atendente logado
    private static String emaillogado;

    // Método para cadastrar um atendente
    public static void cadastrarAtendente(Atendente atendente) throws SQLException, ClassNotFoundException {

        int erros = 0;

        // Valida o CPF do atendente
        if (!CNP.isValidCPF(atendente.getCpf())) {
            System.out.println("Verificando cpf do atendente");
            erros++;
        }

        // Verifica se o atendente já existe no banco
        if (AtendenteDAO.AtendenteExiste(atendente)) {
            System.out.println("Verificando se o atendente ja esta cadastrado");
            erros++;
        }

        // Caso não haja erros, delega o cadastro ao DAO
        if (erros == 0) {
            AtendenteDAO.CadastrarAtendente(atendente);
            System.out.println("Enviando para atendenteDAO");
        } else {
            throw new RuntimeException("Erro ao cadastrar cliente, algum dado inválido");
        }
    }

    // Método para login do atendente
    public static boolean loginAtendente(String email, String senha) throws SQLException, ClassNotFoundException {
        // Verifica no DAO se as credenciais estão corretas
        return AtendenteDAO.AtendenteLogin(email, senha);
    }

    // Método para listar todos os atendentes
    public static Map<Integer, Atendente> listarAtendentes() throws SQLException, ClassNotFoundException {

        System.out.println("Chegou na listar atendentes");
        // Cria o mapa de atendentes
        Map<Integer, Atendente> atendentes = new HashMap<Integer, Atendente>();
        // Popula o mapa chamando o DAO
        atendentes = AtendenteDAO.ListaAtendente();
        System.out.println("Passou da lista de atendentes (AtendenteDAO.ListaAtendente())");
        return atendentes;
    }

    // Método para alterar os dados de um atendente existente
    public static void AlterarAtendente(Integer ID_Atendente, Atendente atendente) throws SQLException, ClassNotFoundException {
        System.out.println("Alterando atendente de AtendenteBO");
        // Delegação da alteração ao DAO
        AtendenteDAO.AlterarAtendente(ID_Atendente, atendente);
        System.out.println("Passou de alterar atendente de AtendenteDAO");
    }

    // Define o email do atendente logado
    public static void setEmailLogado(String email) {
        emaillogado = email;
    }

    // Retorna o email do atendente logado
    public static String getEmailLogado() {
        return emaillogado;
    }
}