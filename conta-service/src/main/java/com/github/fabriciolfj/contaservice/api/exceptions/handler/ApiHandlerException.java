package com.github.fabriciolfj.contaservice.api.exceptions.handler;

import com.github.fabriciolfj.contaservice.api.exceptions.ProcessarTransacaoException;
import com.github.fabriciolfj.contaservice.api.exceptions.SaldoNegativoException;
import com.github.fabriciolfj.contaservice.api.exceptions.dto.Problem;
import com.github.fabriciolfj.contaservice.api.exceptions.dto.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProcessarTransacaoException.class)
    public ResponseEntity<?> handleProcessarTransacaoException(final ProcessarTransacaoException e, final WebRequest request) {
        final var response = createDto(e);
        return super.handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SaldoNegativoException.class)
    public ResponseEntity<?> handleSaldoNegativoException(final SaldoNegativoException e, final WebRequest request) {
        final var response = createDto(e);
        return super.handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(final Exception ex,
                                                            final BindingResult bindingResult,
                                                            final HttpHeaders headers,
                                                            final HttpStatus httpStatus,
                                                            final WebRequest request) {

        final List<ResponseError> erros = bindingResult.getAllErrors()
                .stream()
                .map(obj -> ResponseError
                            .builder()
                            .mensagem(obj.getDefaultMessage())
                            .data(OffsetDateTime.now())
                            .build())
                .collect(Collectors.toList());

        final var problem = Problem.builder()
                .erros(erros)
                .build();

        return super.handleExceptionInternal(ex, problem, headers, httpStatus, request);
    }
    private ResponseError createDto(final Exception e) {
        return ResponseError
                .builder()
                .data(OffsetDateTime.now())
                .mensagem(e.getMessage())
                .build();
    }
}
