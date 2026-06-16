package com.enalab.board.auth.infrastructure.secutiry;

import com.enalab.board.auth.infrastructure.exception.FiguringItOutException;
import com.enalab.board.auth.infrastructure.secutiry.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                                      ObjectMapper objectMapper,
                                                      JwtUtil jwtUtil) {
        super(authenticationConfiguration.getAuthenticationManager());
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            var loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            var token = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.username(),
                    loginRequest.password());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new FiguringItOutException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String token = jwtUtil.generateToken((CustomUserDetails) Objects.requireNonNull(authResult.getPrincipal()));
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        response.getWriter().flush();
    }

    public record LoginRequest(String username, String password) {
    }


}
