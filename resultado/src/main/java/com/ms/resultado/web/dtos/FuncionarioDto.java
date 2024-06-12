package com.ms.resultado.web.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {
    private Long id;
    private String nome;
    private String dataNasc;
    private String cpf;
    private String sexo;
}
