package com.ms.resultado.services;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.StatusSessao;
import com.ms.resultado.repositories.SessaoVotacaoRepository;
import com.ms.resultado.repositories.VotoRepository;
import com.ms.resultado.repositories.FuncionarioClient;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import com.ms.resultado.web.dtos.mappers.VotoMapper;
import com.ms.resultado.exceptions.EntidadeNaoEncontradaException;
import com.ms.resultado.exceptions.VotoDuplicadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final FuncionarioClient funcionarioClient;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    @Transactional
    public Voto cadastrarVoto(VotoCadastroDto votoCadastroDto) {
        verificarEntidadesExistentes(votoCadastroDto.getIdSessao(), votoCadastroDto.getIdFunc());

        if (verificarVotoExistente(votoCadastroDto.getIdSessao(), votoCadastroDto.getIdFunc())) {
            throw new VotoDuplicadoException("Este funcionário já votou nesta sessão de votação.");
        }

        Voto voto = VotoMapper.toVotoCadastrar(votoCadastroDto);
        return votoRepository.save(voto);
    }

    private void verificarEntidadesExistentes(Long sessaoVotacaoId, Long funcionarioId) {
        try {
            SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(sessaoVotacaoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão de votação não encontrada."));
            if (sessaoVotacao.getStatus() == StatusSessao.INATIVO) {
                throw new EntidadeNaoEncontradaException("Não é possível votar em uma sessão de votação INATIVA");
            }
        } catch (EntidadeNaoEncontradaException e) {
            throw e;
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException("Erro ao verificar a sessão de votação.");
        }

        try {
            funcionarioClient.buscarPorId(funcionarioId);
        } catch (Exception e) {
            throw new EntidadeNaoEncontradaException("Funcionário não encontrado.");
        }
    }


    public boolean verificarVotoExistente(Long sessaoId, Long funcionarioId) {
        return votoRepository.existsByIdSessaoAndIdFunc(sessaoId, funcionarioId);
    }
}
