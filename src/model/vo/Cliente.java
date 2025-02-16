package model.vo;

import java.time.LocalDate;

public class Cliente extends Pessoa {

    private Integer id;

    public Cliente(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);


    }

    public Cliente() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}