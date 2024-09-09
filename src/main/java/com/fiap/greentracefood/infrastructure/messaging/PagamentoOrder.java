package com.fiap.greentracefood.infrastructure.messaging;

import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;

public class PagamentoOrder {
    private String id;
    private StatusPagamento status;

    public PagamentoOrder(String id, StatusPagamento status) {
        this.id = id;
        this.status = status;
    }

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
