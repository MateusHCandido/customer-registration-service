package com.mtzz.services.exceptions;

public class ExistingUsernameException extends RuntimeException
{
    public ExistingUsernameException(String username)
    {
        super("A user with login = " + username + " already exists");
    }
}
