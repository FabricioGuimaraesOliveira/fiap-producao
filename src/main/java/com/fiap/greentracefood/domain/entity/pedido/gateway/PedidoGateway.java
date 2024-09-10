package com.fiap.greentracefood.domain.entity.pedido.gateway;


import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;

import java.util.Optional;

public interface PedidoGateway {


    Optional<Pedido> detalharPorCodigo(String codigoPedido);
    Pedido salvar(Pedido pedido);
    void notificar(Pedido pedido);



}
