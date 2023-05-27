package com.mtzz.service.validations;

import com.mtzz.datas.repositories.impl.UserCredentialsImpl;
import com.mtzz.services.exceptions.CredentialsContainsBackSpacesException;
import com.mtzz.services.exceptions.ExistingUsernameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService
{

    @Autowired
    private UserCredentialsImpl userCredentialsImpl;


    public static boolean hasNoWhitespaceIn(String credentials)
    {
        if(!credentials.contains(" "))
        {
            return true;
        }
        throw new CredentialsContainsBackSpacesException();
    }

    public boolean isUsernameTaken(String username)
    {
        boolean validateExistence = userCredentialsImpl.existsByUsername(username);

        if(validateExistence)
        {
            throw new ExistingUsernameException(username);
        }
        return false;
    }
}
