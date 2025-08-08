package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    
    @Query("SELECT c FROM Cliente c WHERE c.email LIKE %:email%")
    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    List<Cliente> findByAtivoTrue();

    boolean existsByEmail(String email);
}