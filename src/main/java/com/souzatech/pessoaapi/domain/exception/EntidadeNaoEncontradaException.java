package com.souzatech.pessoaapi.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message){
        super(message);
    }
}
