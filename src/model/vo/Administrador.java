package model.vo;


import java.time.LocalDate;

public class Administrador extends Funcionario {

    public Administrador(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, double salario, Turno turno) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha, salario, turno);

    }
}
