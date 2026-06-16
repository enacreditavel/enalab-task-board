package com.enalab.board.auth.infrastructure.persistence.repository;

import com.enalab.board.auth.domain.Profile;
import com.enalab.board.auth.domain.User;
import com.enalab.board.auth.domain.UserId;
import com.enalab.board.auth.domain.UserRepository;
import com.enalab.board.auth.infrastructure.exception.UserNotFoundException;
import com.enalab.board.auth.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        var userEntity = UserEntity.from(user);
        var saved = userJpaRepository.save(userEntity);
        return saved.toDomain();
    }

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserEntity::toDomain)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User findById(UserId userId) {
        return userJpaRepository.findById(userId.id()).map(UserEntity::toDomain)
                .orElseThrow(() -> new UserNotFoundException(userId.id()));
    }

    @Override
    public void addProfile(Profile profile) {

    }

    @Override
    public void removeProfile(Profile profile) {

    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(UserEntity::toDomain).toList();
    }
}
