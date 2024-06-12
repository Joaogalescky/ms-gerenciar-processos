package com.ms.resultado.services;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.repositories.VotoRepository;
import com.ms.resultado.web.dtos.ResultadoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ResultadoServiceTest {

    @InjectMocks
    private ResultadoService resultadoService;

    @Mock
    private VotoRepository votoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar o resultado da votação")
    void verResultado() {
        Long idSessao = 1L;
        List<Voto> votos = Arrays.asList(
                new Voto(1L, idSessao, 1L, Escolha.APROVADO),
                new Voto(2L, idSessao, 2L, Escolha.APROVADO),
                new Voto(3L, idSessao, 3L, Escolha.REPROVADO)
        );
        when(votoRepository.findByIdSessao(idSessao)).thenReturn(votos);

        ResultadoDto resultadoDto = resultadoService.verResultado(idSessao);

        assertThat(resultadoDto).isNotNull();
        assertThat(resultadoDto.getNumeroSessao()).isEqualTo(idSessao);
        assertThat(resultadoDto.getVotosAprovados()).isEqualTo(2);
        assertThat(resultadoDto.getVotosReprovados()).isEqualTo(1);
        assertThat(resultadoDto.getResultadoFinal()).isEqualTo(Escolha.APROVADO);
    }

    @Test
    @DisplayName("Deve retornar resultado vazio quando não há votos")
    void verResultadoCase1() {
        Long idSessao = 1L;
        when(votoRepository.findByIdSessao(idSessao)).thenReturn(Arrays.asList());

        ResultadoDto resultadoDto = resultadoService.verResultado(idSessao);

        assertThat(resultadoDto).isNotNull();
        assertThat(resultadoDto.getNumeroSessao()).isEqualTo(idSessao);
        assertThat(resultadoDto.getVotosAprovados()).isEqualTo(0);
        assertThat(resultadoDto.getVotosReprovados()).isEqualTo(0);
        assertThat(resultadoDto.getResultadoFinal()).isEqualTo(Escolha.REPROVADO);
    }
}
