package com.escaes.springcloud.msvc.products.msvc_products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escaes.springcloud.msvc.products.msvc_products.entities.Product;
import com.escaes.springcloud.msvc.products.msvc_products.repositories.ProductRepository;

@Service //O component
public class ProductServiceImpl implements IProductService{

   
    private final ProductRepository productRepository;
    private final Environment environment;// Aca obtenemos el ambiente de configuracion del proyecto donde viene
    // el properties con sus configuraciones incluyendo sus puertos etc.

    public ProductServiceImpl(ProductRepository productRepository,Environment environment){
        this.productRepository=productRepository;
        this.environment=environment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(product->{
            // Si se desea evitar hacer cast, cambiar int en la entidad por String
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList()); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(product->{
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }

}
