package org.example.goaltrack.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.common.TokenType;
import org.example.goaltrack.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class Authenticator implements HandlerInterceptor {

    final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        String username = null;
        try {
            username = jwtService.extractUsername(accessToken, TokenType.ACCESS_TOKEN);
            if (username != null) { // TODO: impl true logic
                return true;
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            log.error("Access token is corrupted.", e);
        }
        return false;
    }
}
