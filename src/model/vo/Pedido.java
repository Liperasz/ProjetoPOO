package model.vo;

import java.util.List;

public class Pedido {

    private double valor;
    private Cliente cliente;
    private Atendente funcionario;
    private boolean entregue;
    private List<Comida> pedidos;


    public Pedido(double valor, Cliente cliente, Atendente funcionario, List<Comida> pedidos) {
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.pedidos = pedidos;
    }

    public double getValor() {
        return valor;
    }

    public List<Comida> getPedidos() {
        return pedidos;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public Atendente getFuncionario() {
        return funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
