//package com.fiap.greentracefood.infrastructure.messaging;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiap.greentracefood.domain.entity.pagamento.model.Pagamento;
//import io.awspring.cloud.sqs.operations.SqsTemplate;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class OrderSender {
//
//    @Value("${order.queue}")
//    private String PROCESSED_QUEUE_NAME;
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderSender.class);
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final SqsTemplate sqsTemplate;
//
//    public void sendProcessedPaymentMessage(Pagamento pagamento) {
//        try {
//            PagamentoOrder pagamentoOrder = new PagamentoOrder(pagamento.getId(), pagamento.getStatus());
//            String jsonMessage = objectMapper.writeValueAsString(pagamentoOrder);
//            sqsTemplate.send(PROCESSED_QUEUE_NAME, jsonMessage);
//            logger.info("Message sent to {} successfully", PROCESSED_QUEUE_NAME);
//        } catch (Exception e) {
//            logger.error("Error sending message to {}", PROCESSED_QUEUE_NAME, e);
//        }
//    }
//}
