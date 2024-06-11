package com.ms.proposta.services;


import com.ms.proposta.entities.Proposta;
import com.ms.proposta.exceptions.IdPropostaNaoEncontradoException;
import com.ms.proposta.repositories.FuncionarioClient;
import com.ms.proposta.repositories.PropostaRepository;
import com.ms.proposta.web.dtos.FuncionarioDto;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import com.ms.proposta.web.exceptions.FuncionarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropostaServiceTest {

    @InjectMocks
    private PropostaService propostaService;

    @Mock
    private PropostaRepository propostaRepository;

    @Mock
    private FuncionarioClient funcionarioClient;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void cadastrarProposta_comDadosValidos_retornaProposta() {
        PropostaCadastroDto propostaDto = new PropostaCadastroDto();
        propostaDto.setIdFuncionario(1L);
        propostaDto.setNome("Arrumar mesa");
        propostaDto.setDescricao("arrumar mesa sala 1");

        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(1L);
        funcionarioDto.setNome("Carlos");

        Proposta proposta = new Proposta();
        proposta.setIdFuncionario(1L);
        proposta.setNome("Arrumar mesa");
        proposta.setDescricao("arrumar mesa sala 1");
        proposta.setDataProposta(new Date());

        when(funcionarioClient.buscarPorId(propostaDto.getIdFuncionario())).thenReturn(funcionarioDto);
        when(propostaRepository.save(any(Proposta.class))).thenReturn(proposta);

        Proposta result = propostaService.cadastrarProposta(propostaDto);

        assertNotNull(result);
        assertEquals(propostaDto.getIdFuncionario(), result.getIdFuncionario());
        assertEquals(propostaDto.getNome(), result.getNome());
        assertEquals(propostaDto.getDescricao(), result.getDescricao());
    }
    @Test
    public void cadastrarProposta_dadosInvalidos_FuncionarioNaoEncontrado() {
        PropostaCadastroDto propostaDto = new PropostaCadastroDto();
        propostaDto.setIdFuncionario(1L);

        when(funcionarioClient.buscarPorId(propostaDto.getIdFuncionario())).thenReturn(null);

       assertThatThrownBy(() -> propostaService.cadastrarProposta(propostaDto))
               .isInstanceOf(FuncionarioNaoEncontradoException.class);
    }

    @Test
    public void listarPropostas() {
        Proposta proposta1 = new Proposta();
        proposta1.setId(1L);
        proposta1.setNome("Proposta 1");
        proposta1.setDescricao("Descrição da Proposta 1");

        Proposta proposta2 = new Proposta();
        proposta2.setId(2L);
        proposta2.setNome("Proposta 2");
        proposta2.setDescricao("Descrição da Proposta 2");

        List<Proposta> propostas = Arrays.asList(proposta1, proposta2);
        when(propostaRepository.findAll()).thenReturn(propostas);

        List<Proposta> result = propostaService.listarPropostas();

        assertEquals(2, result.size());
        assertEquals(proposta1, result.get(0));
        assertEquals(proposta2, result.get(1));
    }

    @Test
    public void buscarPorId_comIdValido_retornaProsposta() {
        Proposta proposta = new Proposta();
        proposta.setId(1L);
        proposta.setNome("Proposta ");
        proposta.setDescricao("Descrição ");

        when(propostaRepository.findById(1L)).thenReturn(Optional.of(proposta));

        Proposta result = propostaService.buscarPorId(1L);

        assertEquals(proposta, result);
    }

    @Test
    public void buscarPorId_comIdInvalido_retornaIdNaoEncontrado() {
        when(propostaRepository.findById(1L)).thenReturn(Optional.empty());

        IdPropostaNaoEncontradoException exception = assertThrows(IdPropostaNaoEncontradoException.class, () -> {
            propostaService.buscarPorId(1L);
        });

        assertEquals("Proposta id=1 não encontrada", exception.getMessage());
    }

    @Test
    public void alterarProposta_comDadosValidos_retornaProposta() {
        PropostaAtualizacaoDto propostaDto = new PropostaAtualizacaoDto();
        propostaDto.setNome("Nome Atualizado");
        propostaDto.setDescricao("Descrição Atualizada");

        Proposta propostaExistente = new Proposta();
        propostaExistente.setId(1L);
        propostaExistente.setNome("Nome Original");
        propostaExistente.setDescricao("Descrição Original");

        when(propostaRepository.findById(1L)).thenReturn(Optional.of(propostaExistente));
        when(propostaRepository.save(any(Proposta.class))).thenReturn(propostaExistente);

        Proposta result = propostaService.alterarProposta(1L, propostaDto);

        assertEquals(propostaDto.getNome(), result.getNome());
        assertEquals(propostaDto.getDescricao(), result.getDescricao());
    }

    @Test
    public void alterarProposta_comDadosInvalidos_retornaIdNaoEncontrado() {
        PropostaAtualizacaoDto propostaDto = new PropostaAtualizacaoDto();
        propostaDto.setNome("Nome Atualizado");
        propostaDto.setDescricao("Descrição Atualizada");

        when(propostaRepository.findById(1L)).thenReturn(Optional.empty());

        IdPropostaNaoEncontradoException exception = assertThrows(IdPropostaNaoEncontradoException.class, () -> {
            propostaService.alterarProposta(1L, propostaDto);
        });

        assertEquals("Proposta id=1 não encontrada", exception.getMessage());
    }

    @Test
    public void deletarProposta_comDadosValidos_retornaPropostaDeletada() {
        Proposta proposta = new Proposta();
        proposta.setId(1L);

        when(propostaRepository.findById(1L)).thenReturn(Optional.of(proposta));

        propostaService.deletarProposta(1L);

        verify(propostaRepository, times(1)).findById(1L);
        verify(propostaRepository, times(1)).delete(proposta);
    }

    @Test
    public void deletarProposta_comDadosInvalidos_retornaIdNaoEncontrado() {
        when(propostaRepository.findById(1L)).thenReturn(Optional.empty());

        IdPropostaNaoEncontradoException exception = assertThrows(IdPropostaNaoEncontradoException.class, () -> {
            propostaService.deletarProposta(1L);
        });

        assertEquals("Proposta id=1 não encontrada", exception.getMessage());
    }
}