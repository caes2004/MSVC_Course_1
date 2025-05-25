package com.escaes.springcloud.msvc.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients//Importante para poder consumir el otro Microservicio
@SpringBootApplication
public class MsvcItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcItemsApplication.class, args);
	}

}
