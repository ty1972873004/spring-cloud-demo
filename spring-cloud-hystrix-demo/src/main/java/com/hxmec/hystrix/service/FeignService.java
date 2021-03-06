package com.hxmec.hystrix.service;

import com.hxmec.hystrix.service.fallback.FeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 14:49
 */
@FeignClient(value = "eureka-client-provider",fallback = FeignServiceFallback.class)
public interface FeignService {

    @RequestMapping(value = "/demo/feign",method = RequestMethod.GET)
    String feignTest(@RequestParam(value = "name") String name);

}
