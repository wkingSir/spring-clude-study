package com.ribbon.eurekaribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : Administrator
 * @Date : 2018/12/1 16 02
 * @Description :
 */
@RestController
public class HelloComsumer {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbin-server" ,method = RequestMethod.GET)
    public String helloController(){

        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
    }
}
