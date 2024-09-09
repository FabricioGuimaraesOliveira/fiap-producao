package com.fiap.greentracefood.domain.entity.pedido.model;

import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;

public class Pedido {

    private String codigo;
    private StatusPedido status = StatusPedido.RECEBIDO;

    // Construtor
    public Pedido(String codigo, StatusPedido status) {
        this.codigo = codigo;
        this.status = status;
    }
    // Construtor padrão público
    public Pedido() {
    }
    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    // Método builder para facilitar a criação de objetos Pedido
    public static PedidoBuilder builder() {
        return new PedidoBuilder();
    }

    // Classe builder
    public static class PedidoBuilder {
        private String codigo;
        private StatusPedido status = StatusPedido.RECEBIDO;

        public PedidoBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public PedidoBuilder status(StatusPedido status) {
            this.status = status;
            return this;
        }

        public Pedido build() {
            return new Pedido(codigo, status);
        }
    }
}
