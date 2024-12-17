package model.vo;


import java.time.LocalDate;

public abstract class Funcionario extends Pessoa {

    private double salario;
    private Turno turno;

    protected Funcionario(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, double salario, Turno turno) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);

        this.salario = salario;
        this.turno = turno;
    }

}
