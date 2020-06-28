package com.hxmec.feign.controller;

import com.hxmec.feign.service.FeignService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 14:54
 */
@RestController
@RequestMapping("/feign")
@AllArgsConstructor
public class FeignClientController {

    private final FeignService feignService;

    @GetMapping(value = "/test1")
    public String feign(@RequestParam String name) {
        return feignService.feignTest(name);
    }

}
