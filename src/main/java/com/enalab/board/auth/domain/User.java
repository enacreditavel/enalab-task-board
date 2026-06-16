package com.enalab.board.auth.domain;


import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class User {
    private UserId userId;

    private String username;

    private String email;

    private String password;

    private String name;

    private Instant createdAt;

    @Builder.Default
    private Set<Profile> profiles = new HashSet<>();

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public void removeProfile(ProfileId profileId) {
        this.profiles.stream().filter(profiles -> profiles.getProfileId().equals(profileId)).findFirst().ifPresent(this.profiles::remove);
    }

}