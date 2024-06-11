package com.ms.proposta.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = PropostaService.class)
class PropostaServiceTest {
//operacao_estado_retorno
    @Autowired
    private PropostaService propostaService;

    @Test
    void cadastrarProposta_comDadosValidos_RetornaProposta() {
        propostaService.cadastrarProposta(proposta);
    }

    @Test
    void listarPropostas() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void alterarProposta() {
    }

    @Test
    void deletarProposta() {
    }
}