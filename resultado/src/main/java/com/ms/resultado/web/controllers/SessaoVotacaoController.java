package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.services.SessaoVotacaoService;
import com.ms.resultado.web.dtos.IniciarVotacaoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessaovotacao")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoVotacao> iniciarVotacao(@RequestBody IniciarVotacaoDto request) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.iniciarVotacao(request);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoVotacao> buscarSessaoPorId(@PathVariable Long id) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscarSessaoPorId(id);
        return new ResponseEntity<>(sessaoVotacao, HttpStatus.OK);
    }
}
