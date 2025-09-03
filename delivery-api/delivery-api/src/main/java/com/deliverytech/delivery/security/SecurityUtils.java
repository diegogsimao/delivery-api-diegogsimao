package com.deliverytech.delivery.security;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Users;

@Service
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

    public static UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null && auth.getAuthorities() != null) {
            return auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                            .equals(role));
        }
        return false;
    }
}
