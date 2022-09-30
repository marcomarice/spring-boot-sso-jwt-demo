package it.bitrock.springbootssojwtdemo.service;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static it.bitrock.springbootssojwtdemo.service.AccountPermission.CURRICULUM_READ;
import static it.bitrock.springbootssojwtdemo.service.AccountPermission.CURRICULUM_WRITE;

public enum AccountRole {
    DEV(Sets.newHashSet()),
    HR(Sets.newHashSet(CURRICULUM_READ, CURRICULUM_WRITE));

    private final Set<AccountPermission> permissions;

    AccountRole(Set<AccountPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AccountPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
