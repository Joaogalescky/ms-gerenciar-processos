package com.ms.proposta.repositories;

import com.ms.proposta.web.dtos.FuncionarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "funcionario-service", url = "http://localhost:8082/api/v1")
public interface FuncionarioClient {
    @GetMapping("/funcionarios/{id}")
    FuncionarioDto buscarPorId(@PathVariable("id") Long id);
}
