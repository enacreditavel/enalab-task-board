package com.enalab.board.auth.infrastructure.persistence.repository;

import com.enalab.board.auth.domain.Profile;
import com.enalab.board.auth.domain.ProfileId;
import com.enalab.board.auth.domain.ProfileRepository;
import com.enalab.board.auth.infrastructure.exception.ProfileNotFoundException;
import com.enalab.board.auth.infrastructure.persistence.entity.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public Profile save(Profile profile) {
        var profileEntity = ProfileEntity.from(profile);
        var saved = profileJpaRepository.save(profileEntity);
        return saved.toDomain();
    }

    @Override
    public Profile findByName(String name) {
        return profileJpaRepository.findByName(name)
                .map(ProfileEntity::toDomain)
                .orElseThrow(() -> new ProfileNotFoundException(name));
    }

    @Override
    public Profile findById(ProfileId profileId) {
        return profileJpaRepository.findById(profileId.id())
                .map(ProfileEntity::toDomain)
                .orElseThrow(() -> new ProfileNotFoundException(profileId.id()));
    }

    @Override
    public List<Profile> findAll() {
        return profileJpaRepository.findAll().stream().map(ProfileEntity::toDomain).toList();
    }

    @Override
    public void deleteById(ProfileId profileId) {
        profileJpaRepository.deleteById(profileId.id());
    }
}
