package com.mtzz.datas.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@ControllerAdvice
public class ResourceExceptionHandler
{
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(CustomerNotFoundException exception,
                                                          HttpServletRequest httpRequest)
    {
        String errorMessage = "Customer not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(),
                errorMessage, exception.getMessage(),httpRequest.getRequestURI());

        return ResponseEntity.status(httpStatus).body(standardError);
    }


}
