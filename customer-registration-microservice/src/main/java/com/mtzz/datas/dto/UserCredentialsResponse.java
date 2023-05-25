package com.mtzz.datas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsResponse
{
    private Long userId;
    private String username;
    private String password;
}
