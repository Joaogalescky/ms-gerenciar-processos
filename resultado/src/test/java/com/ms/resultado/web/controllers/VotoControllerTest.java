package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.services.VotoService;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VotoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private VotoService votoService;

    @BeforeEach
    void setUp() {
        Mockito.reset(votoService);
    }

    @Test
    @DisplayName("Deve registrar voto com sucesso")
    void registrarVotoSuccess() {
        VotoCadastroDto votoCadastroDto = new VotoCadastroDto(1L, 1L, 1L, Escolha.APROVADO);
        Voto voto = new Voto(1L, 1L, 1L, Escolha.APROVADO);
        when(votoService.cadastrarVoto(votoCadastroDto)).thenReturn(voto);

        ResponseEntity<Voto> responseEntity = restTemplate.postForEntity("/api/v1/votos", votoCadastroDto, Voto.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(voto, responseEntity.getBody());
        Mockito.verify(votoService, Mockito.times(1)).cadastrarVoto(votoCadastroDto);
    }




}
