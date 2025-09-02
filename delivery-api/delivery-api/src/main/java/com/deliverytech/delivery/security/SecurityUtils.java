package com.deliverytech.delivery.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.deliverytech.delivery.entity.Users;

public class SecurityUtils {
    public static Users getCurrentUser() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Users)) {
            throw new AuthenticationCredentialsNotFoundException("Usuário não autenticado");
        }
        return (Users) authentication.getPrincipal();

    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null && auth.getAuthorities() != null) {
            return auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                            .equals("ROLE_" + role));
        }
        return false;
    }
}
