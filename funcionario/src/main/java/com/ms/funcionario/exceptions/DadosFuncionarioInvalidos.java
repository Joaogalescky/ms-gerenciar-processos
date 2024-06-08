package com.ms.funcionario.exceptions;

public class DadosFuncionarioInvalidos extends RuntimeException {
    public DadosFuncionarioInvalidos(String message) {
        super(message);
    }
}