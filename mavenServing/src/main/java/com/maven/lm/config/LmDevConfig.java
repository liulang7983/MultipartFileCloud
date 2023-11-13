package com.maven.lm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ming.li
 * @date 2023/11/13 13:36
 * 此时和LmConfig的区别是需要MavenSpring1启动时引入
 */
@Configuration
@PropertySource("classpath:application-dev.yml")
@ConfigurationProperties(prefix = "lm1")
public class LmDevConfig {
    private String name;
    private Integer age;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
