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

    public Pessoa() {}

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
