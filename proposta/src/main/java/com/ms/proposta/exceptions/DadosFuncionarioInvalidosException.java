package com.ms.proposta.exceptions;

public class DadosFuncionarioInvalidosException extends RuntimeException{
    public DadosFuncionarioInvalidosException(String mensagem) {
        super(mensagem);
    }
}
