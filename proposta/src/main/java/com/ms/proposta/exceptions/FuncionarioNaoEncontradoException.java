package com.ms.proposta.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(Long idFuncionario) {
        super("Funcionário com o ID " + idFuncionario + " não encontrado.");
    }

}
