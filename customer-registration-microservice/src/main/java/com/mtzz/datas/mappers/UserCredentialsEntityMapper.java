package com.mtzz.datas.mappers;

import com.mtzz.domains.models.UserCredentials;
import com.mtzz.datas.dto.UserCredentialsRequest;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCredentialsEntityMapper
{
    UserCredentialsEntityMapper INSTANCE = Mappers.getMapper(UserCredentialsEntityMapper.class);

    UserCredentials userCredentialsRequestToUserCredentials(UserCredentialsRequest providedUserCredentials);
}
