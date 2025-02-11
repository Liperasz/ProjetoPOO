package model.vo;

import java.time.LocalDate;

public abstract class Pessoa {

    private String nome;
    private LocalDate nascimento;
    private String email;
    private Sexo sexo;
    private String cpf;
    private String telefone;
    private String senha;

    protected Pessoa(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone =  telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getSexoString() {
        return sexo.toString();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
