package com.escaes.springcloud.msvc.items;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //A compracion de feign este web client no trabaja con balanceo de carga entonces hay que hacer este Bean manualmente.
    @Bean
    @LoadBalanced
    WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
