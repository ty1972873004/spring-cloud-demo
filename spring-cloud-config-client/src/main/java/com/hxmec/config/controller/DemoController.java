package com.hxmec.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/26 18:48
 */
@RestController
@RequestMapping("/demo")
@Slf4j
@RefreshScope
public class DemoController {

    @Value("${hx.name:#{null}}")
    private String name;

    @GetMapping(value = "/test1")
    public String  test1(){
        return "Hello " + name;
    }
}
