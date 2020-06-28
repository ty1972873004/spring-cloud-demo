package com.hxmec.hystrix.service.fallback;

import com.hxmec.hystrix.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 17:12
 */
@Component
public class FeignServiceFallback implements FeignService {

    @Override
    public String feignTest(String name) {
        return "服务请求失败，请求参数为name:"+ name;
    }
}
