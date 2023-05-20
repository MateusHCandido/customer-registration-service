package com.mtzz.datas.resources;

import com.mtzz.datas.dto.UserCredentialsRequest;
import com.mtzz.services.UserCredentialsService;
import com.mtzz.services.exceptions.ExistingUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
public class UserCredentialsResource
{
    private final UserCredentialsService userCredentialsService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserCredentials(@RequestBody @Valid UserCredentialsRequest providedUserCredentials)
    {
        try
        {
            userCredentialsService.registerUserCredentials(providedUserCredentials);
        }
        catch (ExistingUsernameException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
