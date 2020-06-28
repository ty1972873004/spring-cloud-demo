package com.hxmec.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 功能描述: 
 * @auther: Trazen
 * @date: 2020/6/22 13:51
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerApplication.class, args);
    }

}
