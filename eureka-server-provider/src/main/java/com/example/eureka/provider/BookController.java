package com.example.eureka.provider;

import com.baijz.bean.BookBase;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.logging.Logger;

/**
 * @Author : Administrator
 * @Date : 2018/12/3 22 09
 * @Description :
 */
@RestController
public class BookController {

    private Logger logger = Logger.getLogger(BookController.class.toString());

    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/provide-book" ,method = RequestMethod.POST)
    public BookBase bookProvider(){
        return new BookBase("java从入门到放弃",22,"武僧","行云流水");
    }

    @RequestMapping(value = "/provide-book-uri" ,method = RequestMethod.POST)
    public URI bookProviderUri(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:9999/ribbon-server")
                .build().encode();
        return uriComponents.toUri();
    }

}
