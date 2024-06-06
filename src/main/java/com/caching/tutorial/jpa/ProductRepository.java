package com.caching.tutorial.jpa;

import com.caching.tutorial.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByName(String name);
    Product findProductByNameAndType(String name, String type);

}
