package com.hxmec.eureka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 * @auther: Trazen
 * @date: 2020/6/22 13:34
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${server.port:#{null}}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + serverPort;
    }

    @GetMapping("/feign")
    public String feignTest(@RequestParam(value = "name") String name){
        return "Hello Feign "+ name;
    }

}
