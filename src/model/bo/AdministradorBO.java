package model.bo;

import model.dao.AdministradorDAO;
import model.dao.AtendenteDAO;

import java.sql.SQLException;

public class AdministradorBO {

    // Retorna a função que verifica se a senha está correta
    public static boolean loginAdministrador(String email, String senha) throws SQLException, ClassNotFoundException {

        return AdministradorDAO.AdministradorLogin(email, senha);
    }
}
