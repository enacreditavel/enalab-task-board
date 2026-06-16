package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.profile.output.ProfileOutput;

import java.util.UUID;

public record ProfileResponse(UUID id, String name) {
    public static ProfileResponse from(ProfileOutput output) {
        return new ProfileResponse(output.id(), output.name());
    }
}
