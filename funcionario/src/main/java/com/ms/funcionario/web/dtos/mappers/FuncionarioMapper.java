package com.ms.funcionario.web.dtos.mappers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.web.dtos.FuncionarioResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public static Funcionario toFuncionario(FuncionarioResponseDto createDto) {
        return new ModelMapper().map(createDto, Funcionario.class);
    }

    public static FuncionarioResponseDto toDto(Funcionario funcionario) {
        return new ModelMapper().map(funcionario, FuncionarioResponseDto.class);
    }
}
