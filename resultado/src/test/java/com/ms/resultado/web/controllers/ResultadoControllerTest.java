package com.ms.resultado.web.controllers;

import com.ms.resultado.services.ResultadoService;
import com.ms.resultado.web.dtos.ResultadoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResultadoControllerTest {

    @InjectMocks
    private ResultadoController resultadoController;

    @Mock
    private ResultadoService resultadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar o resultado com sucesso")
    void verResultado_Success() {

        Long idSessao = 1L;
        ResultadoDto resultadoDto = new ResultadoDto();
        when(resultadoService.verResultado(idSessao)).thenReturn(resultadoDto);

        ResponseEntity<ResultadoDto> responseEntity = resultadoController.verResultado(idSessao);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resultadoDto, responseEntity.getBody());
        verify(resultadoService, times(1)).verResultado(idSessao);
    }
}
