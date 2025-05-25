package com.escaes.springcloud.msvc.products.msvc_products.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escaes.springcloud.msvc.products.msvc_products.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
