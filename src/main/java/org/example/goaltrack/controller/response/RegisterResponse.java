package org.example.goaltrack.controller.response;

import org.springframework.http.HttpStatus;

public record RegisterResponse(String message, HttpStatus status) {
}
