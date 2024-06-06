package com.ms.funcionario.web.dtos;

import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FuncionarioRequestDto {
    @NonNull
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private Date dataNasc;
    @NonNull
    private String cpf;
    @NonNull
    private Enum sexo;
}
