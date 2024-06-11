package com.ms.funcionario.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class FuncionarioListarDto {
    private String nome;
    private String dataNasc;
    private String cpf;
    private String sexo;

    public FuncionarioListarDto(){

    }
}