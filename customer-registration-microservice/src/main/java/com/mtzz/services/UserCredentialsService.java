package com.mtzz.services;


import com.mtzz.datas.repositories.impl.UserCredentialsImpl;
import com.mtzz.domains.models.UserCredentials;
import com.mtzz.datas.dto.UserCredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mtzz.datas.mappers.UserCredentialsMapper.receiveFrom;
import static com.mtzz.services.validations.ValidationService.hasNoWhitespaceIn;


@Service
public class UserCredentialsService
{

    @Autowired
    private UserCredentialsImpl userCredentialsImpl;


    public void registerUserCredentials(UserCredentialsRequest providedUserCredentials)
    {
        String username = providedUserCredentials.getUsername();
        String password = providedUserCredentials.getPassword();

        if(!userCredentialsImpl.existsByUsername(username))
        {
            if(hasNoWhitespaceIn(username) || hasNoWhitespaceIn(password))
            {
                UserCredentials userCredentials = receiveFrom(providedUserCredentials);
                userCredentialsImpl.save(userCredentials);
            }
        }
    }
}
