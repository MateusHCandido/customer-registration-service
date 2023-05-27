package com.mtzz.services.exceptions;

public class InvalidNumberCountException extends RuntimeException
{
    public InvalidNumberCountException()
    {
        super("The amount of values passed to the field is invalid");
    }
}
