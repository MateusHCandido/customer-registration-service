package com.mtzz.services.validations;


import com.mtzz.datas.repositories.impl.UserCredentialsImpl;
import com.mtzz.domains.models.UserCredentials;
import com.mtzz.services.exceptions.CredentialsContainsBackSpacesException;
import com.mtzz.services.exceptions.ExistingUsernameException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;


import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class ValidationServiceTest extends ValidationService
{

    @TestConfiguration
    static class ValidationServiceTestConfiguration
    {
        @Bean
        public ValidationService validationService()
        {
            return new ValidationService();
        }

    }

    @Autowired
    private ValidationService validationService;

    @MockBean
    private UserCredentialsImpl userCredentialsImpl;


    @Test
    @DisplayName(value = "")
    public void shouldReturnTrueWhenHasNoWhitespacesInUsernameOrPasswordField()
    {
        String username = "username_test";
        String password = "Mp123!@#_";
        boolean validateFields = hasNoWhitespaceIn(username) || hasNoWhitespaceIn(password);

        Assert.assertTrue(validateFields);
    }

    @Test(expected = CredentialsContainsBackSpacesException.class)
    public void shouldThrowAnExceptionWhenHasWhiteSpacesInUsernameOrPasswordField()
    {
        String username = "user  name";
        String password = "Mp123  !@#_";
        boolean validateFields = hasNoWhitespaceIn(username) || hasNoWhitespaceIn(password);

        Assert.assertTrue(validateFields);
    }

    @Test
    public void shouldReturnFalseWhenThereIsNotUsernameBeingUser()
    {
        String username = "username_test";
        boolean validateUsernameField = true;

        Mockito.when(userCredentialsImpl.existsByUsername(username)).thenReturn(false);

        validateUsernameField = userCredentialsImpl.existsByUsername(username);

        Assert.assertFalse(validateUsernameField);
    }

    @Test(expected = ExistingUsernameException.class)
    public void shouldThrowAnExceptionWhenUsernameIsExisting()
    {
        String username = "username_test";
        String password = "Mp123!@#_";

        UserCredentials userCredentials = new UserCredentials(1L, username, password);
        userCredentialsImpl.save(userCredentials);

        Mockito.when(userCredentialsImpl.existsByUsername(username)).thenReturn(true);
        boolean validateUsernameField = isUsernameTaken(username);
    }
}

