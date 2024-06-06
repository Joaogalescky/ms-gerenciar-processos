package com.ms.proposta.web.dtos;

import com.ms.proposta.Enum.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaAtualizacaoDto {
    private String nome;
    private String descricao;
    private Status status;
    private Long tempoVoto;
}
