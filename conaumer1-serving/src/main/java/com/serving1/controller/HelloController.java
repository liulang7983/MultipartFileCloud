package com.serving1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/11/6 21:26
 */
@RestController
@RequestMapping("hello1")
public class HelloController {

    @RequestMapping("test")
    public String test(){
        return "test";
    }
    @RequestMapping("test1")
    public String test1(){
        return "test1";
    }
}
