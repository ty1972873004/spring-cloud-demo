package com.hxmec.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 
 * @author  Trazen
 * @date  2020/7/2 18:11
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Object fallback() {
        Map<String,Object> result = new HashMap<>(3);
        result.put("data",null);
        result.put("message","request fallback!!!");
        result.put("code",500);
        return result;
    }

}