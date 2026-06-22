package com.enalab.board.auth.infrastructure.persistence.repository;

import com.enalab.board.auth.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, UUID> {
    List<PermissionEntity> findByNameContainingIgnoreCase(String name);

    Optional<PermissionEntity> findByName(String name);

    boolean existsByName(String name);
}
