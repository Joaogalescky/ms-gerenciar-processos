package com.ms.proposta.web.controllers;

import com.ms.proposta.entities.Proposta;
import com.ms.proposta.services.PropostaService;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/propostas")
@RequiredArgsConstructor
public class PropostaController {

    @Autowired
    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<Proposta> cadastrarProposta(@RequestBody PropostaCadastroDto propostaDto) {
        Proposta proposta = propostaService.cadastrarProposta(propostaDto);
        return ResponseEntity.ok(proposta);
    }

    @GetMapping
    public ResponseEntity<List<Proposta>> listarPropostas() {
        List<Proposta> propostas = propostaService.listarPropostas();
        return ResponseEntity.ok(propostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proposta> buscarPorId(@PathVariable Long id) {
        Proposta proposta = propostaService.buscarPorId(id);
        return ResponseEntity.ok(proposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proposta> alterarProposta(@PathVariable Long id, @RequestBody PropostaAtualizacaoDto propostaDto) {
        Proposta proposta = propostaService.alterarProposta(id, propostaDto);
        return ResponseEntity.ok(proposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProposta(@PathVariable Long id) {
        propostaService.deletarProposta(id);
        return ResponseEntity.noContent().build();
    }
}
