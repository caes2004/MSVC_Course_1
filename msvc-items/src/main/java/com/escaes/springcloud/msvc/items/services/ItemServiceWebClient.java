package com.escaes.springcloud.msvc.items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.escaes.springcloud.msvc.items.models.Item;
import com.escaes.springcloud.msvc.items.models.ProductDTO;

@Service("WebClientService")
public class ItemServiceWebClient implements IitemService {

    

    private final WebClient.Builder client;
    
    public ItemServiceWebClient(Builder client) {
        this.client = client;
    
    }

    @Override
    public List<Item> findAll() {
        return this.client.build()
        .get()
        .uri("http://msvc-products")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
            .bodyToFlux(ProductDTO.class)//Las datos que consume del otro Microservicio
            .map(product->new Item(product,new Random().nextInt(10)+1))
        .collectList()
        .block();
           
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String,Long>params=new HashMap<>();
        params.put("id", id);

         try {
            return Optional.of(this.client.build()
            .get()
            .uri("http://msvc-products/{id}",params)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ProductDTO.class)
            .map(product->new Item(product,new Random().nextInt(10)+1))
            .block());
            
         } catch (WebClientResponseException e) {
          return Optional.empty();// Este se maneja en el Controller con el singletonMAp
         }
        
    }

}
