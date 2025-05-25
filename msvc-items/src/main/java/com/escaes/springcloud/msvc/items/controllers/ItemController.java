package com.escaes.springcloud.msvc.items.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.escaes.springcloud.msvc.items.models.Item;
import com.escaes.springcloud.msvc.items.services.IitemService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class ItemController {

    private IitemService service;
    //En el Qualifier cambiar el nombre de acuerdo al servicio que se desea utilizar, en este caso el de WebFlux
    public ItemController(@Qualifier("WebClientService")IitemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?>list() {

        List<Item>findAll=service.findAll();
        if(!findAll.isEmpty()){
            return ResponseEntity.ok(service.findAll());
        }
        return ResponseEntity.status(404)
        .body(Collections.singletonMap("message"
        , "No existen productos en el microservicio msvc-products, verificar microservicio msvc-products"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
    Optional<Item>item = service.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item);
        }
            
    return ResponseEntity.status(404)
    .body(Collections.singletonMap("message"
    , "No existe producto en el microservicio msvc-products"));
    }


}
