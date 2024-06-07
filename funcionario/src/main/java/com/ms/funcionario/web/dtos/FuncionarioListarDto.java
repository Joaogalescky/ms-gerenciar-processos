package com.ms.funcionario.web.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class FuncionarioListarDto {
    private String nome;
    private Date dataNasc;
    private String cpf;
    private String sexo;
}