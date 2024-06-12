package com.ms.resultado.services;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.enums.StatusSessao;
import com.ms.resultado.exceptions.EntidadeNaoEncontradaException;
import com.ms.resultado.repositories.PropostaClient;
import com.ms.resultado.repositories.SessaoVotacaoRepository;
import com.ms.resultado.web.dtos.IniciarVotacaoDto;
import com.ms.resultado.web.dtos.PropostaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessaoVotacaoServiceTest {

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    @Mock
    private PropostaClient propostaClient;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private AgendadorVotacao agendadorVotacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve iniciar uma votação com sucesso")
    void iniciarVotacaoSuccess() {

        IniciarVotacaoDto iniciarVotacaoDto = new IniciarVotacaoDto(1L, "00:02");
        PropostaDto propostaDto = new PropostaDto(1L, 1L, "Nome da Proposta", "Descrição da Proposta");
        when(propostaClient.buscarPorId(1L)).thenReturn(propostaDto);
        when(sessaoVotacaoRepository.save(any(SessaoVotacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SessaoVotacao resultado = sessaoVotacaoService.iniciarVotacao(iniciarVotacaoDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProposta());
        assertEquals(StatusSessao.ATIVO, resultado.getStatus());
        assertNotNull(resultado.getHoraInicio());
        assertEquals(120000L, resultado.getTempoVotacao()); // Corrigido para 2 minutos
        verify(sessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
        verify(agendadorVotacao, times(1)).scheduleStatusChange(any(SessaoVotacao.class));
    }


    @Test
    @DisplayName("Deve lançar exceção ao iniciar votação com proposta não encontrada")
    void iniciarVotacaoCase2() {

        IniciarVotacaoDto iniciarVotacaoDto = new IniciarVotacaoDto(1L, "00:02");
        when(propostaClient.buscarPorId(1L)).thenReturn(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoVotacaoService.iniciarVotacao(iniciarVotacaoDto);
        });
    }

    @Test
    @DisplayName("Deve buscar uma sessão de votação por ID com sucesso")
    void buscarSessaoPorIdSuccess() {

        SessaoVotacao sessaoVotacao = new SessaoVotacao(1L, 1L, 60000L, StatusSessao.ATIVO, new Date(), "00:01:00");
        when(sessaoVotacaoRepository.findById(1L)).thenReturn(Optional.of(sessaoVotacao));

        SessaoVotacao resultado = sessaoVotacaoService.buscarSessaoPorId(1L);

        assertNotNull(resultado);
        assertEquals(sessaoVotacao, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar sessão de votação por ID não encontrada")
    void buscarSessaoPorIdCase2() {

        when(sessaoVotacaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoVotacaoService.buscarSessaoPorId(1L);
        });
    }
}
