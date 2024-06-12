package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Resultado;
import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.services.SessaoVotacaoService;
import com.ms.resultado.web.dtos.IniciarVotacaoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sessão Votação", description = "Contém as operações relativas para iniciar a votação e leitura da sessão")
@RestController
@RequestMapping("/api/v1/sessaovotacao")
public class SessaoVotacaoController {

    @Autowired
    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @Operation(summary = "Iniciar votação", description = "Recurso para iniciar votação",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso iniciado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Recurso já iniciado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<SessaoVotacao> iniciarVotacao(@RequestBody IniciarVotacaoDto request) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.iniciarVotacao(request);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar sessão", description = "Recurso para buscar sessão por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso buscado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SessaoVotacao> buscarSessaoPorId(@PathVariable Long id) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoPorId(id);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.OK);
    }
}
