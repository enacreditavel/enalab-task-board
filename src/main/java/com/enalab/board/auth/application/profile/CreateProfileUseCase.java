package com.enalab.board.auth.application.profile;

import com.enalab.board.auth.application.profile.input.CreateProfileInput;
import com.enalab.board.auth.application.profile.output.ProfileOutput;
import com.enalab.board.auth.domain.Profile;
import com.enalab.board.auth.domain.ProfileId;
import com.enalab.board.auth.domain.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateProfileUseCase {
    private final ProfileRepository profileRepository;

    @Transactional
    public ProfileOutput execute(CreateProfileInput input){
        return ProfileOutput.from(
                profileRepository.save(
                        Profile.builder()
                                .profileId(ProfileId.generate())
                                .name(input.name())
                                .build()));
    }
}
