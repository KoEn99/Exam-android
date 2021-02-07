package com.koen.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String role;
}
