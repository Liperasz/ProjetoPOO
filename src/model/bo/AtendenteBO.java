package model.bo;

import lib.CNP;
import model.dao.AtendenteDAO;
import model.dao.ClienteDAO;
import model.vo.Atendente;

import java.sql.SQLException;

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

}
