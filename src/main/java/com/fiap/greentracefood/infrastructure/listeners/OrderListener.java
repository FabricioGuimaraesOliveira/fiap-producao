package com.fiap.greentracefood.infrastructure.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.greentracefood.domain.entity.pagamento.enums.StatusPagamento;
import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final PedidoGateway pedidoGateway;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SqsListener("${aws.sqs.queue.order}")
    public void receiveMessage(String message) {
        try {
            OrderDTO orderDTO = objectMapper.readValue(message, OrderDTO.class);
            logger.info("Received order: {}", orderDTO);

            StatusPedido statusPedido;
            if (StatusPagamento.APROVADO.equals(orderDTO.getStatus())) {
                statusPedido = StatusPedido.RECEBIDO;
            } else {
                statusPedido = StatusPedido.CANCELADO;
                pedidoGateway.notificar(new Pedido(orderDTO.getId(), statusPedido));
            }

            Pedido pedido = new Pedido(orderDTO.getId(), statusPedido);
            pedidoGateway.salvar(pedido);

        } catch (Exception e) {
            logger.error("Error processing message", e);
        }

    }
}
