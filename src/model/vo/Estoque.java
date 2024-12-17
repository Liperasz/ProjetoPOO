package model.vo;

import java.util.Date;

public class Estoque {

    private Ingrediente estoqueingrediente;
    private Date validade;
    private int quantidade;

    public Estoque(Ingrediente estoqueingrediente) {
        this.estoqueingrediente = estoqueingrediente;
        this.validade = new Date();
        this.quantidade = 0;
    }
}


