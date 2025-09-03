package com.deliverytech.delivery.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.deliverytech.delivery.entity.Users;

public interface UserRepository extends JpaRepository<Users, UUID> {

    UserDetails findByEmail(String email);
}
