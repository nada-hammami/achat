package tn.esprit.com.foyer.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tn.esprit.com.foyer.Permissions;

import java.util.*;
import java.util.stream.Collectors;

import static tn.esprit.com.foyer.Permissions.*;

@RequiredArgsConstructor
public enum Role {

  USER("ROLE_USER", Collections.emptySet()),
  ADMIN("ROLE_ADMIN",
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE
          )
  );

  private final String roleName;

  @Getter
  private final Set<Permissions> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
