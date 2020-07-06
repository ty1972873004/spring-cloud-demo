package com.hxmec.eureka.controller;

import com.hxmec.eureka.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:
 * @author Trazen
 * @date 2020/6/22 13:34
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Value("${server.port:#{null}}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("name"));
        return "Hello " + serverPort;
    }

    @GetMapping("/feign")
    public String feignTest(@RequestParam(value = "name") String name){
        return "Hello Feign "+ name;
    }

    @PostMapping("/readBody")
    public String readBody(@RequestBody UserDTO userDTO){
        return userDTO.getName();
    }

}
