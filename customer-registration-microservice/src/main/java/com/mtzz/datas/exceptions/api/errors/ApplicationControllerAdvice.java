package com.mtzz.datas.exceptions.api.errors;

import com.mtzz.datas.exceptions.CustomerNotFoundException;
import com.mtzz.services.exceptions.CPFAlreadyRegisteredException;
import com.mtzz.services.exceptions.EmptyListException;
import com.mtzz.services.exceptions.InvalidNumberCountException;
import com.mtzz.services.exceptions.SpecialCharactersOrNumbersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException exception)
    {
        BindingResult bindingResult = exception.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException exception)
    {
        String errorMessage = exception.getReason();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors apiErrors = new ApiErrors(errorMessage);
        return new ResponseEntity(apiErrors, statusCode);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity customerNotFoundException(CustomerNotFoundException exception)
    {
        String errorMessage = exception.getReason();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors apiErrors = new ApiErrors(errorMessage);
        return new ResponseEntity(apiErrors, statusCode);
    }

    @ExceptionHandler(EmptyListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity emptyListException(EmptyListException exception)
    {
        String errorMessage = exception.getReason();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors apiErrors = new ApiErrors(errorMessage);
        return new ResponseEntity(apiErrors, statusCode);
    }


    @ExceptionHandler(CPFAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity cpfAlreadyRegisteredException(CPFAlreadyRegisteredException exception)
    {
        String errorMessage = exception.getReason();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors apiErrors = new ApiErrors(errorMessage);
        return new ResponseEntity(apiErrors, statusCode);
    }

    @ExceptionHandler(SpecialCharactersOrNumbersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity specialCharactersOrNumbersException(SpecialCharactersOrNumbersException exception)
    {
        String errorMessage = exception.getReason();
        HttpStatus statusCode = exception.getStatus();
        ApiErrors apiErrors = new ApiErrors(errorMessage);
        return new ResponseEntity(apiErrors, statusCode);
    }


}
