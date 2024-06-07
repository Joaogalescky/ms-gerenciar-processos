package com.ms.proposta.services;

import com.ms.proposta.Enum.Status;
import com.ms.proposta.entities.Proposta;
import com.ms.proposta.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AgendadorProposta {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private final PropostaRepository propostaRepository;

    public void scheduleStatusChange(Proposta proposta) {
        Runnable tarefa = () -> {
            proposta.setStatus(Status.INATIVO);
            propostaRepository.save(proposta);
        };

        long tempo = proposta.getTempoVoto();
        scheduler.schedule(tarefa, tempo, TimeUnit.MILLISECONDS);
    }
}
