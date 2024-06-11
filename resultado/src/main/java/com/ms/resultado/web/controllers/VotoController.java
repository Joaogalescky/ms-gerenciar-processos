package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Resultado;
import com.ms.resultado.entities.Voto;
import com.ms.resultado.services.VotoService;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import com.ms.resultado.web.dtos.mappers.VotoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Votação", description ="Contém operação relativas para registrar voto")
@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votoService;

    @Operation(summary = "Registrar voto", description = "Registrar voto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Voto registrado com sucessso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resultado.class))),
                    @ApiResponse(responseCode = "409", description = "Voto já registrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Voto não registrado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<VotoCadastroDto> registrarVoto(@RequestBody VotoCadastroDto votoCadastroDto) {
        Voto voto = votoService.cadastrarVoto(votoCadastroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VotoMapper.toVotoCadastroDto(voto));
    }
}
