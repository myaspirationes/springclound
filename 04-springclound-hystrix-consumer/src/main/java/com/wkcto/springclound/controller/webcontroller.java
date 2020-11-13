package com.wkcto.springclound.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wkcto.springclound.hystrix.myHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class webcontroller {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/consumer/hello")
    public String hello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SPRINGCLOUND-HYSTRIX-PROVIDER/hello/hystrix", String.class);
        String body = responseEntity.getBody();
        System.out.println(body);

        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String error(Throwable throwable) {
        System.out.println(throwable.getMessage());
        System.out.println(throwable.getClass());
        return "被调用者出错或无响应";
    }


    //@HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/consumer/myself")
    public String hellomyselt() {
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SPRINGCLOUND-HYSTRIX-PROVIDER/hello/hystrix", String.class);
//        String body = responseEntity.getBody();
//        System.out.println(body);
//
//        System.out.println(responseEntity.getStatusCode());
        myHystrixCommand command = new myHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),restTemplate,"http://SPRINGCLOUND-HYSTRIX-PROVIDER/hello/hystrix");
        String body = (String) command.execute();
        return "zidingyi"+body;
    }

}
