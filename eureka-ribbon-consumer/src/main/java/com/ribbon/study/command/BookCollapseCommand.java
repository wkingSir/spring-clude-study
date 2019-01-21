package com.ribbon.study.command;

import com.baijz.bean.BookBase;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.ribbon.study.service.BookService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Administrator
 * @Date : 2019/1/21 10 48
 * @Description :
 */
public class BookCollapseCommand extends HystrixCollapser<List<BookBase>,BookBase,Long> {

    private BookService bookService;
    private Long id;

    /**
     * 设置请求间隔100毫秒之内
     * @param bookService
     * @param id
     */
    public BookCollapseCommand(BookService bookService ,Long id){
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("bookCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter()
                        .withTimerDelayInMilliseconds(100)));
        this.bookService = bookService;
        this.id = id;
    }

    @Override
    public Long getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<BookBase>> createCommand(Collection<CollapsedRequest<BookBase, Long>>
                                                                       collapsedRequests) {
        List<Long> ids = new ArrayList<>(collapsedRequests.size());
        ids.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        BookBatchCommand bookBatchCommand = new BookBatchCommand(ids, bookService);
        return bookBatchCommand;
    }

    @Override
    protected void mapResponseToRequests(List<BookBase> batchResponse, Collection<CollapsedRequest<BookBase, Long>> collapsedRequests) {
        System.out.println("mapResponseToRequests");
        int count = 0;
        for (CollapsedRequest<BookBase, Long> collapsedRequest : collapsedRequests) {
            BookBase book = batchResponse.get(count++);
            collapsedRequest.setResponse(book);
        }
    }
}
