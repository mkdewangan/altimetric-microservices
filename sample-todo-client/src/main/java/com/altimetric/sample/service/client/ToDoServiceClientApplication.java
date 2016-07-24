package com.altimetric.sample.service.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@org.springframework.context.annotation.ComponentScan(basePackageClasses = ToDoServiceClientController.class)
public class ToDoServiceClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoServiceClientApplication.class, args);
    }
}
