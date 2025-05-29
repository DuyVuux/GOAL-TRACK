package org.example.goaltrack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.controller.request.UserCreationRequest;
import org.example.goaltrack.controller.response.RegisterResponse;
import org.example.goaltrack.service.EmailService;
import org.example.goaltrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RegisterController {
    final UserService userService;
    final EmailService emailService;

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserCreationRequest request) {
        try {
            userService.save(request);
            emailService.emailVerification(request.getEmail(), request.getUsername());
        } catch (IOException e) {
            log.error("Send email verification failure!", e);
            return ResponseEntity.ofNullable(new RegisterResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ofNullable(new RegisterResponse("Please confirm your email to activate this user", HttpStatus.ACCEPTED));
    }

}
