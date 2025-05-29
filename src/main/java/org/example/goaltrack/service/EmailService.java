//package org.example.goaltrack.service;
//
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
//import com.sendgrid.helpers.mail.objects.Personalization;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.example.goaltrack.common.TokenType.ACCESS_TOKEN;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j(topic = "EMAIL-SERVICE")
//public class EmailService {
//
//    @Value("${spring.sendgrid.api-key}")
//    //@Value("${SENDGRID_API}")
//    private String sendGridApiKey;
//
//    @Value("${spring.sendgrid.from-email}")
//    //@Value("${SENDGRID_FROM_EMAIL}")
//    private String from;
//
//    @Value("${spring.sendgrid.templateId}")
//    private String templateId;
//
//    @Value("${spring.sendgrid.verificationLink}")
//    private String verificationLink;
//
//    private final SendGrid sendGrid;
//    private final JwtService jwtService;
//
//    /**
//     * Send email by SendGrid
//     * @param to send email to someone
//     * @param subject
//     * @param text
//     */
//    public void send(String to, String subject, String text) {
//        log.info("Email send started");
//        log.info("Current SendGrid API Key: {}", sendGridApiKey);
//
//        Email fromEmail = new Email(from);
//        Email toEmail = new Email(to);
//
//        Content content = new Content("text/plain", text);
//        Mail mail = new Mail(fromEmail, subject, toEmail, content);
//
//        Request request = new Request();
//
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//
//            Response response = sendGrid.api(request);
//            int statusCode = response.getStatusCode();
//            if (statusCode == 202) {
//                log.info("Email sent successfully to {}", to);
//            } else {
//                log.error("Email sent failed. Status: {}, Body: {}", statusCode, response.getBody());
//                throw new RuntimeException("SendGrid error: " + response.getBody());
//            }
//        } catch (IOException e) {
//            log.error("Email sent failed, exception: {}", e.getMessage(), e);
//            throw new RuntimeException("Email sending failed", e);
//        }
//    }
//
//    /**
//     * Email verification by SendGrid
//     * @param to
//     * @param name
//     * @throws IOException
//     */
//    public void emailVerification(String to, String name) throws IOException {
//        log.info("Email verification started");
//        Email fromEmail = new Email(from, "Duke Shelby");
//        Email toEmail = new Email(to, name);
//
//        String subject = "Xác thực tài khoản";
//
//        String secretCode = String.format( "?secretCode=%s", UUID.randomUUID());
//
//        // TODO generate secretCode and save to database
//
//        // Định nghĩa Template
//        Map<String, String> map = new HashMap<>();
//        map.put("name", name);
//        map.put("verification_link", verificationLink + secretCode);
//
//        Mail mail = new Mail();
//        mail.setFrom(fromEmail);
//        mail.setSubject(subject);
//
//        Personalization personalization = new Personalization();
//        personalization.addTo(toEmail);
//
//        // Add to dynamic data
//        map.forEach(personalization::addDynamicTemplateData);
//
//        mail.addPersonalization(personalization);
//        mail.setTemplateId(templateId);
//
//        Request request = new Request();
//        request.setMethod(Method.POST);
//        request.setEndpoint("mail/send");
//        request.setBody(mail.build());
//
//        log.info("Sending verification email to: {}", to);
//        log.info("Using template ID: {}", mail.getTemplateId());
//        log.info("Dynamic data: {}", map);
//
//        Response response = sendGrid.api(request);
//        log.info("SendGrid Response: Status={}, Body={}, Headers={}",
//                response.getStatusCode(), response.getBody(), response.getHeaders());
//
//        if (response.getStatusCode() == 202) {
//            log.info("Verification sent successfully");
//        } else {
//            log.error("Verification sent failed. Status: {}, Body: {}", response.getStatusCode(), response.getBody());
//            throw new RuntimeException("Verification email failed: " + response.getBody());
//        }
//
//    }
//
//}
package org.example.goaltrack.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.model.user.User;
import org.example.goaltrack.respository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.example.goaltrack.common.TokenType.ACCESS_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "EMAIL-SERVICE")
public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    //@Value("${SENDGRID_API}")
    private String sendGridApiKey;

    @Value("${spring.sendgrid.from-email}")
    //@Value("${SENDGRID_FROM_EMAIL}")
    private String from;

    @Value("${spring.sendgrid.templateId}")
    private String templateId;

    @Value("${spring.sendgrid.verificationLink}")
    private String verificationLink;

    private final SendGrid sendGrid;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    /**
     * Send email by SendGrid
     * @param to send email to someone
     * @param subject
     * @param text
     */
    public void send(String to, String subject, String text) {
        log.info("Email send started");
        log.info("Current SendGrid API Key: {}", sendGridApiKey);

        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);

        Content content = new Content("text/plain", text);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            int statusCode = response.getStatusCode();
            if (statusCode == 202) {
                log.info("Email sent successfully to {}", to);
            } else {
                log.error("Email sent failed. Status: {}, Body: {}", statusCode, response.getBody());
                throw new RuntimeException("SendGrid error: " + response.getBody());
            }
        } catch (IOException e) {
            log.error("Email sent failed, exception: {}", e.getMessage(), e);
            throw new RuntimeException("Email sending failed", e);
        }
    }

    /**
     * Email verification by SendGrid
     * @param to
     * @param name
     * @throws IOException
     */
    public void emailVerification(String to, String name) throws IOException {
        log.info("Email verification started");
        Email fromEmail = new Email(from, "Duke Shelby");
        Email toEmail = new Email(to, name);

        String subject = "Xác thực tài khoản";

        String secretCode = UUID.randomUUID().toString();

//        String secretCode = String.format( "?secretCode=%s", UUID.randomUUID());

        // TODO generate secretCode and save to database

        // Lưu secretCode vào database (thêm code này)
        try {
            User user = userRepository.findByEmail(to);
            if (user != null) {
                user.setSecretCode(secretCode);
                userRepository.save(user);
                log.info("Secret code saved for user: {}", to);
            } else {
                log.error("User not found with email: {}", to);
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            log.error("Failed to save secret code: {}", e.getMessage());
            throw new RuntimeException("Failed to save verification code", e);
        }

        // Định nghĩa Template (giữ code cũ, nhưng thay đổi URL)
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("verification_link", verificationLink + "?secretCode=" + secretCode);

        Mail mail = new Mail();
        mail.setFrom(fromEmail);
        mail.setSubject(subject);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);

        // Add to dynamic data
        map.forEach(personalization::addDynamicTemplateData);

        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        log.info("Sending verification email to: {}", to);
        log.info("Using template ID: {}", mail.getTemplateId());
        log.info("Dynamic data: {}", map);

        Response response = sendGrid.api(request);
        log.info("SendGrid Response: Status={}, Body={}, Headers={}",
                response.getStatusCode(), response.getBody(), response.getHeaders());

        if (response.getStatusCode() == 202) {
            log.info("Verification sent successfully");
        } else {
            log.error("Verification sent failed. Status: {}, Body: {}", response.getStatusCode(), response.getBody());
            throw new RuntimeException("Verification email failed: " + response.getBody());
        }

    }

}