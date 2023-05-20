package com.mtzz.datas.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class UserCredentialsRequest
{
    @Column(unique = true, name = "login")
    @NotEmpty(message = "{mandatory.login.field}")
    private String username;

    @NotEmpty(message = "{mandatory.password.field}")
    private String password;
}
