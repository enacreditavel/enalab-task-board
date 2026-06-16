package com.enalab.board.auth.application.profile;

import com.enalab.board.auth.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddPermissionToProfileUseCase {
    private final PermissionRepository permissionRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public void execute(UUID profileId, String permissionName){
        Profile profile = profileRepository.findById(ProfileId.generate(profileId));
        Permission permission = permissionRepository.findByName(permissionName);
        profile.addPermission(permission);
        profileRepository.save(profile);
    }


}
