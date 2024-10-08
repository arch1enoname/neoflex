package com.arthur.neoflex.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CalculateException {
    String message;
    Throwable cause;
    HttpStatus httpStatus;
}
