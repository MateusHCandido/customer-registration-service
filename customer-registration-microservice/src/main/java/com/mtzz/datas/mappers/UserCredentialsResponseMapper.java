package com.mtzz.datas.mappers;

import com.mtzz.domains.models.UserCredentials;
import com.mtzz.datas.dto.UserCredentialsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCredentialsResponseMapper
{
    UserCredentialsResponseMapper INSTANCE = Mappers.getMapper(UserCredentialsResponseMapper.class);

    UserCredentialsResponse userCredentialsToUserCredentialsResponse(UserCredentials userCredentials);
}
