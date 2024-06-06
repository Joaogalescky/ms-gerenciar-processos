package com.ms.funcionario.web.controllers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.services.FuncionarioService;
import com.ms.funcionario.web.dtos.FuncionarioResponseDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Iterable<Funcionario>> listarFuncionario() {
        Iterable<Funcionario> funcionarios = funcionarioService.listarFuncionario();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioCadastrado = funcionarioService.cadastrarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> getById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(FuncionarioMapper.toDto(funcionario));
    }
}
