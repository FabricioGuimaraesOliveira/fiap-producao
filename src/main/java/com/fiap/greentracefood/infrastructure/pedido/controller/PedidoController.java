package com.fiap.greentracefood.infrastructure.pedido.controller;


import com.fiap.greentracefood.domain.entity.pedido.enums.StatusPedido;
import com.fiap.greentracefood.domain.entity.pedido.model.Pedido;
import com.fiap.greentracefood.infrastructure.pedido.dto.response.PedidoResponseDTO;
import com.fiap.greentracefood.usecases.pedido.PedidoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/pedidos")
@Tag(name = "pedido", description = "API responsável pelo controle da produção de pedidos.")
public class PedidoController {
    private final ModelMapper modelMapper;
    private final PedidoUseCase pedidoUseCase;


    public PedidoController(ModelMapper modelMapper, PedidoUseCase pedidoUseCase) {
        this.modelMapper = modelMapper;
        this.pedidoUseCase = pedidoUseCase;
    }



    @Operation(summary = "Alterar o status do pedido")
    @ApiResponse(responseCode = "200", description = "Status do pedido alterado com sucesso",
            content = { @Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Erro ao alterar o status do pedido",
            content = @Content)
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
            content = @Content)
    @PatchMapping("/{codigo}/alterar-status")
    @ResponseStatus(HttpStatus.OK)
    void alterarStatusPedido(
            @Parameter(description = "Código do pedido", required = true)
            @PathVariable String codigo,
            @Parameter(description = "Atualizar Status do Pedido", required = true)
            @RequestParam StatusPedido status) {
             pedidoUseCase.alterarStatusPedido(codigo, status);
    }

    @Operation(summary = "Detalhar pedido por código")
    @ApiResponse(responseCode = "200", description = "Pedido detalhado encontrado",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PedidoResponseDTO.class)) })
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{codigo}/detalhar")
    public ResponseEntity<PedidoResponseDTO> detalharPorCodigo(@PathVariable String codigo) {
        Optional<Pedido> pedidoDetalhadoOptional = pedidoUseCase.detalharPorCodigo(codigo);
        return pedidoDetalhadoOptional
                .map(pedidoDetalhado -> modelMapper.map(pedidoDetalhado, PedidoResponseDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
