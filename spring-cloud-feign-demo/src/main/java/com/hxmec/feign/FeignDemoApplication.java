package com.hxmec.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 14:45
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args );
    }
}
