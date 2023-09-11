package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李明
 * @date Created in 2023/8/31 23:00
 */
@SpringBootApplication
@EnableFeignClients
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class,args);
    }
}
