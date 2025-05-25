package com.escaes.springcloud.msvc.products.msvc_products.services;

import java.util.List;
import java.util.Optional;

import com.escaes.springcloud.msvc.products.msvc_products.entities.Product;

public interface IProductService {

    List<Product>findAll();
    Optional<Product>findById(Long id);
}
