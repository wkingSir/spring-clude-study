package com.ribbon.study.service;

import com.baijz.bean.BookBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author : Administrator
 * @Date : 2019/1/21 10 37
 * @Description :
 */
@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    public BookBase test8(Long id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getbook6/{1}", BookBase.class, id);
    }

    public List<BookBase> test9(List<Long> ids) {
        System.out.println("test9---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        BookBase[] books = restTemplate.getForObject("http://HELLO-SERVICE/getbook6?ids={1}", BookBase[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

    /**
     * 注解实现请求合并
     * 使用场景：
     * 1.高延时的请求
     * 2.高并发
     */
    @HystrixCollapser(batchMethod = "test11",collapserProperties = {@HystrixProperty(name ="timerDelayInMilliseconds",value = "100")})
    public Future<BookBase> test10(Long id) {
        return null;
    }

    @HystrixCommand
    public List<BookBase> test11(List<Long> ids) {
        System.out.println("test9---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        BookBase[] books = restTemplate.getForObject("http://HELLO-SERVICE/getbook6?ids={1}", BookBase[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

}
