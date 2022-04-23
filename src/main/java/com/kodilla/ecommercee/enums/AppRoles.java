package com.kodilla.ecommercee.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kodilla.ecommercee.enums.Permissions.*;

public enum AppRoles {
    USER(Sets.newHashSet(P_USER)),
    ADMIN(Sets.newHashSet(P_USER,P_ADMIN)),
    ROOT(Sets.newHashSet(P_USER,P_ADMIN,P_ROOT));

    private final Set<Permissions> permissions;

    AppRoles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
