package model.vo;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private double valor;
    private Cliente cliente;
    private Atendente funcionario;
    private boolean entregue;
    private List<Comida> pedidos;
    private boolean pago;
    private LocalDateTime momentopedido;
    private Integer id_pedido;

    public Pedido(double valor, Cliente cliente, Atendente funcionario, List<Comida> pedidos, LocalDateTime momentopedido, Boolean entregue, boolean pago) {
        this.valor = valor;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.pedidos = pedidos;
        this.entregue = entregue;
        this.pago = pago;
        this.momentopedido = momentopedido;
    }

    public Pedido() {}

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

    public LocalDateTime getMomentopedido() {
        return momentopedido;
    }

    public boolean isPago() {
        return pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Atendente funcionario) {
        this.funcionario = funcionario;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void setPedidos(List<Comida> pedidos) {
        this.pedidos = pedidos;
    }

    public void setMomentopedido(LocalDateTime momentopedido) {
        this.momentopedido = momentopedido;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }
}
