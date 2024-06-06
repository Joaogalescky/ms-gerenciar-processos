package com.ms.funcionario.services;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.repositories.FuncionarioRepository;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import com.ms.funcionario.exceptions.FuncionarioIdNaoEncontrado;
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
        Funcionario funcionario = FuncionarioMapper.toFuncionarioCadastrar(funcionarioCadastrarDto);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(
                () -> new FuncionarioIdNaoEncontrado(String.format("Funcionario id=%s não encontrado", id))
        );
    }
    @Transactional
    public Funcionario alterarFuncionario(Long idFunc, FuncionarioListarDto funcionarioListarDto) {
      Funcionario funcionario = buscarPorId(idFunc);
      funcionario.setNome(funcionarioListarDto.getNome());
      funcionario.setDataNasc(funcionarioListarDto.getDataNasc());
      funcionario.setCpf(funcionarioListarDto.getCpf());
      funcionario.setSexo(funcionarioListarDto.getSexo());
      return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id){
        Funcionario funcionario = buscarPorId(id);
       funcionarioRepository.delete(funcionario);
    }
}