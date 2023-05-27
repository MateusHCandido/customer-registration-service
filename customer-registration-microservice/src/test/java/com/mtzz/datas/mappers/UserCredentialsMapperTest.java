package com.mtzz.datas.mappers;


import com.mtzz.datas.dto.UserCredentialsRequest;
import com.mtzz.datas.dto.UserCredentialsResponse;
import com.mtzz.domains.models.UserCredentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class UserCredentialsMapperTest
{
    @Test
    public void shouldConvertUserCredentialsRequestToUserCredentialsEntity()
    {
        UserCredentialsRequest userCredentialsRequest = new UserCredentialsRequest("mateuscds", "1231234");

        UserCredentials userCredentials = UserCredentialsMapper.receiveFrom(userCredentialsRequest);

        assertEquals(userCredentials.getUsername(), "mateuscds");
        assertEquals(userCredentials.getPassword(), "1231234");
    }

    @Test
    public void shouldConvertUserCredentialsEntityToUserCredentialsResponse()
    {
        UserCredentials userCredentials = new UserCredentials(1L, "mateuscds", "1231234");

        UserCredentialsResponse userCredentialsResponse = UserCredentialsMapper.responseOf(userCredentials);


        assertEquals(userCredentials.getUsername(), "mateuscds");
        assertEquals(userCredentials.getPassword(), "1231234");
    }
}
