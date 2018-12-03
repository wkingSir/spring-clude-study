package com.example.eureka.provider;

import com.baijz.bean.BookBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
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

    @RequestMapping(value = "provide-book")
    public BookBase bookProvider(){
        return new BookBase("java从入门到放弃",22,"武僧","行云流水");
    }

}
