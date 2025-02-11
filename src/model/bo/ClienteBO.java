package model.bo;

import lib.CNP;
import model.dao.ClienteDAO;
import model.dao.ConexionJDBC;
import model.vo.Cliente;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteBO {


    public static void cadastrarCliente(@NotNull Cliente cliente) throws SQLException, ClassNotFoundException {

        System.out.println("ClienteBO recebeu o cliente");
        int erros = 0;

        System.out.println("CPF gerado: " + cliente.getCpf());

        if (ChronoUnit.YEARS.between(cliente.getNascimento(), LocalDate.now()) < 18) {
            System.out.println("Verificando idade do cliente");
            erros++;
        }

        if (!CNP.isValidCPF(cliente.getCpf())) {
            System.out.println("Verificando cpf do cliente");
            erros++;
        }

        if (ClienteDAO.ClienteExiste(cliente)) {
            System.out.println("Verificando se o cliente ja esta cadastrado");
            erros++;

        }

        if (erros == 0) {
            System.out.println("Enviando para clienteDAO");
            ClienteDAO.CadastrarCliente(cliente);
        }


    }
}
