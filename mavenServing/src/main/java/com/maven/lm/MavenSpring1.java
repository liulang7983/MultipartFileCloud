package com.maven.lm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ming.li
 * @date 2023/11/13 11:52
 */
@SpringBootApplication
public class MavenSpring1 {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MavenSpring1.class);
       app.setAdditionalProfiles("dev");
        ConfigurableApplicationContext context = app.run(args);
        System.out.println(context.getEnvironment().getProperty("lm1.name"));
    }
}
