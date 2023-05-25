package com.mtzz.datas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsRequest
{

    @NotEmpty(message = "username field is mandatory")
    private String username;

    @NotEmpty(message = "password field is mandatory")
    private String password;
}
