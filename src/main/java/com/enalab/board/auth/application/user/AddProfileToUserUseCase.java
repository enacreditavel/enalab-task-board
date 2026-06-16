package com.enalab.board.auth.application.user;

import com.enalab.board.auth.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddProfileToUserUseCase {
    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    public void execute(UUID userId, UUID profileId){
        Profile profile = profileRepository.findById(ProfileId.generate(profileId));
        log.info("Profile found: {}", profile.getName());

        User user = userRepository.findById(UserId.generate(userId));
        log.info("User found: {}", user.getUsername());

        user.addProfile(profile);
        log.info("Profile added to user: {}", user.getProfiles().stream().map(Profile::getName).toList());

        userRepository.save(user);
        log.info("User saved: {}", user.getUsername());
    }

}
