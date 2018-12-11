package com.baijz.bean;

import java.io.Serializable;

/**
 * @Author : Administrator
 * @Date : 2018/12/3 22 19
 * @Description :
 */
public class BookBase implements Serializable {

    private String name;

    private int age;

    private String publisher;

    private String author;

    public BookBase(){

    }

    public BookBase(String name, int age, String publisher, String author){
        this.name = name;
        this.age = age;
        this.publisher = publisher;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
