package com.raven.calculator.dto;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String username;
    private String password;
    private String email;
}