package com.enalab.board.auth.application.profile;

import com.enalab.board.auth.application.profile.output.ProfileOutput;
import com.enalab.board.auth.domain.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllProfilesUseCase {
    private final ProfileRepository profileRepository;

    @Transactional
    public List<ProfileOutput> execute(){
        return profileRepository.findAll().stream()
                .map(ProfileOutput::from)
                .toList();
    }

}
