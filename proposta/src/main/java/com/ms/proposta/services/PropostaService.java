package com.ms.proposta.services;

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

    public Proposta cadastrarProposta(PropostaCadastroDto propostaDTO) {
        Proposta proposta = new Proposta();
        proposta.setNome(propostaDTO.getNome());
        proposta.setDescricao(propostaDTO.getDescricao());
        proposta.setStatus(propostaDTO.getStatus());

        if (propostaDTO.getTempoVoto() != null) {
            proposta.setTempoVoto(propostaDTO.getTempoVoto() * 60000L);
        } else {
            proposta.setTempoVoto(60000L);
        }

        proposta.setDataProposta(new Date());
        return propostaRepository.save(proposta);
    }

    public List<Proposta> listarPropostas() {
        return propostaRepository.findAll();
    }

    public Proposta buscarPorId(Long id) {
        return propostaRepository.findById(id).orElseThrow(
                () -> new IdPropostaNaoEncontradoException(String.format("Proposta id=%s não encontrada", id))
        );
    }

    public Proposta alterarProposta(Long idProposta, PropostaAtualizacaoDto propostaDTO) {
        Proposta propostaExistente = buscarPorId(idProposta);

        propostaExistente.setNome(propostaDTO.getNome());
        propostaExistente.setDescricao(propostaDTO.getDescricao());
        propostaExistente.setStatus(propostaDTO.getStatus());

        if (propostaDTO.getTempoVoto() != null) {
            propostaExistente.setTempoVoto(propostaDTO.getTempoVoto() * 60000L);
        }
        return propostaRepository.save(propostaExistente);
    }
    public void deletarProposta(Long id) {
        Proposta proposta = buscarPorId(id);
        propostaRepository.delete(proposta);
    }
}
