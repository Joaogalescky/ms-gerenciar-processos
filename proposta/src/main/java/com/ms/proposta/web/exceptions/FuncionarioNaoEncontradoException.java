package com.ms.proposta.web.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(Long idFuncionario) {
        super("Funcionário com o ID " + idFuncionario + " não encontrado.");
    }

}
