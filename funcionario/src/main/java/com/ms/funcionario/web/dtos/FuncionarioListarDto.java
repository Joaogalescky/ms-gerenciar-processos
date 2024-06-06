package com.ms.funcionario.web.dtos;

import lombok.Data;


@Data
public class FuncionarioListarDto {
    private String nome;
    private String dataNasc;
    private String cpf;
    private String sexo;
}
