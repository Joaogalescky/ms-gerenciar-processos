package com.ms.funcionario.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(Long idFuncionario) {
        super("Funcionário id=" + idFuncionario + " não encontrado.");
    }
}
