package com.ms.resultado.web.dtos;

import com.ms.resultado.enums.StatusSessao;
import lombok.Data;

@Data
public class PropostaDto {
    private Long idProposta;
    private Long idFuncionario;
    private String nome;
    private String descricao;
    private String tempoVoto;
    private StatusSessao status;
}
