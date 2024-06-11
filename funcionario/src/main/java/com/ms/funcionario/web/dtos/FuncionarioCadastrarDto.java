package com.ms.funcionario.web.dtos;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FuncionarioCadastrarDto {
    @NonNull
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String dataNasc;
    @NonNull
    private String cpf;
    @NonNull
    private String sexo;


}