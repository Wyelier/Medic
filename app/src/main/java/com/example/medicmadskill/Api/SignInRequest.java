package com.example.medicmadskill.Api;

public class SignInRequest {
    private String email;

    private String code;

    public SignInRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
