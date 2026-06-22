package com.enalab.board.auth.infrastructure.security;

import com.enalab.board.auth.infrastructure.security.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver exceptionResolver;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                                      ObjectMapper objectMapper,
                                                      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver,
                                                      JwtUtil jwtUtil) {
        super(authenticationConfiguration.getAuthenticationManager());
        this.objectMapper = objectMapper;
        this.exceptionResolver = exceptionResolver;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response){
        try {
            var loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            var token = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.username(),
                    loginRequest.password());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException | AuthenticationException e) {
            exceptionResolver.resolveException(request, response, null, e);
            return null;
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

    public record LoginRequest(
            @Schema(description = "Nome de usuário")
            String username,
            @Schema(description = "Senha do usuário")
            String password) {
    }

    public record LoginResponse(
            @Schema(description = "Token JWT gerado para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            String token
    ){}

    @RestController
    public static class LoginController {
        @PostMapping("/api/auth/login")
        @Operation(
                summary = "Realizar login na aplicação",
                description = "Endpoint interceptado pelo filtro de segurança para autenticação e geração do token JWT."
        )
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Login realizado com sucesso.",
                        content = @Content(schema = @Schema(implementation = LoginResponse.class))
                ),
        })
        public ResponseEntity<LoginResponse> login(
                @RequestBody(description = "Credenciais do usuário", required = true,
                        content = @Content(schema = @Schema(implementation = LoginRequest.class)))
                LoginRequest loginDto) {

            throw new IllegalStateException("Este método é interceptado pelo Spring Security.");
        }
    }

}
