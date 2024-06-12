package com.ms.resultado.services;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.enums.Escolha;
import com.ms.resultado.exceptions.EntidadeNaoEncontradaException;
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

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;


    @Transactional
    public ResultadoDto verResultado(Long idSessao) {

        sessaoVotacaoService.buscarSessaoPorId(idSessao);

        List<Voto> votos = votoRepository.findByIdSessao(idSessao);

        int votosAprovados = 0;
        int votosReprovados = 0;

        // Conta os votos aprovados e reprovados
        for (Voto voto : votos) {
            if (voto.getEscolha() == Escolha.APROVADO) {
                votosAprovados++;
            } else if (voto.getEscolha() == Escolha.REPROVADO) {
                votosReprovados++;
            }
        }

        Escolha resultadoFinal;
        if (votosAprovados > votosReprovados) {
            resultadoFinal = Escolha.APROVADO;
        } else {
            resultadoFinal = Escolha.REPROVADO;
        }

        ResultadoDto resultadoDto = new ResultadoDto();
        resultadoDto.setNumeroSessao(idSessao);
        resultadoDto.setVotosAprovados(votosAprovados);
        resultadoDto.setVotosReprovados(votosReprovados);
        resultadoDto.setResultadoFinal(resultadoFinal);

        return resultadoDto;
    }
}
