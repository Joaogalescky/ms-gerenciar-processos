package com.ms.proposta.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaCadastroDto {
    private Long idFuncionario;
    private String nome;
    private String descricao;
}
