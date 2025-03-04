package model.vo;


import java.time.LocalDate;

public class Estoque {

    private Ingrediente estoqueingrediente;
    private LocalDate validade;
    private int quantidade;
    private int Lote;

    public Estoque(Ingrediente estoqueingrediente, LocalDate validade, int quantidade, int Lote) {
        this.estoqueingrediente = estoqueingrediente;
        this.validade = validade;
        this.quantidade = quantidade;
        this.Lote = Lote;
    }

    public int getLote() {
        return Lote;
    }

    public Ingrediente getEstoqueingrediente() {
        return estoqueingrediente;
    }

    public void setEstoqueingrediente(Ingrediente estoqueingrediente) {
        this.estoqueingrediente = estoqueingrediente;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setLote(int lote) {
        Lote = lote;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }
}


