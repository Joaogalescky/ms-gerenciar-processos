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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Sessão Votação", description ="Contém as operações relativas para iniciar a votação e leitura da sessão")
@RestController
@RequestMapping("/api/v1/sessaovotacao")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }
    @Operation(summary = "Iniciar votação", description = "Iniciar votação",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Votação iniciada com sucessso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(responseCode = "409", description = "Votação ja está iniciada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<SessaoVotacao> iniciarVotacao(@RequestBody IniciarVotacaoDto request) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.iniciarVotacao(request);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar Sessão", description = "Buscar Sessão",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sessão buscada com sucessso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(responseCode = "404", description = "Sessão não encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SessaoVotacao> buscarSessaoPorId(@PathVariable Long id) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoPorId(id);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.OK);
    }
}
