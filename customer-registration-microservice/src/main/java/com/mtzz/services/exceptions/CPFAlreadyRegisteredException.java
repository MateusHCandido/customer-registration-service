package com.mtzz.services.exceptions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
public class CPFAlreadyRegisteredException extends RuntimeException
{
    private String reason = "The entered CPF is already registered";
    private HttpStatus status = HttpStatus.BAD_REQUEST;
}
