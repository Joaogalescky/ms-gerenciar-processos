package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Resultado;
import com.ms.resultado.services.ResultadoService;
import com.ms.resultado.web.dtos.ResultadoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Resultados", description = "Contém operação relativas para leitura do resultado")
@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;

    @Autowired
    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @Operation(summary = "Buscar resultado", description = "Recurso para buscar resultado por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resultado buscado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resultado não encontrado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/{idSessao}")
    public ResponseEntity<ResultadoDto> verResultado(@PathVariable Long idSessao) {
        ResultadoDto resultadoDto = resultadoService.verResultado(idSessao);
        return ResponseEntity.ok(resultadoDto);
    }
}
