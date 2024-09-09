package com.fiap.greentracefood.infrastructure.pedido.gateway;

import com.fiap.greentracefood.domain.entity.pedido.gateway.PedidoGateway;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.infrastructure.persistence.pedido.PedidoEntity;
import org.modelmapper.ModelMapper;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

public class PedidoDatabaseRepository implements PedidoGateway {

    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<PedidoEntity> table;
    private final ModelMapper modelMapper;

    public PedidoDatabaseRepository(DynamoDbEnhancedClient enhancedClient, String tableName, ModelMapper modelMapper) {
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(PedidoEntity.class));
        this.modelMapper = modelMapper;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity pedidoEntity = modelMapper.map(pedido, PedidoEntity.class);
        table.putItem(pedidoEntity);
        return modelMapper.map(pedidoEntity, Pedido.class);
    }



    @Override
    public Optional<Pedido> detalharPorCodigo(String codigoPedido) {
        Key key = Key.builder().partitionValue(codigoPedido).build();
        PedidoEntity pedidoEntity = table.getItem(r -> r.key(key));

        return Optional.ofNullable(modelMapper.map(pedidoEntity, Pedido.class));
    }

}
