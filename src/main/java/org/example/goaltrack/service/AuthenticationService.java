package org.example.goaltrack.service;

import org.example.goaltrack.controller.request.SignInRequest;
import org.example.goaltrack.controller.response.TokenResponse;
import org.hibernate.annotations.Filter;

@Filter(name="Authentication")
public interface AuthenticationService {

    TokenResponse getAccessToken(SignInRequest request);

    TokenResponse getRefreshToken(String request);
}
