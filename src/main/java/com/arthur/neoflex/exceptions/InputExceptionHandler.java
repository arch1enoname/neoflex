package com.arthur.neoflex.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@AllArgsConstructor
@Builder
@Data
@ControllerAdvice
public class InputExceptionHandler {

    @ExceptionHandler(value = {InputException.class})
    public ResponseEntity<Object> inputExceptionHandler(InputException inputException){
        CalculateException calculateException = new CalculateException(
                inputException.getMessage(),
                inputException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(calculateException, calculateException.getHttpStatus());
    }

}
