package com.ms.proposta.web.dtos;

import com.ms.proposta.Enum.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaCadastroDto {
    private String nome;
    private String descricao;
    private String tempoVoto;
}
