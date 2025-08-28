package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.deliverytech.delivery.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);
}
