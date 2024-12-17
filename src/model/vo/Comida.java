package model.vo;

import java.util.List;

public class Comida {

    private String nome;
    private List<Ingrediente> ingredientes;
    private double preco;
    private String descricao;

    public Comida(String nome, List<Ingrediente> ingredientes, double preco, String descricao) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
        this.descricao = descricao;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
}
