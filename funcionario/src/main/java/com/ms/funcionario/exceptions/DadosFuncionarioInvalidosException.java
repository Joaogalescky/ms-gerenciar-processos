package com.ms.funcionario.exceptions;

public class DadosFuncionarioInvalidosException extends RuntimeException {
    public DadosFuncionarioInvalidosException(String message) {
        super(message);
    }
}