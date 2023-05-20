package com.mtzz.datas.mappers;

import com.mtzz.domains.models.UserCredentials;
import com.mtzz.datas.dto.UserCredentialsRequest;
import com.mtzz.datas.dto.UserCredentialsResponse;

public class UserCredentialsMapper
{
    public static UserCredentials receiveFrom(UserCredentialsRequest userCredentialsRequest)
    {
        UserCredentials userCredentials;

        userCredentials = UserCredentialsEntityMapper
                .INSTANCE
                .userCredentialsRequestToUserCredentials(userCredentialsRequest);

        return userCredentials;
    }

    public static UserCredentialsResponse ResponseOf(UserCredentials userCredentials)
    {
        UserCredentialsResponse userCredentialsResponse;

        userCredentialsResponse = UserCredentialsResponseMapper
                .INSTANCE
                .userCredentialsToUserCredentialsResponse(userCredentials);

        return userCredentialsResponse;
    }
}
