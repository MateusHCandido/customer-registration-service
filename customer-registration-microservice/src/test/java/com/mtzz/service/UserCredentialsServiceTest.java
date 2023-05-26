package com.mtzz.service;

import com.mtzz.datas.dto.UserCredentialsRequest;
import com.mtzz.datas.repositories.impl.UserCredentialsImpl;
import com.mtzz.services.UserCredentialsService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserCredentialsServiceTest
{

    @TestConfiguration
    static class UserCredentialsServiceTestConfiguration
    {
        @Bean
        public UserCredentialsService userCredentialsService()
        {
            return new UserCredentialsService();
        }
    }


    @MockBean
    private UserCredentialsImpl userCredentialsImpl;

    @Autowired
    private UserCredentialsService userCredentialsService;


    @Test
    public void shouldCreateUserCredentials()
    {
        UserCredentialsRequest providedUserCredentials = new UserCredentialsRequest("mateustest", "1231234");
        userCredentialsService.registerUserCredentials(providedUserCredentials);

        when(userCredentialsImpl.existsByUsername("mateustest")).thenReturn(true);
        boolean assertion = userCredentialsImpl.existsByUsername("mateustest");

        assertTrue(assertion);
    }
}
