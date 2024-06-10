package com.ms.funcionario.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ms.funcionario.common.FuncionarioConstants.FUNCIONARIO;

@SpringBootTest(classes = FuncionarioService.class)
public class FuncionarioServiceTest {
    @Autowired
    private FuncionarioService funcionarioService;

    @Test
    public void cadastrarFuncionario() {
        funcionarioService.cadastrarFuncionario(FUNCIONARIO);
    }
}