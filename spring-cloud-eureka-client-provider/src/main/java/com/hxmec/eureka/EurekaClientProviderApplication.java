package com.hxmec.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 13:34
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProviderApplication.class, args);
    }

}
