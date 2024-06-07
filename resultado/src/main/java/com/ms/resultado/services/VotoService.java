package com.ms.resultado.services;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.repositories.VotoRepository;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import com.ms.resultado.web.dtos.mappers.VotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VotoService {

    @Autowired
    private final VotoRepository votoRepository;

    @Transactional
    public Voto votoCadastrar(VotoCadastroDto votoCadastroDto) {
        Voto voto = VotoMapper.toVotoCadastrar(votoCadastroDto);
        return votoRepository.save(voto);
    }

}
