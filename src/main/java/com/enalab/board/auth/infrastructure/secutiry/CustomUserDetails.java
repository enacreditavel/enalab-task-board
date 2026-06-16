package com.enalab.board.auth.infrastructure.secutiry;

import com.enalab.board.auth.domain.Permission;
import com.enalab.board.auth.domain.Profile;
import com.enalab.board.auth.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.modulith.NamedInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@NamedInterface
@Getter
@Builder
public class CustomUserDetails implements UserDetails {

    private UUID id;

    private String username;

    private String password;

    @Builder.Default
    private Set<Profile> profiles = new HashSet<>();

    public static CustomUserDetails from(User user){
        return CustomUserDetails.builder()
                .id(user.getUserId().id())
                .username(user.getUsername())
                .password(user.getPassword())
                .profiles(user.getProfiles())
                .build();
    }

    public void addProfile(Profile profile){
        this.profiles.add(profile);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles.stream()
                .flatMap(profile -> profile.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority( "ROLE_" + permission.name()))
                .collect(Collectors.toSet());
    }
}
