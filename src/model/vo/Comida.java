package model.vo;

import java.util.List;

public class Comida {

    private String nome;
    private List<Ingrediente> ingredientes;
    private double preco;
    private String descricao;
    private Integer quantidade;

    public Comida(String nome, List<Ingrediente> ingredientes, double preco, String descricao) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Comida() {}

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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
