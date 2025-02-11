package model.bo;

import lib.CNP;
import model.dao.ClienteDAO;
import model.vo.Cliente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteBO {


    public static void cadastrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        System.out.println("ClienteBO recebeu o cliente");

        if (ChronoUnit.YEARS.between(cliente.getNascimento(), LocalDate.now()) >= 18 && CNP.isValidCPF(cliente.getCpf()) && !ClienteDAO.ClienteExiste(cliente)) {

            System.out.println("Enviando para ClienteDAO");
            ClienteDAO.CadastrarCliente(cliente);

        }
    }
}
