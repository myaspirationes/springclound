package com.wkcto.springclound.controller;

import com.wkcto.springclound.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class WebController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/web/hello")
    public String hello() {
        //return restTemplate.getForEntity("http://localhost:8079/service/hello",String.class).getBody();
        //  01-SPRINGCLOUND-PROVIDER
        String url="http://08-SPRINGCLOUND-PROVIDER/service/hello";
        String responseEntity = restTemplate.getForObject(url, String.class);
        return responseEntity+"zuul 服务信息~~！";
    }

    @RequestMapping("/test")
    public String test() {
        //return restTemplate.getForEntity("http://localhost:8079/service/hello",String.class).getBody();
        //  01-SPRINGCLOUND-PROVIDER
        System.out.println("0002222222222");

        String url="http://08-SPRINGCLOUND-GATEWAY/api-zuul/service/hello";
        String responseEntity = restTemplate.getForObject(url, String.class);
        return responseEntity+"经过网管处理的zuul 服务信息~~！";
    }





    @RequestMapping(value = "web/user", method = RequestMethod.GET)
    public String user() {

        //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://01-SPRINGCLOUND-PROVIDER/service/hello", String.class);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/user", User.class);
        User body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();

        int statusCodeValue = responseEntity.getStatusCodeValue();

        HttpHeaders headers = responseEntity.getHeaders();

        System.out.println(body.getId() + "---" + body.getName() + "----" + body.getPhone());

        System.out.println(statusCode);

        System.out.println(statusCodeValue);

        System.out.println(headers);
        return restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/user", String.class).getBody();
    }


    @RequestMapping(value = "web/getuser", method = RequestMethod.GET)
    public User getUser() {
        String[] strArry = {"105", "赵云", "13812345678"};
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://01-SPRINGCLOUND-PROVIDER/service/hello", String.class);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={0}&name={1}&phone={2}", User.class, strArry);
        User body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();

        int statusCodeValue = responseEntity.getStatusCodeValue();

        HttpHeaders headers = responseEntity.getHeaders();

        System.out.println(body.getId() + "---" + body.getName() + "----" + body.getPhone());

        System.out.println(statusCode);

        System.out.println(statusCodeValue);

        System.out.println(headers);
        return restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={0}&name={1}&phone={2}", User.class, strArry).getBody();
    }

    @RequestMapping("web/getusermap")
    public User getUserMap() {
        String[] strArry = {"105", "赵云", "13812345678"};

        Map<String, Object> paraMap = new ConcurrentHashMap<>();

        paraMap.put("id", 109);
        paraMap.put("name", "刘备");
        paraMap.put("phone", 139888888);
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://01-SPRINGCLOUND-PROVIDER/service/hello", String.class);
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={id}&name={name}&phone={phone}", User.class, paraMap);
        User body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();

        int statusCodeValue = responseEntity.getStatusCodeValue();

        HttpHeaders headers = responseEntity.getHeaders();

        System.out.println(body.getId() + "---" + body.getName() + "----" + body.getPhone());

        System.out.println(statusCode);

        System.out.println(statusCodeValue);

        System.out.println(headers);
        return restTemplate.getForEntity("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={id}&name={name}&phone={phone}", User.class, paraMap).getBody();
    }


    @RequestMapping("web/getuserforobject")
    public User getUserObject() {
        String[] strArry = {"105", "赵云", "13812345678"};

        Map<String, Object> paraMap = new ConcurrentHashMap<>();

        paraMap.put("id", 1054);
        paraMap.put("name", "解放路科技的");
        paraMap.put("phone", 1377777);

        User responseEntity = restTemplate.getForObject("http://01-SPRINGCLOUND-PROVIDER/service/getuser?id={id}&name={name}&phone={phone}", User.class, paraMap);

        User body = restTemplate.getForObject("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={0}&name={1}&phone={2}", User.class, strArry);


        System.out.println(responseEntity.getId() + "---" + responseEntity.getName() + "----" + responseEntity.getPhone());
        System.out.println(body.getId() + "---" + body.getName() + "----" + body.getPhone());

        return restTemplate.getForObject("http://08-SPRINGCLOUND-PROVIDER/service/getuser?id={id}&name={name}&phone={phone}", User.class, paraMap);
    }

    @RequestMapping(value = "web/adduser" )
    public User addUser() {
        String[] strArry = {"105", "赵云", "13812345678"};

        MultiValueMap<String, Object> dataMap= new LinkedMultiValueMap();
        dataMap.add("id", 123);
        dataMap.add("name", "解放路科技的");
        dataMap.add("phone", "15899898");
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://01-SPRINGCLOUND-PROVIDER/service/hello", String.class);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://08-SPRINGCLOUND-PROVIDER/service/adduser", dataMap, User.class);
        User body = responseEntity.getBody();

        HttpStatus statusCode = responseEntity.getStatusCode();

        int statusCodeValue = responseEntity.getStatusCodeValue();

        HttpHeaders headers = responseEntity.getHeaders();

        System.out.println(body.getId() + "---" + body.getName() + "----" + body.getPhone());

        System.out.println(statusCode);

        System.out.println(statusCodeValue);

        System.out.println(headers);
        return restTemplate.postForEntity("http://08-SPRINGCLOUND-PROVIDER/service/adduser", dataMap, User.class).getBody();
    }

}
