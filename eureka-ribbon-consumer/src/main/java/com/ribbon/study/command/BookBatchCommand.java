package com.ribbon.study.command;

import com.baijz.bean.BookBase;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.ribbon.study.service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Administrator
 * @Date : 2019/1/21 10 42
 * @Description :
 */
public class BookBatchCommand extends HystrixCommand<List<BookBase>> {

    private List<Long> ids;
    private BookService bookService;

    protected BookBatchCommand(List<Long> ids, BookService bookService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CollapsingGroup")).andCommandKey(HystrixCommandKey.Factory.asKey("CollapsingKey")));
        this.ids = ids;
        this.bookService = bookService;
    }

    @Override
    protected List<BookBase> run() throws Exception {
        return bookService.test9(ids);
    }
}
