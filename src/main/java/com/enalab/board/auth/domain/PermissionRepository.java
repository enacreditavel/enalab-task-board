package com.enalab.board.auth.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository {
    Permission save(Permission permission);

    Permission findByName(String name);

    Permission findById(PermissionId permissionId);

    List<Permission> findAll();

    boolean existsById(PermissionId permissionId);

    void deleteById(PermissionId permissionId);
}
