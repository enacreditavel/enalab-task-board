package com.enalab.board.auth.infrastructure.persistence.entity;

import com.enalab.board.auth.domain.User;
import com.enalab.board.auth.domain.UserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String name;

    private Instant createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_profiles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    @Builder.Default
    private Set<ProfileEntity> profiles = new HashSet<>();



    public static UserEntity from(User user){
        return UserEntity.builder()
                .id(user.getUserId().id())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .profiles(user.getProfiles().stream().map(ProfileEntity::from).collect(Collectors.toSet()))
                .build();
    }

    public User toDomain(){
        return User.builder()
                .userId(UserId.generate(this.id))
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .createdAt(this.createdAt)
                .profiles(this.profiles.stream().map(ProfileEntity::toDomain).collect(Collectors.toSet()))
                .build();
    }
}
