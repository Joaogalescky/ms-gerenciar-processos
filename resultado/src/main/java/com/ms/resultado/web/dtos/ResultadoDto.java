package com.ms.resultado.web.dtos;

import com.ms.resultado.enums.Escolha;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultadoDto {

    private Long numeroSessao;
    private int votosAprovados;
    private int votosReprovados;
    private Escolha resultadoFinal;
}
