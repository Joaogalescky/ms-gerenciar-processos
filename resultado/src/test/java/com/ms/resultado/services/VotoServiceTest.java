package com.ms.resultado.services;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.enums.StatusSessao;
import com.ms.resultado.exceptions.EntidadeNaoEncontradaException;
import com.ms.resultado.exceptions.VotoDuplicadoException;
import com.ms.resultado.repositories.FuncionarioClient;
import com.ms.resultado.repositories.SessaoVotacaoRepository;
import com.ms.resultado.repositories.VotoRepository;
import com.ms.resultado.web.dtos.FuncionarioDto;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import org.junit.jupiter.api.Assertions;
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

class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private FuncionarioClient funcionarioClient;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Deve lançar exceção ao cadastrar voto em sessão inativa")
    void cadastrarVotoCase1() {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        VotoCadastroDto votoCadastroDto = new VotoCadastroDto(1L, 1L, 1L, Escolha.REPROVADO);
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setStatus(StatusSessao.INATIVO);

        when(sessaoVotacaoRepository.findById(1L)).thenReturn(Optional.of(sessaoVotacao));

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            votoService.cadastrarVoto(votoCadastroDto);
        });

        assertEquals("Não é possível votar em uma sessão de votação INATIVA", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar voto duplicado")
    void cadastrarVotoCase2() {
        VotoCadastroDto votoCadastroDto = new VotoCadastroDto(1L, 1L, 1L, Escolha.REPROVADO);
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setStatus(StatusSessao.ATIVO);

        when(sessaoVotacaoRepository.findById(1L)).thenReturn(Optional.of(sessaoVotacao));
        when(votoRepository.existsByIdSessaoAndIdFunc(1L, 1L)).thenReturn(true);

        VotoDuplicadoException exception = assertThrows(VotoDuplicadoException.class, () -> {
            votoService.cadastrarVoto(votoCadastroDto);
        });

        assertEquals("Este funcionário já votou nesta sessão de votação.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve verificar a existência de voto")
    void verificarVotoExistente() {
        Long sessaoId = 1L;
        Long funcionarioId = 1L;

        when(votoRepository.existsByIdSessaoAndIdFunc(sessaoId, funcionarioId)).thenReturn(true);

        boolean result = votoService.verificarVotoExistente(sessaoId, funcionarioId);

        assertTrue(result);
    }
}
