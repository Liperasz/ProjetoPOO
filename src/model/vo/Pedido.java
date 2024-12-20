package model.vo;

import java.util.List;

public class Pedido {

    private double valor;
    private Cliente cliente;
    private Atendente funcionario;
    private boolean entregue;
    private List<Comida> pedidos;
    private boolean pago;


    public Pedido(double valor, Cliente cliente, Atendente funcionario, List<Comida> pedidos) {
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.pedidos = pedidos;
        this.entregue = false;
        this.pago = false;
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

    public boolean isPago() {
        return pago;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
