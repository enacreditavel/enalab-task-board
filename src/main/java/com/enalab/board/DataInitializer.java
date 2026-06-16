package com.enalab.board;

import com.enalab.board.auth.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder encoder;



    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            Permission permission = Permission.create("homelander");
            permissionRepository.save(permission);
            Profile profile =
                    Profile
                            .builder()
                            .profileId(ProfileId.generate())
                            .name("ENACREDITAVEL")
                            .build();
            profile.addPermission(permission);
            profileRepository.save(profile);
            User user = User.builder()
                    .userId(UserId.generate())
                    .username("ena")
                    .email("ena@enail.com")
                    .password(encoder.encode("ena"))
                    .name("enacreditavel")
                    .createdAt(Instant.now())
                    .build()
                    ;
            user.addProfile(profile);
            userRepository.save(user);
        };
    }
}
