package com.fiap.greentracefood.infrastructure.persistence.pedido;

import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Objects;

@DynamoDbBean
public class PedidoEntity {

    private String codigo;
    private StatusPedido status;

    public PedidoEntity() {}

    public PedidoEntity(String codigo, StatusPedido status) {
        this.codigo = codigo;
        this.status = status;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("codigo")
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @DynamoDbAttribute("status")
    public StatusPedido getStatus() {
        return this.status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoEntity that)) return false;
        return Objects.equals(getCodigo(), that.getCodigo()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getStatus());
    }

    @Override
    public String toString() {
        return "PedidoEntity [codigo=" + codigo + ", status=" + status + "]";
    }
}
