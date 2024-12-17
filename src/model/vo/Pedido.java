package model.vo;

public class Pedido {

    private double valor;
    private Cliente cliente;
    private Funcionario funcionario;
    private boolean entregue;

    public Pedido(double valor, Cliente cliente, Funcionario funcionario) {
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
}
