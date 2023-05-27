package com.mtzz.services.exceptions;

public class CPFAlreadyRegisteredException extends RuntimeException
{
    public CPFAlreadyRegisteredException()
    {
        super("The entered cpf is already registered");
    }
}
