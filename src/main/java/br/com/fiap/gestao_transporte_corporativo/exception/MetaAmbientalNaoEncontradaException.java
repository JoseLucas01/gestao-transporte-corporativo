package br.com.fiap.gestao_transporte_corporativo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetaAmbientalNaoEncontradaException extends RuntimeException {
    public MetaAmbientalNaoEncontradaException(String message) {
        super(message);
    }
}
