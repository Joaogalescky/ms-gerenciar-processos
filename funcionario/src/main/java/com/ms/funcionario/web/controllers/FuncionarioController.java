package com.ms.funcionario.web.controllers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.exceptions.FuncionarioNaoEncontradoException;
import com.ms.funcionario.services.FuncionarioService;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Iterable<Funcionario>> listarFuncionario() {
        Iterable<Funcionario> funcionarios = funcionarioService.listarFuncionario();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FuncionarioCadastrarDto> cadastrarFuncionario(@RequestBody FuncionarioCadastrarDto funcionarioCadastrarDto) {
        Funcionario funcionario = funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapper.toDtoCadastrar(funcionario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioListarDto> getById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(id);
        }
        return ResponseEntity.ok(FuncionarioMapper.toListDto(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioCadastrarDto> atualizarFun(@PathVariable Long id, @RequestBody FuncionarioListarDto funcionarioListarDto) throws Exception {
        Funcionario funcionario = funcionarioService.alterarFuncionario(id, funcionarioListarDto);
        return ResponseEntity.ok(FuncionarioMapper.toDtoCadastrar(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
    funcionarioService.deletarFuncionario(id);
    return ResponseEntity.noContent().build();
  }
}

