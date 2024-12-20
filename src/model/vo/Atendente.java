package model.vo;


import java.time.LocalDate;

public class Atendente extends Funcionario {

    private Status status;

    public Atendente(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, double salario, Status status) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha, salario);

        this.status = status;
    }


}
