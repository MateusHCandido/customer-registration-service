package com.mtzz.datas.repositories.impl;

import com.mtzz.domains.models.UserCredentials;
import com.mtzz.domains.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class UserCredentialsImpl
{
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;


    public void save(UserCredentials userCredentials)
    {
        userCredentialsRepository.save(userCredentials);
    }

    public Boolean existsByUsername(String username)
    {
        return userCredentialsRepository.existsByUsername(username);
    }

}
