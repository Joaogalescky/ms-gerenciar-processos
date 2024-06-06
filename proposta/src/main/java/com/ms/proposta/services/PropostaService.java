package com.ms.proposta.services;

import com.ms.proposta.entities.Proposta;
import com.ms.proposta.exceptions.IdPropostaNaoEncontradoException;
import com.ms.proposta.repositories.PropostaRepository;
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

    public Proposta cadastrarProposta(Proposta proposta) {
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


}
