package com.example.dockerdeployfullstack.persistence;

import com.example.dockerdeployfullstack.persistence.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
