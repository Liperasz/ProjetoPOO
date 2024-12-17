package model.vo;

import java.util.Date;

public class Cliente extends Pessoa {

    protected Cliente(String nome, Date nascimento, String email, Sexo sexo, String cpf, String telefone, String senha) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);


    }

}