package model.vo;

import java.util.List;

public class Comida {

    private String nome;
    private List<Ingrediente> ingredientes;

    public Comida(String nome, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.ingredientes = ingredientes;
    }


}
