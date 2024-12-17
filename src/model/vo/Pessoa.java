package model.vo;

import java.util.Date;

public abstract class Pessoa {

    private String nome;
    private Date nascimento;
    private String email;
    private Sexo sexo;
    private String cpf;
    private String telefone;
    private String senha;

    protected Pessoa(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone =  telefone;
        this.senha = senha;
    }



}