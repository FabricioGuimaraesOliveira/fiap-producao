package com.fiap.greentracefood.infrastructure.listeners;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;

public class OrderDTO {
    private String id;
    private StatusPagamento status;

    // Construtor padrão
    public OrderDTO() {}

    // Construtor com parâmetros
    public OrderDTO(String id, StatusPagamento status) {
        this.id = id;
        this.status = status;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagamentoOrder{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
