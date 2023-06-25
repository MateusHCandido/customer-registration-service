package com.mtzz.services.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class SpecialCharactersOrNumbersException extends RuntimeException
{
    private String reason = "The name field contains special characters";
    private  HttpStatus status = HttpStatus.BAD_REQUEST;
}
