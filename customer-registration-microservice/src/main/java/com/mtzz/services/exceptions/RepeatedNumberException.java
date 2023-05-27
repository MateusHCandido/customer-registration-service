package com.mtzz.services.exceptions;

public class RepeatedNumberException extends RuntimeException
{
    public RepeatedNumberException()
    {
        super("all cpf numbers are the same");
    }
}
