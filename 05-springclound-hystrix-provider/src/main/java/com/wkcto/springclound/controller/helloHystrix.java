package com.wkcto.springclound.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloHystrix {

    @RequestMapping("/hello/hystrix")
    public String Hello(){
        System.out.println("Service   Hystrix 1 provider");
        System.out.println("Service   Hystrix 1 provider"+1/0);

        return "Hello ,Spring Cloud!!~~~provider 11111  OF  Hystrix";
    }

}
