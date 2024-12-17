package model.vo;


import java.time.LocalDate;

public class Estoque {

    private Ingrediente estoqueingrediente;
    private LocalDate validade;
    private int quantidade;

    public Estoque(Ingrediente estoqueingrediente, LocalDate validade, int quantidade) {
        this.estoqueingrediente = estoqueingrediente;
        this.validade = validade;
        this.quantidade = quantidade;
    }
}


