package com.ms.funcionario.web.dtos;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Data
public class FuncionarioCadastrarDto {
    @NonNull
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private Date dataNasc;
    @NonNull
    private String cpf;
    @NonNull
    private String sexo;
}