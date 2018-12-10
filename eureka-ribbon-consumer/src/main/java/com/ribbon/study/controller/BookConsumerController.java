package com.ribbon.study.controller;

import com.baijz.bean.BookBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class BookController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/get-book")
    public BookBase getBook(){

        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/book/back-book").build();

        URI uri = uriComponents.toUri();

        return restTemplate.postForObject(uri,"",BookBase.class);

    }

}
