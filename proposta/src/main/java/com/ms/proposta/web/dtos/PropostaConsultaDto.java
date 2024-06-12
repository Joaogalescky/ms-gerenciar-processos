package com.ms.proposta.web.dtos;

import lombok.Getter;

@Getter
public class PropostaConsultaDto {
    private Long idProposta;
    private Long idFuncionario;
    private String nome;
    private String descricao;
}
