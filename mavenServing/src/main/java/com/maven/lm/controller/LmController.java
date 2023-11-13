package com.maven.lm.controller;

import com.maven.lm.config.LmConfig;
import com.maven.lm.config.LmDevConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/11/13 11:55
 */
@RestController
@RequestMapping("lm")
public class LmController {

    @Value("${lm.name}")
    private String name;
    @Autowired
    private LmConfig lmConfig;
    @Autowired
    private LmDevConfig lmDevConfig;

    @RequestMapping("getName")
    public String getName(){
        return name;
    }
    @RequestMapping("getLm")
    public String getLm(){
        return lmConfig.getName()+":"+lmConfig.getAge()+":"+lmConfig.getMessage();
    }

    @RequestMapping("getDevLm")
    public String getDevLm(){
        return lmDevConfig.getName()+":"+lmDevConfig.getAge()+":"+lmDevConfig.getMessage();
    }
}
