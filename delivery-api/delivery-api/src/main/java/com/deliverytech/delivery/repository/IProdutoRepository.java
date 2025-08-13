package com.deliverytech.delivery.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Product;

public interface IProdutoRepository extends JpaRepository<Product, Long> {

    // public List<Product> findByAvailableTrue();

    // public List<Product> findByPriceLessThanEqual(BigDecimal ckprice);

}