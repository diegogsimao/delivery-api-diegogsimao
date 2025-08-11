package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}