package com.ms.resultado.web.dtos;

import com.ms.resultado.enums.StatusSessao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class PropostaDto {
    private Long idProposta;
    private Long idFuncionario;
    private String nome;
    private String descricao;
}
