package com.enalab.board.auth.infrastructure.persistence.repository;

import com.enalab.board.auth.domain.Permission;
import com.enalab.board.auth.domain.PermissionId;
import com.enalab.board.auth.domain.PermissionRepository;
import com.enalab.board.auth.infrastructure.persistence.entity.PermissionEntity;
import com.enalab.board.common.exceptions.FiguringItOutException;
import com.enalab.board.common.exceptions.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public Permission save(Permission permission) {
        try{
            return permissionJpaRepository.save(PermissionEntity.from(permission)).toDomain();
        } catch (RuntimeException e) {
            throw new FiguringItOutException(e.getMessage());
        }
    }

    @Override
    public Permission findById(@NonNull PermissionId permissionId) {
        return permissionJpaRepository.findById(permissionId.id())
                .map(PermissionEntity::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id: " + permissionId.id()));
    }

    @Override
    public Permission findByName(String name) {
        return permissionJpaRepository.findByName(name)
                .map(PermissionEntity::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with name: " +name));
    }

    @Override
    public List<Permission> searchByName(String name) {
        return permissionJpaRepository.findByNameContainingIgnoreCase(name).stream()
                .map(PermissionEntity::toDomain).toList();
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
    public boolean existsByName(String permissionName) {
        return permissionJpaRepository.existsByName(permissionName.toUpperCase());
    }

    @Override
    public void deleteById(PermissionId permissionId) {
        try{
            permissionJpaRepository.deleteById(permissionId.id());
        } catch (RuntimeException e) {
            throw new FiguringItOutException(e.getMessage());
        }
    }


}
