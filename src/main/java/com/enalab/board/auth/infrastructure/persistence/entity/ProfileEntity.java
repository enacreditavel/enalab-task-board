package com.enalab.board.auth.infrastructure.persistence.entity;

import com.enalab.board.auth.domain.Profile;
import com.enalab.board.auth.domain.ProfileId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profile_permissions",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Builder.Default
    private Set<PermissionEntity> permissions = new HashSet<>();

    public static ProfileEntity from(Profile profile){
        return ProfileEntity.builder()
                .id(profile.getProfileId().id())
                .name(profile.getName())
                .permissions(profile.getPermissions().stream().map(PermissionEntity::from).collect(Collectors.toSet()))
                .build();
    }

    public Profile toDomain(){
        return Profile.builder()
                .profileId(ProfileId.generate(this.id))
                .name(this.name)
                .permissions(this.permissions.stream().map(PermissionEntity::toDomain).collect(Collectors.toSet()))
                .build();
    }
}
