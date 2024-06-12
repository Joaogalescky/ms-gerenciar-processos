package com.ms.resultado.web.dtos;

import com.ms.resultado.enums.Escolha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class VotoCadastroDto {

    private Long id;
    @NonNull
    private Long idSessao;
    @NonNull
    private Long idFunc;
    @NonNull
    private Escolha escolha;
}
