package com.wkcto.springclound.controller;

import com.wkcto.springclound.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @RequestMapping("/service/hello")
    public String hello() {
        System.out.println("Service 1 provider");
        return "Hello ,Spring Cloud!!~~~provider 11111";
    }

    @RequestMapping("/service/user")
    public User  user() {
        User user = new User();
        user.setId(108);
        user.setName("张胜男");
        user.setPhone("13899998888");
        System.out.println(" provider 1111  返回 user对象");
        return user;
    }

    @RequestMapping("/service/getuser")
    public User  getuser(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("phone") String phone) {
        User user = new User();
        user.setId(id);
        user.setName(name );
        user.setPhone(phone);
        System.out.println(" provider 1111  返回 user对象");
        return user;
    }

    @RequestMapping(value = "/service/adduser",method = RequestMethod.POST)
    public User  adduser(@RequestParam("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("phone") String phone) {
        User user = new User();
        user.setId(id);
        user.setName(name );
        user.setPhone(phone);
        System.out.println(" provider 1111  返回 tianj  ijan de  user对象");
        return user;
    }


}
