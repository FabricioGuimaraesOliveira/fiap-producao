package com.fiap.greentracefood.infrastructure.pedido.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponseDTO implements Serializable {

    @Schema(example = "05d2b496-9751-4d4d-9875-71444271fc25")
    private String codigo;

    @Schema(example = "CRIADO", description = "Status do pedido")
    private StatusPedido status;


}
