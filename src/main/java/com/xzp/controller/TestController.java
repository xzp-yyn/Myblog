package com.xzp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/16 18:26
 * @Version 1.0
 */
@RestController
public class TestController {

    @Value("${test.name}")
    private String test;

    @GetMapping("/test")
    public String getTest(){
        return test;
    }
}
