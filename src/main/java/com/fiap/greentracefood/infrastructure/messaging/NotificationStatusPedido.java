package com.fiap.greentracefood.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationStatusPedido {

    @Value("${aws.sqs.queue.notification}")
    private String PROCESSED_QUEUE_NAME;

    private static final Logger logger = LoggerFactory.getLogger(NotificationStatusPedido.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SqsTemplate sqsTemplate;

    public void notificationStatusPedido(Pedido pedido ) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(pedido);
            sqsTemplate.send(PROCESSED_QUEUE_NAME, jsonMessage);
            logger.info("Notificando Status Pedido: {}", jsonMessage);
            logger.info("Message sent to {} successfully", PROCESSED_QUEUE_NAME);
        } catch (Exception e) {
            logger.error("Error sending message to {}", PROCESSED_QUEUE_NAME, e);
        }
    }
}
