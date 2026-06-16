package com.enalab.board.auth.application.profile.output;

import com.enalab.board.auth.domain.Profile;

import java.util.UUID;

public record ProfileOutput(UUID id, String name) {
    public static ProfileOutput from(Profile profile) {
        return new ProfileOutput(profile.getProfileId().id(), profile.getName());
    }
}
