package com.serving1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ming.li
 * @date 2023/11/6 23:02
 */

@SpringBootApplication
@EnableFeignClients
public class Serving1App {
    public static void main(String[] args) {
        SpringApplication.run(Serving1App.class, args);
    }
}
