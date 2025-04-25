package com.manoj.ecomm.repo;

import com.manoj.ecomm.model.Product;
import com.manoj.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
