package com.example.eureka.provider;

import com.baijz.bean.BookBase;
import org.springframework.web.bind.annotation.*;

@RestController("/book")
public class BookController {

    @RequestMapping(name = "/back-book",method = RequestMethod.POST)
    public BookBase getBook(String name){
        return new BookBase(name,18,"wkingsir-publisher","俞敏洪");
    }
}
