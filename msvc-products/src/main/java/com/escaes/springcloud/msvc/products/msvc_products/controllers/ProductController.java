package com.escaes.springcloud.msvc.products.msvc_products.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.escaes.springcloud.msvc.products.msvc_products.entities.Product;
import com.escaes.springcloud.msvc.products.msvc_products.services.ProductServiceImpl;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class ProductController {

    private final ProductServiceImpl serviceImpl;

    ProductController(ProductServiceImpl serviceImpl){
        this.serviceImpl=serviceImpl;
    }

    @GetMapping
    public ResponseEntity<?>list() {
        return ResponseEntity.ok(this.serviceImpl.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>details(@PathVariable Long id) {
            Product product=serviceImpl.findById(id).
            orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found"));
        return ResponseEntity.ok(product);
    }
    

}
