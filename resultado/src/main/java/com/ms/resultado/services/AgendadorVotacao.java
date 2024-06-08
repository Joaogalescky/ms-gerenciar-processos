package com.ms.resultado.services;

import com.ms.resultado.entities.SessaoVotacao;
import com.ms.resultado.enums.StatusSessao;
import com.ms.resultado.repositories.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AgendadorVotacao {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public void scheduleStatusChange(SessaoVotacao sessaoVotacao) {
        Runnable tarefa = () -> {
            sessaoVotacao.setStatus(StatusSessao.INATIVO);
            sessaoVotacaoRepository.save(sessaoVotacao);
        };

        long tempo = sessaoVotacao.getTempoVotacao();
        scheduler.schedule(tarefa, tempo, TimeUnit.MILLISECONDS);
    }
}
