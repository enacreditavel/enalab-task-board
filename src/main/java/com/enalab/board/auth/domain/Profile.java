package com.enalab.board.auth.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class Profile {
    private ProfileId profileId;

    private String name; // nome do perfil, ex: "ADMIN", "USER"

    @Builder.Default
    private Set<Permission> permissions = new HashSet<>();

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(String permissionName) {
        this.permissions.stream().filter(permission -> permission.name().equals(permissionName)).findFirst().ifPresent(this.permissions::remove);
    }
}
