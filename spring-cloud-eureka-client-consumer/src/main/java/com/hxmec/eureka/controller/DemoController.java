package com.hxmec.eureka.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 14:22
 */
@RestController
@RequestMapping("/test")
@AllArgsConstructor
@Slf4j
public class DemoController {

    private final RestTemplate restTemplate;

    @GetMapping("/callHello")
    public String callHello() {
        //String result = restTemplate.getForObject("http://localhost:9001/demo/hello", String.class);
        String result = restTemplate.getForObject("http://eureka-client-provider/demo/hello", String.class);
        log.info("调用结果：{}" , result);
        return result;
    }
}
