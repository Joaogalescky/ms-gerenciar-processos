package com.ms.proposta.web.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuncionarioDto {
    private Long id;
    private String nome;
    private String dataNasc;
    private String cpf;
    private String sexo;
}
