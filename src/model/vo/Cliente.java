package model.vo;

import java.util.Date;

public abstract class Cliente extends Pessoa {

    private Curso curso;

    protected Cliente(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, Curso curso) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);
        this.curso = curso;

    }

}