package org.example.goaltrack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.controller.request.EmailRequest;
import org.example.goaltrack.service.EmailService;
import org.hibernate.annotations.Filter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "EMAIL-CONTROLLER")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public void send(@RequestBody EmailRequest request) {
        log.info("Sending email to {}", request.getTo());
        emailService.send(request.getTo(), request.getSubject(), request.getContent());
        log.info("Email sent successfully");
    }

    @PostMapping("/verify-email")
    public void emailVerification(@RequestParam String to, @RequestParam String name) throws IOException {
        log.info("Verification email to {}", to);
        emailService.emailVerification(to, name);
    }
}
