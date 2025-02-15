package model.bo;

import lib.CNP;
import model.dao.AtendenteDAO;
import model.dao.ClienteDAO;
import model.vo.Atendente;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AtendenteBO {

    public static void cadastrarAtendente(Atendente atendente) throws SQLException, ClassNotFoundException {

        int erros = 0;

        if (!CNP.isValidCPF(atendente.getCpf())) {
            System.out.println("Verificando cpf do atendente");
            erros++;
        }

        if (AtendenteDAO.AtendenteExiste(atendente)) {
            System.out.println("Verificando se o atendente ja esta cadastrado");
            erros++;

        }

        if (erros == 0) {
            AtendenteDAO.CadastrarAtendente(atendente);
            System.out.println("Enviando para atendenteDAO");

        }
    }

    public static boolean loginAtendente(String email, String senha) throws SQLException, ClassNotFoundException {

        return AtendenteDAO.AtendenteLogin(email, senha);
    }

    public static Map<Integer, Atendente> listarAtendentes() throws SQLException, ClassNotFoundException {

        System.out.println("Chegou na listar atendentes");
        Map<Integer, Atendente> atendentes = new HashMap<Integer, Atendente>();
        atendentes = AtendenteDAO.ListaAtendente();
        System.out.println("Passou da lista de atendentes (AtendenteDAO.ListaAtendente())");
        return atendentes;
    }

    public static void AlterarAtendente(Integer ID_Atendente, Atendente atendente) throws SQLException, ClassNotFoundException {
        System.out.println("Alterando atendente de AtendenteBO");
        AtendenteDAO.AlterarAtendente(ID_Atendente, atendente);
        System.out.println("Passou de alterar atendente de AtendenteDAO");

    }
}
