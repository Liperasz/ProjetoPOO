package model.vo;

import java.util.Date;

public class Professor extends Cliente {

    private String siape;

    public Professor(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, Curso curso, String siape) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha, curso);

        this.siape = siape;
    }
}
