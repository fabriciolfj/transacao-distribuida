package com.github.fabriciolfj.contaservice.api.exceptions;

public class SaldoNegativoException extends RuntimeException {

    public SaldoNegativoException(final String msg) {
        super(msg);
    }
}
