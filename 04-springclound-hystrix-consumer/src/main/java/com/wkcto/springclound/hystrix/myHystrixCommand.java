package com.wkcto.springclound.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class myHystrixCommand extends HystrixCommand {

    private RestTemplate restTemplate;
    private  String  url;

    public myHystrixCommand(Setter setter,RestTemplate restTemplate,String url) {
        super(setter);
        this.restTemplate=restTemplate;
        this.url=url;
    }

    @Override
    protected Object run() throws Exception {
        return restTemplate.getForEntity(url,Object.class);
    }

    @Override
    protected Object getFallback() {
        System.out.println(super.getFailedExecutionException().getClass());
        System.out.println(super.getFailedExecutionException().getMessage());

        return "RONG DUNALron熔断服务";
    }
}
