package com.example.jack.bean;

import java.util.List;

/**
 * Created by Jack on 16/6/16.
 */
public class Book {

    private String name;
    private List<Author> author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}