package com.tailan.investimentos.exceptions;

import com.tailan.investimentos.model.dtos.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExceptionHandleGlobal {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException ex) {
        ErrorResponse newError = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Usúario não encontrado.", ex.getMessage());
        return new ResponseEntity<>(newError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailExistsException.class)
    public ResponseEntity<ErrorResponse> userEmailExistsException(UserEmailExistsException ex) {
        ErrorResponse newError = new ErrorResponse(LocalDateTime.now(), HttpStatus.NO_CONTENT.value(), "Email já cadastrado.", ex.getMessage());
        return new ResponseEntity<>(newError, HttpStatus.BAD_REQUEST);
    }
}
