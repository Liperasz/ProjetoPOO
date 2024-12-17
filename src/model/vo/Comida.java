package model.vo;

import java.util.List;

public class Comida {

    private String nome;
    private List<Ingrediente> ingredientes;
    private double preco;

    public Comida(String nome, List<Ingrediente> ingredientes, double preco) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
    }


}
