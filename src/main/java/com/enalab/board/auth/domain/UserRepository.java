package com.enalab.board.auth.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    User findByUsername(String username);

    User findById(UserId userId);

    void addProfile(Profile profile);

    void removeProfile(Profile profile);

    List<User> findAll();
}
