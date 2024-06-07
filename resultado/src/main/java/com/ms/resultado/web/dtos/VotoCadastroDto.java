package com.ms.resultado.web.dtos;

import com.ms.resultado.enums.Apuracao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class VotoCadastroDto {

    private Long id;
    @NonNull
    private Long idProposta;
    @NonNull
    private Long idFunc;
    @NonNull
    private Apuracao apuracao;
}
