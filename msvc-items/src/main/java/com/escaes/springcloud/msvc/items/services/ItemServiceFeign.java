package com.escaes.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.escaes.springcloud.msvc.items.clients.ProductFeignClient;
import com.escaes.springcloud.msvc.items.models.Item;


import feign.FeignException;

@Service("FeignService")
public class ItemServiceFeign implements IitemService{

    private final ProductFeignClient fClient;
    public ItemServiceFeign(ProductFeignClient fClient) {
        this.fClient = fClient;
    }

    @Override
    public List<Item> findAll() {
        
        return fClient.findAll().stream().map(p->{
            Random random=new Random();
            return new Item(p, random.nextInt(10)+1);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {

        try {
        
            return Optional.ofNullable(new Item(fClient.findById(id),new Random().nextInt(10)+1));
        
        } catch (FeignException e) {
            
            return Optional.empty();//Se maneja en el controller con singletonMap
        }
         
    }

}
