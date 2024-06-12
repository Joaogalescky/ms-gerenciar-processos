package com.ms.resultado.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IniciarVotacaoDto {
    private Long idProposta;
    private String tempoVotacao;
}