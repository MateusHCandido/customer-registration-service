package com.mtzz.datas.mappers;

import com.mtzz.domains.models.UserCredentials;
import com.mtzz.datas.dto.UserCredentialsRequest;
import com.mtzz.datas.dto.UserCredentialsResponse;


public class UserCredentialsMapper
{

    public static UserCredentials receiveFrom(UserCredentialsRequest userCredentialsRequest)
    {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(userCredentialsRequest.getUsername());
        userCredentials.setPassword(userCredentialsRequest.getPassword());

        return userCredentials;
    }



    public static UserCredentialsResponse ResponseOf(UserCredentials userCredentials)
    {
        UserCredentialsResponse userCredentialsResponse = new UserCredentialsResponse();

        userCredentialsResponse.setUserId(userCredentials.getUserId());
        userCredentialsResponse.setUsername(userCredentials.getUsername());
        userCredentialsResponse.setPassword(userCredentials.getPassword());

        return userCredentialsResponse;
    }
}
