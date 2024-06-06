package com.ms.proposta.services;

import com.ms.proposta.entities.Proposta;
import com.ms.proposta.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {
    @Autowired
    private final PropostaRepository propostaRepository;

    public Proposta cadastrarProposta (Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public List <Proposta> listarPropostas() {
        return propostaRepository.findAll();
    }

}
