package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Product;

public interface IProdutoRepository extends JpaRepository<Product, Long> {

}