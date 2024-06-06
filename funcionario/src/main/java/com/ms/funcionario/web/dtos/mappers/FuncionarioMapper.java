package com.ms.funcionario.web.dtos.mappers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public static Funcionario toFuncionarioCadastrar(FuncionarioCadastrarDto createDto) {
        return new ModelMapper().map(createDto, Funcionario.class);
    }

    public static FuncionarioCadastrarDto toDtoCadastrar(Funcionario funcionario) {
        return new ModelMapper().map(funcionario, FuncionarioCadastrarDto.class);
    }

    public static Funcionario toFuncionarioListar(FuncionarioListarDto funcionarioListarDto) {
        return new ModelMapper().map(funcionarioListarDto, Funcionario.class);
    }
    public static FuncionarioListarDto toListDto(Funcionario funcionario) {
        return new ModelMapper().map(funcionario, FuncionarioListarDto.class);
    }

}
