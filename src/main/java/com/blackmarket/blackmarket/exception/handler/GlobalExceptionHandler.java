package com.blackmarket.blackmarket.exception.handler;

import com.blackmarket.blackmarket.exception.exceptions.EntidadeNaoEncontradaException;
import com.blackmarket.blackmarket.exception.exceptions.ErrorDeParametroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<EntidadeNaoEncontradaException> entidadeNaoEncontradaResponseEntity(EntidadeNaoEncontradaException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(ErrorDeParametroException.class)
    public ResponseEntity<ErrorDeParametroException> errorDeParametroResponseEntity(ErrorDeParametroException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }

}
