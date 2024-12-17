package model.vo;

import java.util.List;

public class Pedido {

    private double valor;
    private Cliente cliente;
    private Funcionario funcionario;
    private boolean entregue;
    private List<Comida> pedidos;


    public Pedido(double valor, Cliente cliente, Funcionario funcionario) {
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
}
