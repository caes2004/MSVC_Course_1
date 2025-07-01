package com.escaes.springcloud.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SampleGlobalFIlter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFIlter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("Ejecutando el filtro antes del Request Pre");

        // Mutamos la request con un nuevo header personalizado
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .headers(h -> h.add("X-Custom-Token", "Prueba Token"))
                        .build())
                .build();

        return chain.filter(modifiedExchange).then(Mono.fromRunnable(() -> {
            logger.info("Ejecutando filtro Post Response");

            String token = modifiedExchange.getRequest().getHeaders().getFirst("X-Custom-Token");
            //Manera normal para agregar el header
            if (token != null) {
                logger.info("Token leído en POST: " + token);
                exchange.getResponse().getHeaders().add("X-Custom-Token-1", token);
            }
            // Agregar el header de manera reactiva
            Optional.ofNullable(modifiedExchange.getRequest().getHeaders().getFirst("X-Custom-Token"))
                    .ifPresent(value -> {
                        exchange.getResponse().getHeaders().add("X-Custom-Token-2", value);

                    });

            // Opcional: agregar una cookie y cambiar el tipo de contenido
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
