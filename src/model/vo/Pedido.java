package model.vo;

public class Pedido {

    private int codigo;
    private double valor;
    private Cliente cliente;
    private Funcionario funcionario;
    private boolean entregue;

    public Pedido(int codigo, double valor, Cliente cliente, Funcionario funcionario) {
        this.codigo = codigo;
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
}
