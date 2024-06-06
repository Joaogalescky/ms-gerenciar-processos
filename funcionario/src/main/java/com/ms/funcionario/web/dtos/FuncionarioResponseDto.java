package com.ms.funcionario.web.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FuncionarioResponseDto {
    private Long id;
    private String nome;
    private Date dataNasc;
    private String cpf;
    private Enum sexo;
}
