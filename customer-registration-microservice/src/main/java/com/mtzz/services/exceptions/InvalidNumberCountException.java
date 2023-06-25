package com.mtzz.services.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class InvalidNumberCountException extends RuntimeException
{
    private String reason = "The amount of values passed to the field is invalid";
    private HttpStatus status = HttpStatus.BAD_REQUEST;
}
