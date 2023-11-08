package com.lm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ming.li
 * @date 2023/11/6 23:02
 */

@SpringBootApplication
@EnableFeignClients
public class ServingApp {
    public static void main(String[] args) {
        SpringApplication.run(ServingApp.class, args);
    }
}
