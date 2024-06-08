package com.ms.resultado.controllers;

import com.ms.resultado.services.ResultadoService;
import com.ms.resultado.web.dtos.ResultadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;

    @Autowired
    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping("/{idSessao}")
    public ResponseEntity<ResultadoDto> verResultado(@PathVariable Long idSessao) {
        ResultadoDto resultadoDto = resultadoService.verResultado(idSessao);
        return ResponseEntity.ok(resultadoDto);
    }
}
