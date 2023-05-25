package com.mtzz.domains.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentials
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{mandatory.login.field}")
    private String username;

    @NotEmpty(message = "{mandatory.password.field}")
    private String password;


    public UserCredentials (UserCredentials userCredentials)
    {
        this.userId = userCredentials.getUserId();
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
    }
}
