package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Voto;

import com.ms.resultado.services.VotoService;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import com.ms.resultado.web.dtos.mappers.VotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<VotoCadastroDto> registrarVoto(@RequestBody VotoCadastroDto votoCadastroDto) {
        Voto voto = votoService.votoCadastrar(votoCadastroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VotoMapper.toVotoCadastroDto(voto));
    }
}
