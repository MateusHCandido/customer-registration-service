package com.mtzz.services.exceptions;

public class CredentialsContainsBackSpacesException extends RuntimeException
{
    public CredentialsContainsBackSpacesException()
    {
        super("The password entered contains spaces");
    }
}
