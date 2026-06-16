package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.user.output.UserCompleteOutput;
import com.enalab.board.auth.application.user.output.UserOutput;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record UserCompleteResponse(UUID id, String username, String email, Instant createdAt, Set<ProfileResponse> profiles) {
    public static UserCompleteResponse from(UserCompleteOutput output){
        return UserCompleteResponse.builder()
                .id(output.id())
                .username(output.username())
                .email(output.email())
                .createdAt(output.createdAt())
                .profiles(output.profiles().stream().map(ProfileResponse::from).collect(Collectors.toSet()))
                .build();
    }
}
