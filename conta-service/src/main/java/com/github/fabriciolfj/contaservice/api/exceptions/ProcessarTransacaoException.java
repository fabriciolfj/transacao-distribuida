package com.github.fabriciolfj.contaservice.api.exceptions;

public class ProcessarTransacaoException extends RuntimeException {

    public ProcessarTransacaoException(final String msg) {
        super(msg);
    }
}
