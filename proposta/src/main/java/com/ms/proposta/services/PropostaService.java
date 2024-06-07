package com.ms.proposta.services;

import com.ms.proposta.Enum.Status;
import com.ms.proposta.entities.Proposta;
import com.ms.proposta.exceptions.IdPropostaNaoEncontradoException;
import com.ms.proposta.repositories.PropostaRepository;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {
    @Autowired
    private final PropostaRepository propostaRepository;

    @Autowired
    private final AgendadorProposta propostaScheduler;

    public Proposta cadastrarProposta(PropostaCadastroDto propostaDto) {
        Proposta proposta = new Proposta();
        proposta.setNome(propostaDto.getNome());
        proposta.setDescricao(propostaDto.getDescricao());
        proposta.setStatus(Status.ATIVO);

        if (propostaDto.getTempoVoto() != null) {
            proposta.setTempoVoto(Proposta.converterTempo(propostaDto.getTempoVoto()));
        } else {
            proposta.setTempoVoto(60000L);
        }

        proposta.setDataProposta(new Date());
        Proposta savedProposta = propostaRepository.save(proposta);

        propostaScheduler.scheduleStatusChange(savedProposta);

        return savedProposta;
    }

    public List<Proposta> listarPropostas() {
        List<Proposta> propostas = propostaRepository.findAll();
        for (Proposta proposta : propostas) {
            proposta.setTempoRestante(proposta.getTempoRestante());
        }
        return propostas;
    }

    public Proposta buscarPorId(Long id) {
        Proposta proposta = propostaRepository.findById(id).orElseThrow(
                () -> new IdPropostaNaoEncontradoException(String.format("Proposta id=%s não encontrada", id))
        );
        proposta.setTempoRestante(proposta.getTempoRestante());
        return proposta;
    }

    public Proposta alterarProposta(Long idProposta, PropostaAtualizacaoDto propostaDto) {
        Proposta propostaExistente = buscarPorId(idProposta);

        propostaExistente.setNome(propostaDto.getNome());
        propostaExistente.setDescricao(propostaDto.getDescricao());
        propostaExistente.setStatus(propostaDto.getStatus());

        if (propostaDto.getTempoVoto() != null) {
            propostaExistente.setTempoVoto(Proposta.converterTempo(propostaDto.getTempoVoto()));
        }
        return propostaRepository.save(propostaExistente);
    }

    public void deletarProposta(Long id) {
        Proposta proposta = buscarPorId(id);
        propostaRepository.delete(proposta);
    }
}
