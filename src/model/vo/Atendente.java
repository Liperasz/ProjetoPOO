package model.vo;


import java.time.LocalDate;

public class Atendente extends Funcionario {

    private Status status;
    private Integer id;

    public Atendente(String nome, LocalDate nascimento, String email, Sexo sexo, String cpf, String telefone, String senha, Status status) {
        super(nome, nascimento, email, sexo, cpf, telefone, senha);

        this.status = status;
    }

    public Atendente() {
        super();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
