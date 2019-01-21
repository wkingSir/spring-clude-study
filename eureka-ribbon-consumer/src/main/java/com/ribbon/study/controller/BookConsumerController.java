package com.ribbon.study.controller;

import com.baijz.bean.BookBase;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.ribbon.study.command.BookCollapseCommand;
import com.ribbon.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Book;
import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class BookConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/get-book",method = RequestMethod.GET)
    public BookBase getBook(){

        String name = "钢铁是怎样炼成的";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/back-book").build().encode();

        URI uri = uriComponents.toUri();

        ResponseEntity<BookBase> responseEntity = restTemplate.postForEntity(uri,name,BookBase.class);

        return responseEntity.getBody();
    }



    @RequestMapping("/test7")
    @ResponseBody
    public void test7() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        System.out.println("=============================>>>>>>>>>>>>>"+new Date().getTime());
//        BookCollapseCommand bc1 = new BookCollapseCommand(bookService, 1l);
//        BookCollapseCommand bc2 = new BookCollapseCommand(bookService, 2l);
//        BookCollapseCommand bc3 = new BookCollapseCommand(bookService, 3l);
//        BookCollapseCommand bc4 = new BookCollapseCommand(bookService, 4l);
//        Future<BookBase> q1 = bc1.queue();
//        Future<BookBase> q2 = bc2.queue();
//        Future<BookBase> q3 = bc3.queue();
        Future<BookBase> q1 = bookService.test10(1l);
        Future<BookBase> q2 = bookService.test10(2l);
        Future<BookBase> q3 = bookService.test10(3l);

        BookBase book1 = q1.get();
        BookBase book2 = q2.get();
        BookBase book3 = q3.get();
        System.out.println("=============================>>>>>>>>>>>>>"+new Date().getTime());
//        Thread.sleep(3000);
        Future<BookBase> q4 = bookService.test10(4l);
//        Future<BookBase> q4 = bc4.queue();
        BookBase book4 = q4.get();
        System.out.println("=============================>>>>>>>>>>>>>"+new Date().getTime());
        System.out.println("book1>>>"+book1);
        System.out.println("book2>>>"+book2);
        System.out.println("book3>>>"+book3);
        System.out.println("book4>>>"+book4);
        context.close();
    }

}
