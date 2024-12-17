package model.vo;

import java.util.Date;

public class Aluno extends Cliente {

    private String ra;

    public Aluno(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, Curso curso, String ra) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha, curso);

        this.ra = ra;
    }
}
