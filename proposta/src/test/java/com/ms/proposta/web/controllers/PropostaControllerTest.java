package com.ms.proposta.web.controllers;

import com.ms.proposta.entities.Proposta;
import com.ms.proposta.services.PropostaService;
import com.ms.proposta.web.dtos.FuncionarioDto;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import com.ms.proposta.web.dtos.PropostaConsultaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropostaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PropostaService propostaService;

    @Test
    void cadastrarProposta() {
        PropostaCadastroDto propostaCadastroDto = new PropostaCadastroDto();
        propostaCadastroDto.setIdFuncionario(1L);
        propostaCadastroDto.setNome("Arrumar mesa");
        propostaCadastroDto.setDescricao("arrumar mesa sala 1");

        Proposta proposta = new Proposta();
        proposta.setIdFuncionario(1L);
        proposta.setNome("Arrumar mesa");
        proposta.setDescricao("arrumar mesa sala 1");
        proposta.setDataProposta(new Date());

        Mockito.when(propostaService.cadastrarProposta(Mockito.any(PropostaCadastroDto.class)))
                .thenReturn(proposta);

        ResponseEntity<Proposta> response = restTemplate.postForEntity("/api/v1/propostas", propostaCadastroDto, Proposta.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(propostaService, Mockito.times(1)).cadastrarProposta(Mockito.any(PropostaCadastroDto.class));
    }
    @Test
    void listarPropostas(){
        Proposta proposta1 = new Proposta();
        proposta1.setIdFuncionario(1L);
        proposta1.setNome("Arrumar mesa");
        proposta1.setDescricao("arrumar mesa sala 1");
        proposta1.setDataProposta(new Date());

        Proposta proposta2 = new Proposta();
        proposta2.setIdFuncionario(1L);
        proposta2.setNome("Arrumar mesa");
        proposta2.setDescricao("arrumar mesa sala 2");
        proposta2.setDataProposta(new Date());

        List<Proposta> propostas = List.of(proposta1, proposta2);

        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/propostas", List.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(propostaService, Mockito.times(1)).listarPropostas();
    }


}