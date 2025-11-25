package com.example.login_project.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignupDto {
    private String email;
    private String password;
}
