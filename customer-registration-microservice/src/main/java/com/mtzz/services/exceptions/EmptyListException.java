package com.mtzz.services.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
public class EmptyListException extends RuntimeException
{
    private String reason = "Customer list is empty";
    private HttpStatus status = HttpStatus.NOT_FOUND;
}

