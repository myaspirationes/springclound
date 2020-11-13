package com.wkcto.springclound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Application9102 {

    public static void main(String[] args) {
        SpringApplication.run(Application9102.class, args);
    }

}
