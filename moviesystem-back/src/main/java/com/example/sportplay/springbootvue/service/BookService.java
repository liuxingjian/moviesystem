package com.example.sportplay.springbootvue.service;




import com.example.sportplay.springbootvue.bean.Book;

import java.util.List;

public interface BookService {

    // 查询所有种类电影浏览量信息
     List<Book> selectAllBooks();
     int updataBook(String name);
     int insertBook(String name);
}