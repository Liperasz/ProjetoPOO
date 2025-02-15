package model.vo;


import java.time.LocalDate;

public abstract class Funcionario extends Pessoa {

    private Status status;

    protected Funcionario(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);

    }


}
