package com.example.eureka.provider;

import com.baijz.bean.BookBase;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/back-book" ,method = RequestMethod.POST)
    public BookBase bookProvider(){
        return new BookBase("java从入门到放弃",22,"武僧","行云流水");
    }

    @RequestMapping(value = "/provide-book-uri" ,method = RequestMethod.POST)
    public URI bookProviderUri(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:9999/ribbon-server")
                .build().encode();
        return uriComponents.toUri();
    }


    /**
     * 请求合并所需的两个请求
     */
    @RequestMapping("/getbook6")
    public List<BookBase> book6(String ids) {
        System.out.println("ids>>>>>>>>>>>>>>>>>>>>>" + ids);
        ArrayList<BookBase> books = new ArrayList<>();
        books.add(new BookBase("《李自成》", 55, "姚雪垠", "人民文学出版社"));
        books.add(new BookBase("中国文学简史", 33, "林庚", "清华大学出版社"));
        books.add(new BookBase("文学改良刍议", 33, "胡适", "无"));
        books.add(new BookBase("ids", 22, "helloworld", "haha"));
        return books;
    }

    @RequestMapping("/getbook6/{id}")
    public BookBase book61(@PathVariable Integer id) {
        BookBase book = new BookBase("《李自成》2", 55, "姚雪垠2", "人民文学出版社2");
        return book;
    }
}
