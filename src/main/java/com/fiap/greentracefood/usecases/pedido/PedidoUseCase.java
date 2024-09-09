package com.fiap.greentracefood.usecases.pedido;


import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;

import java.util.Optional;


public class PedidoUseCase {
    private final PedidoGateway pedidoGateway;


    public PedidoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;

    }


    public void alterarStatusPedido(String codigo, StatusPedido status) {
        Pedido pedido = pedidoGateway.detalharPorCodigo(codigo).orElseThrow();
        pedido.setStatus(status);
        pedidoGateway.salvar(pedido);

    }

    public Optional<Pedido> detalharPorCodigo(String codigoPedido) {
        return pedidoGateway.detalharPorCodigo(codigoPedido);
    }


}
