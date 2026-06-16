package com.enalab.board.auth.application.user.output;

import com.enalab.board.auth.application.profile.output.ProfileOutput;
import com.enalab.board.auth.domain.User;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record UserCompleteOutput(UUID id, String username, String name, String email, Instant createdAt, Set<ProfileOutput> profiles) {
    public static UserCompleteOutput from(User user) {
        return UserCompleteOutput.builder()
                .id(user.getUserId().id())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .profiles(user.getProfiles().stream().map(ProfileOutput::from).collect(Collectors.toSet()))
                .build();
    }
}
