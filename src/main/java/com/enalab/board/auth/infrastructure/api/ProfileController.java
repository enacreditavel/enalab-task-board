package com.enalab.board.auth.infrastructure.api;

import com.enalab.board.auth.application.profile.*;
import com.enalab.board.auth.infrastructure.api.dto.AddPopPermissionRequest;
import com.enalab.board.auth.infrastructure.api.dto.CreateProfileRequest;
import com.enalab.board.auth.infrastructure.api.dto.ProfileResponse;
import com.enalab.board.auth.infrastructure.secutiry.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final CreateProfileUseCase createProfileUseCase;
    private final FindAllProfilesUseCase findAllProfilesUseCase;
    private final AddPermissionToProfileUseCase addPermissionToProfileUseCase;
    private final RemovePermissionFromProfileUseCase removePermissionFromProfileUseCase;
    private final DeleteProfileUseCase deleteProfileUseCase;

    @PostMapping
    @PreAuthorize("hasRole('PROFILE_WRITE') or hasRole('HOMELANDER')")
    public ProfileResponse createProfile(@RequestBody CreateProfileRequest request){
        var output = createProfileUseCase.execute(request.toInput());
        return ProfileResponse.from(output);
    }
    @GetMapping
    @PreAuthorize("hasRole('PROFILE_READ') or hasRole('HOMELANDER')")
    public List<ProfileResponse> getAllProfiles(@AuthenticationPrincipal CustomUserDetails userDetails){
        log.info(userDetails.getUsername());
        return findAllProfilesUseCase.execute().stream().map(ProfileResponse::from).toList();
    }

    @PatchMapping("/{id}/add-permission")
    @PreAuthorize("hasRole('PROFILE_WRITE') or hasRole('HOMELANDER')")
    public void addPermission(@PathVariable UUID id, @RequestBody AddPopPermissionRequest permissionName){
        addPermissionToProfileUseCase.execute(id, permissionName.name());
    }

    @PatchMapping("/{id}/remove-permission")
    @PreAuthorize("hasRole('PROFILE_WRITE') or hasRole('HOMELANDER')")
    public void removePermission(@PathVariable UUID id, @RequestBody AddPopPermissionRequest permissionName){
        removePermissionFromProfileUseCase.execute(id, permissionName.name());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFILE_WRITE') or hasRole('HOMELANDER')")
    public void deleteProfile(@PathVariable UUID id){
        deleteProfileUseCase.execute(id);
    }
}
