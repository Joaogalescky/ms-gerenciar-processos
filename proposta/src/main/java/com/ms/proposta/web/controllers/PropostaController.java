package com.ms.proposta.web.controllers;

import com.ms.proposta.entities.Proposta;
import com.ms.proposta.repositories.FuncionarioClient;
import com.ms.proposta.services.PropostaService;
import com.ms.proposta.web.dtos.FuncionarioDto;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Propostas", description ="Contém todas as operações relativas aos para cadastro, edição e leitura de uma proposta.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/propostas")
public class PropostaController {

    @Autowired
    private final PropostaService propostaService;

    private final FuncionarioClient funcionarioClient;

    @Operation(
            summary = "Cadastrar proposta",
            description = "Recurso para cadastrar proposta",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso cadastrado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = PropostaCadastroDto.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Recurso já cadastrado no sistema",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Proposta> cadastrarProposta(@RequestBody PropostaCadastroDto propostaDto) {
        Proposta proposta = propostaService.cadastrarProposta(propostaDto);
        return ResponseEntity.ok(proposta);
    }

    @Operation(
            summary = "Listar propostas",
            description = "Recurso para listar propostas",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso listado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PropostaCadastroDto.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Proposta>> listarPropostas() {
        List<Proposta> propostas = propostaService.listarPropostas();
        return ResponseEntity.ok(propostas);
    }

    @Operation(
            summary = "Buscar proposta",
            description = "Recurso para buscar proposta por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso buscado com sucesso!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PropostaCadastroDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Proposta> buscarPorId(@PathVariable Long id) {
        Proposta proposta = propostaService.buscarPorId(id);
        return ResponseEntity.ok(proposta);
    }

    @Operation(
            summary = "Atualizar proposta",
            description = "Recurso para atualizar proposta",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Recurso atualizada com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Proposta não confere",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Proposta> alterarProposta(@PathVariable Long id, @RequestBody PropostaAtualizacaoDto propostaDto) {
        Proposta proposta = propostaService.alterarProposta(id, propostaDto);
        return ResponseEntity.ok(proposta);
    }

    @Operation(summary = "Deletar proposta", description = "Recurso para deletar proposta",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Recurso deletada com sucesso!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProposta(@PathVariable Long id) {
        propostaService.deletarProposta(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar funcionário", description = "Recurso para buscar funcionário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso buscado com sucesso!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PropostaCadastroDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioDto> buscarFuncionario(@PathVariable Long id) {
        FuncionarioDto funcionario = funcionarioClient.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }
}
