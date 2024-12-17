package model.vo;

import java.util.Date;

public abstract class Funcionario extends Pessoa {

    private double salario;
    private Turno turno;
    private String matricula;


    protected Funcionario(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, double salario, Turno turno, String matricula) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);

        this.salario = salario;
        this.turno = turno;
        this.matricula = matricula;

    }

}
