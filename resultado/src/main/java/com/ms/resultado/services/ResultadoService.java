package com.ms.resultado.services;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.repositories.VotoRepository;
import com.ms.resultado.web.dtos.ResultadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResultadoService {
    @Autowired
    private VotoRepository votoRepository;

    @Transactional
    public ResultadoDto verResultado(Long idSessao) {
        List<Voto> votos = votoRepository.findByIdSessao(idSessao);

        int votosAprovado = 0;
        int votosReprovados = 0;

        for (Voto voto : votos) {
            if (voto.getEscolha() == Escolha.APROVADO) {
                votosAprovado++;
            } else if (voto.getEscolha() == Escolha.REPROVADO) {
                votosReprovados++;
            }
        }

        Escolha resultadoFinal;
        if (votosAprovado > votosReprovados) {
            resultadoFinal = Escolha.APROVADO;
        } else {
            resultadoFinal = Escolha.REPROVADO;
        }

        ResultadoDto resultadoDto = new ResultadoDto();
        resultadoDto.setNumeroSessao(idSessao);
        resultadoDto.setVotosAprovados(votosAprovado);
        resultadoDto.setVotosReprovados(votosReprovados);
        resultadoDto.setResultadoFinal(resultadoFinal);

        return resultadoDto;
    }
}
