package com.enalab.board.auth.infrastructure.persistence.entity;

import com.enalab.board.auth.domain.Permission;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {
    @Id
    private UUID permissionEntityId;

    @Column(unique = true, nullable = false)
    private String name;

    public static PermissionEntity from(Permission permission){
        return PermissionEntity.builder()
                .permissionEntityId(permission.permissionId().id())
                .name(permission.name())
                .build();
    }

    public Permission toDomain(){
        return Permission.create(this.permissionEntityId, this.name);
    }
}
