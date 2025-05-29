package org.example.goaltrack.controller.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SignInRequest implements Serializable {
    private String username;
    private String email;
    private String password;
    private String platform; // web, mobile, miniApp
    private String deviceToken;
    private String versionApp;
}
