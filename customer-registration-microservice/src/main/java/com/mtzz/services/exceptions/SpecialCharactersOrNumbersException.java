package com.mtzz.services.exceptions;

public class SpecialCharactersOrNumbersException extends RuntimeException
{
    public SpecialCharactersOrNumbersException()
    {
        super("the name field contains special characters");
    }
}
