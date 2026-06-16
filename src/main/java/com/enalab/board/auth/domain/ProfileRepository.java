package com.enalab.board.auth.domain;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ProfileRepository {
    Profile save(Profile profile);

    Profile findById(ProfileId profileId);

    Profile findByName(String name);

    List<Profile> findAll();

    void deleteById(ProfileId id);
}
