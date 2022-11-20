package com.example.sportplay.springbootvue.controller;


import com.example.sportplay.springbootvue.bean.Book;
import com.example.sportplay.springbootvue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查询所有
     * @return 所有浏览量信息
     */
    @GetMapping(value = "/select")
    public Map<String, List<Book>> select() {
        Map<String, List<Book>> map = new HashMap<>();
        map.put("Books", bookService.selectAllBooks());
        return map;
    }
}