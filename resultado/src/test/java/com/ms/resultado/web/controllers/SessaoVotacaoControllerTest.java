package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.services.SessaoVotacaoService;
import com.ms.resultado.web.dtos.IniciarVotacaoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SessaoVotacaoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SessaoVotacaoService sessaoVotacaoService;

    @Test
    @DisplayName("Deve iniciar votação com sucesso")
    void iniciarVotacaoSuccess() {

        IniciarVotacaoDto iniciarVotacaoDto = new IniciarVotacaoDto(1L, "60:00");
        SessaoVotacao sessaoVotacao = new SessaoVotacao(1L, 1L, 3600000L, null, new Date(), "01:00:00");
        when(sessaoVotacaoService.iniciarVotacao(iniciarVotacaoDto)).thenReturn(sessaoVotacao);

        ResponseEntity<SessaoVotacao> resposta = restTemplate.postForEntity("/api/v1/sessaovotacao", iniciarVotacaoDto, SessaoVotacao.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(sessaoVotacao, resposta.getBody());
        Mockito.verify(sessaoVotacaoService, Mockito.times(1)).iniciarVotacao(iniciarVotacaoDto);
    }

    @Test
    @DisplayName("Deve buscar sessão por ID com sucesso")
    void buscarSessaoPorIdSuccess() {

        Long id = 1L;
        SessaoVotacao sessaoVotacao = new SessaoVotacao(1L, 1L, 3600000L, null, new Date(), "01:00:00");
        when(sessaoVotacaoService.buscarSessaoPorId(id)).thenReturn(sessaoVotacao);

        ResponseEntity<SessaoVotacao> resposta = restTemplate.getForEntity("/api/v1/sessaovotacao/{id}", SessaoVotacao.class, id);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(sessaoVotacao, resposta.getBody());
        Mockito.verify(sessaoVotacaoService, Mockito.times(1)).buscarSessaoPorId(id);
    }
}
