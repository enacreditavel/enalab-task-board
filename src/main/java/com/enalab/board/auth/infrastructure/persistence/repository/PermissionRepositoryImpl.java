package com.enalab.board.auth.infrastructure.persistence.repository;

import com.enalab.board.auth.domain.Permission;
import com.enalab.board.auth.domain.PermissionId;
import com.enalab.board.auth.domain.PermissionRepository;
import com.enalab.board.auth.infrastructure.exception.PermissionNotFoundException;
import com.enalab.board.auth.infrastructure.persistence.entity.PermissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public Permission save(Permission permission) {
        var permissionEntity = PermissionEntity.from(permission);
        var saved = permissionJpaRepository.save(permissionEntity);
        return saved.toDomain();
    }

    @Override
    public Permission findById(PermissionId permissionId) {
        return permissionJpaRepository.findById(permissionId.id())
                .map(PermissionEntity::toDomain)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId.id()));
    }

    @Override
    public Permission findByName(String name) {
        return permissionJpaRepository.findByName(name)
                .map(PermissionEntity::toDomain)
                .orElseThrow(() -> new PermissionNotFoundException(name));
    }

    @Override
    public List<Permission> findAll() {
        return permissionJpaRepository.findAll().stream()
                .map(PermissionEntity::toDomain).toList();
    }

    @Override
    public boolean existsById(PermissionId permissionId) {
        return permissionJpaRepository.existsById(permissionId.id());
    }

    @Override
    public void deleteById(PermissionId permissionId) {
        permissionJpaRepository.deleteById(permissionId.id());
    }


}
