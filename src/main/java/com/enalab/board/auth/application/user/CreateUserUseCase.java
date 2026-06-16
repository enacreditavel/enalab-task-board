package com.enalab.board.auth.application.user;

import com.enalab.board.auth.application.user.input.CreateUserInput;
import com.enalab.board.auth.application.user.output.UserOutput;
import com.enalab.board.auth.domain.User;
import com.enalab.board.auth.domain.UserId;
import com.enalab.board.auth.domain.UserRepository;
import com.enalab.board.auth.infrastructure.secutiry.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserOutput execute(CreateUserInput input) {
        var user = User.builder()
                .userId(UserId.generate())
                .username(input.username())
                .email(input.email())
                .password(passwordEncoder.encode(input.password()))
                .name(input.name())
                .createdAt(Instant.now())
                .build();
        var saved = userRepository.save(user);
        return UserOutput.from(saved);
    }
}
