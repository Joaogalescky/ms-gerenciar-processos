package com.ms.resultado.services;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.enums.StatusSessao;
import com.ms.resultado.exceptions.EntidadeNaoEncontradaException;
import com.ms.resultado.repositories.PropostaClient;
import com.ms.resultado.repositories.SessaoVotacaoRepository;
import com.ms.resultado.web.dtos.IniciarVotacaoDto;
import com.ms.resultado.web.dtos.PropostaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final PropostaClient propostaClient;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final AgendadorVotacao agendadorVotacao;

    public SessaoVotacao iniciarVotacao(IniciarVotacaoDto iniciarVotacaoDto) {
        PropostaDto propostaDto = propostaClient.buscarPorId(iniciarVotacaoDto.getIdProposta());
        if (propostaDto == null) {
            throw new EntidadeNaoEncontradaException("Proposta com id " + iniciarVotacaoDto.getIdProposta() + " não encontrada");
        }

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setIdProposta(iniciarVotacaoDto.getIdProposta());
        sessaoVotacao.setStatus(StatusSessao.ATIVO);
        sessaoVotacao.setHoraInicio(new Date());

        if (iniciarVotacaoDto.getTempoVotacao() != null) {
            sessaoVotacao.setTempoVotacao(SessaoVotacao.converterTempo(iniciarVotacaoDto.getTempoVotacao()));
        } else {
            sessaoVotacao.setTempoVotacao(60000L);
        }

        sessaoVotacaoRepository.save(sessaoVotacao);
        agendadorVotacao.scheduleStatusChange(sessaoVotacao);

        return sessaoVotacao;
    }

    public SessaoVotacao buscarSessaoPorId(Long id) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão de votação com id " + id + " não encontrada"));
        sessaoVotacao.setTempoRestante(sessaoVotacao.getTempoRestante());
        return sessaoVotacao;
    }
}
