package com.ms.resultado.repositories;

import com.ms.resultado.web.dtos.PropostaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "proposta-service", url = "http://localhost:8081/api/v1")
public interface PropostaClient {
    @GetMapping("/propostas/{id}")
    PropostaDto buscarPorId(@PathVariable("id") Long id);
}
