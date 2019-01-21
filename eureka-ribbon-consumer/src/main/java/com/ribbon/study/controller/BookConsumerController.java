package com.ribbon.study.controller;

import com.baijz.bean.BookBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class BookConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/get-book",method = RequestMethod.GET)
    public BookBase getBook(){

        String name = "钢铁是怎样炼成的";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/book/back-book").build().encode();

        URI uri = uriComponents.toUri();

        ResponseEntity<BookBase> responseEntity = restTemplate.postForEntity(uri,name,BookBase.class);

        return responseEntity.getBody();
    }

}
