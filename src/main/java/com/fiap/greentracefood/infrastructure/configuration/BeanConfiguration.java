package com.fiap.greentracefood.infrastructure.configuration;

import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.infrastructure.messaging.NotificationStatusPedido;
import com.fiap.greentracefood.infrastructure.pedido.gateway.PedidoDatabaseRepository;
import com.fiap.greentracefood.usecases.pedido.PedidoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Bean
	public PedidoGateway createPedidoGateway(DynamoDbEnhancedClient enhancedClient, @Value("${dynamodb.tablename}") String tableName, NotificationStatusPedido notificationStatusPedido) {
		return new PedidoDatabaseRepository(enhancedClient,tableName,modelMapper(),notificationStatusPedido);
	}




	@Bean
	PedidoUseCase createPedidoUseCase(PedidoGateway pedidoGateway) {
		return new PedidoUseCase(pedidoGateway);
	}
}
