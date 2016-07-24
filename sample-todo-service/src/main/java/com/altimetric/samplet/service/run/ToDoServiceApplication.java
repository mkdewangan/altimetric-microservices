package com.altimetric.samplet.service.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import com.altimetric.sample.service.controller.ToDoServiceController;

@SpringBootApplication
@EnableDiscoveryClient
@org.springframework.context.annotation.ComponentScan(basePackageClasses = ToDoServiceController.class)
public class ToDoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoServiceApplication.class, args);
    }
}
