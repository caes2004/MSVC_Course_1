package com.escaes.springcloud.msvc.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.escaes.springcloud.msvc.items.models.ProductDTO;
//El feign Client funciona como un DAO pero para consumir datos de otro microservicio.
//Usando el loadBalancer no es necesario especificar la URL aqui pero si en el properties
@FeignClient(/*url="localhost:8080",*/name="msvc-products")
public interface ProductFeignClient {
//Se deben tener las mismas rutas del otro microservico
    @GetMapping
    List<ProductDTO>findAll();
    
    @GetMapping("/{id}")
    ProductDTO findById(@PathVariable Long id);

}
