package com.enalab.board.auth.application.user;

import com.enalab.board.auth.domain.ProfileId;
import com.enalab.board.auth.domain.UserId;
import com.enalab.board.auth.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RemoveProfileFromUserUseCase {
    private final UserRepository userRepository;

    @Transactional
    public void execute(UUID userId, UUID profileId) {
        var user = userRepository.findById(UserId.generate(userId));
        user.removeProfile(ProfileId.generate(profileId));
    }
}
