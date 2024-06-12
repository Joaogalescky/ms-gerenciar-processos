package com.ms.funcionario.web.controllers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.services.FuncionarioService;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FuncionarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    void listarFuncionario() {
        Funcionario funcionario1 = new Funcionario(1L, "João", "1980-01-01", "12345678901", "M");
        Funcionario funcionario2 = new Funcionario(2L, "Maria", "1990-02-02", "98765432109", "F");
        Iterable<Funcionario> funcionarios = List.of(funcionario1, funcionario2);
        Mockito.when(funcionarioService.listarFuncionario()).thenReturn((List<Funcionario>) funcionarios);

        ResponseEntity<List> resposta = restTemplate.getForEntity("/api/v1/funcionarios", List.class);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Mockito.verify(funcionarioService, Mockito.times(1)).listarFuncionario();
    }

    @Test
    void cadastrarFuncionario() {
        FuncionarioCadastrarDto funcionarioDto = new FuncionarioCadastrarDto(1L, "João", "1980-01-01", "12345678901", "M");
        Funcionario funcionarioMock = new Funcionario(1L, "João", "1980-01-01", "12345678901", "M");
        Mockito.when(funcionarioService.cadastrarFuncionario(funcionarioDto)).thenReturn(funcionarioMock);

        ResponseEntity<FuncionarioCadastrarDto> resposta = restTemplate.postForEntity("/api/v1/funcionarios", funcionarioDto, FuncionarioCadastrarDto.class);

        Assertions.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        Mockito.verify(funcionarioService, Mockito.times(1)).cadastrarFuncionario(funcionarioDto);
    }

    @Test
    void buscarPorId() {
        Long id = 1L;
        Funcionario funcionarioMock = new Funcionario(id, "João", "1980-01-01", "12345678901", "M");
        Mockito.when(funcionarioService.buscarPorId(id)).thenReturn(funcionarioMock);

        ResponseEntity<FuncionarioListarDto> resposta = restTemplate.getForEntity("/api/v1/funcionarios/{id}", FuncionarioListarDto.class, id);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Mockito.verify(funcionarioService, Mockito.times(1)).buscarPorId(id);
    }

    @Test
    void atualizarFun() {
        Long id = 1L;
        FuncionarioListarDto funcionarioDto = new FuncionarioListarDto("João", "1980-01-01", "12345678901", "M");
        Funcionario funcionarioMock = new Funcionario(id, "João", "1980-01-01", "12345678901", "M");
        Mockito.when(funcionarioService.alterarFuncionario(id, funcionarioDto)).thenReturn(funcionarioMock);

        ResponseEntity<FuncionarioCadastrarDto> resposta = restTemplate.exchange("/api/v1/funcionarios/{id}", HttpMethod.PUT, new HttpEntity<>(funcionarioDto), FuncionarioCadastrarDto.class, id);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Mockito.verify(funcionarioService, Mockito.times(1)).alterarFuncionario(id, funcionarioDto);
    }

    @Test
    void deletarFuncionario() {
        Long id = 1L;
        Mockito.doNothing().when(funcionarioService).deletarFuncionario(id);

        ResponseEntity<Void> resposta = restTemplate.exchange("/api/v1/funcionarios/{id}", HttpMethod.DELETE, null, Void.class, id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        Mockito.verify(funcionarioService, Mockito.times(1)).deletarFuncionario(id);
    }
}
