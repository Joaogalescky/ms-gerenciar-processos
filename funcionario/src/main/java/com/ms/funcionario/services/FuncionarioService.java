package com.ms.funcionario.services;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.repositories.FuncionarioRepository;
import com.ms.funcionario.web.exceptions.FuncionarioIdNaoEncontrado;
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

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(
                () -> new FuncionarioIdNaoEncontrado(String.format("Funcionario id=%s não encontrado", id))
        );
    }
}