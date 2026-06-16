package com.enalab.board.auth.application.profile;

import com.enalab.board.auth.domain.ProfileId;
import com.enalab.board.auth.domain.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RemovePermissionFromProfileUseCase {
    private final ProfileRepository profileRepository;

    @Transactional
    public void execute(UUID profileId, String permissionName){
        var profile = profileRepository.findById(ProfileId.generate(profileId));
        profile.removePermission(permissionName);
    }
}
