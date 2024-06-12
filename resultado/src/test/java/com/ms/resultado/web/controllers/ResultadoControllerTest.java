package com.ms.resultado.web.controllers;

import com.ms.resultado.entities.Resultado;
import com.ms.resultado.services.ResultadoService;
import com.ms.resultado.web.dtos.ResultadoDto;
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
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResultadoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ResultadoService resultadoService;

    @BeforeEach
    void setUp() {

        Mockito.reset(resultadoService);
    }

    @Test
    @DisplayName("Deve retornar o resultado com sucesso")
    void verResultadoSuccess() {

        Long idSessao = 1L;
        ResultadoDto resultadoDto = new ResultadoDto();
        when(resultadoService.verResultado(idSessao)).thenReturn(resultadoDto);

        ResponseEntity<ResultadoDto> resposta = restTemplate.getForEntity("/api/v1/resultados/{id}", ResultadoDto.class, idSessao);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(resultadoDto, resposta.getBody());
        Mockito.verify(resultadoService, Mockito.times(1)).verResultado(idSessao);
    }

    @Test
    @DisplayName("Deve retornar um erro quando o resultado não for encontrado")
    void verResultadoCase2() {

        Long idSessao = 1L;
        when(resultadoService.verResultado(idSessao)).thenReturn(null);

        ResponseEntity<ResultadoDto> resposta = restTemplate.getForEntity("/api/v1/resultados/{id}", ResultadoDto.class, idSessao);

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals(null, resposta.getBody());
        Mockito.verify(resultadoService, Mockito.times(1)).verResultado(idSessao);
    }

}
