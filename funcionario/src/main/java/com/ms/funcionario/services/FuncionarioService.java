package com.ms.funcionario.services;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.exceptions.DadosFuncionarioInvalidosException;
import com.ms.funcionario.repositories.FuncionarioRepository;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public Funcionario cadastrarFuncionario(FuncionarioCadastrarDto funcionarioCadastrarDto) {
        if (funcionarioCadastrarDto.getNome().isEmpty() || funcionarioCadastrarDto.getCpf().isEmpty() || funcionarioCadastrarDto.getSexo().isEmpty()
        ) {
            throw new DadosFuncionarioInvalidosException("Dados obrigatórios do funcionário estão imcompletos ou inválidos");
        }

        String cpf = funcionarioCadastrarDto.getCpf().replaceAll("\\D", "");

        if (cpf.length() != 11) {
            throw new DadosFuncionarioInvalidosException("CPF deve ter 11 dígitos");
        }

        if (funcionarioRepository.existsByCpf(cpf)) {
            throw new DadosFuncionarioInvalidosException(String.format("Funcionario CPF=%s já existente", cpf));
        }

        Funcionario funcionario = FuncionarioMapper.toFuncionarioCadastrar(funcionarioCadastrarDto);
        funcionario.setCpf(cpf);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(
                () -> new DadosFuncionarioInvalidosException(String.format("Funcionario id=%s não encontrado", id))
        );
    }

    @Transactional
    public Funcionario alterarFuncionario(Long idFunc, FuncionarioListarDto funcionarioListarDto) {
        Funcionario funcionario = buscarPorId(idFunc);

        if (funcionarioListarDto.getNome().isEmpty()
                || funcionarioListarDto.getCpf().isEmpty()
                || funcionarioListarDto.getDataNasc() == null
                || funcionarioListarDto.getSexo().isEmpty()) {
            throw new DadosFuncionarioInvalidosException("Dados obrigatórios do funcionário estão imcompletos ou inválidos");
        }

        String novoCpf = funcionarioListarDto.getCpf().replaceAll("\\D", "");

        if (novoCpf.length() != 11) {
            throw new DadosFuncionarioInvalidosException("CPF deve ter 11 dígitos");
        }

        if (!funcionario.getCpf().equals(novoCpf) && funcionarioRepository.existsByCpf(novoCpf)) {
            throw new DadosFuncionarioInvalidosException("CPF já existente");
        }

        funcionario.setNome(funcionarioListarDto.getNome());
        funcionario.setDataNasc(funcionarioListarDto.getDataNasc());
        funcionario.setCpf(novoCpf);
        funcionario.setSexo(funcionarioListarDto.getSexo());
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(
                () -> new DadosFuncionarioInvalidosException(String.format("Funcionario id=%s não encontrado", id))
        );
        funcionarioRepository.delete(funcionario);
    }
}