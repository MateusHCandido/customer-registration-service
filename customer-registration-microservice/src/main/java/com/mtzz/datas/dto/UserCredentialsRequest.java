package com.mtzz.datas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsRequest
{

    @NotEmpty(message = "username field is mandatory")
    @Size(max = 12, message = "Username must contain up to 12 characters")
    private String username;

    @NotEmpty(message = "password field is mandatory")
    @Size(max = 15, message = "The password must contain up to 15 characters")
    private String password;
}
