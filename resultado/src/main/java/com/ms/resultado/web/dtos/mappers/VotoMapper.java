package com.ms.resultado.web.dtos.mappers;

import com.ms.resultado.entities.Voto;
import com.ms.resultado.web.dtos.VotoCadastroDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class VotoMapper {
    public static Voto toVotoCadastrar(VotoCadastroDto votoCadastroDto) {
        return new ModelMapper().map(votoCadastroDto, Voto.class);
    }
    public static VotoCadastroDto toVotoCadastroDto(Voto voto) {
        return new ModelMapper().map(voto, VotoCadastroDto.class);
    }
}
