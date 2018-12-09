package com.ribbon.test.controller;

import com.ribbon.test.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello-hystrix" ,method = RequestMethod.GET)
    public String helloController(){
        return helloService.hello();
    }
}
