package model.vo;

import java.util.Date;

public class Atendente extends Funcionario {

    public Atendente(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, double salario, Turno turno, String matricula) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha, salario, turno, matricula);
    }


}