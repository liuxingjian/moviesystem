package com.example.sportplay.springbootvue.impl;


import com.example.sportplay.springbootvue.bean.Book;
import com.example.sportplay.springbootvue.repository.BookRepository;
import com.example.sportplay.springbootvue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceimpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> selectAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public int updataBook(String name) {
        return bookRepository.updataBook(name);
    }

    @Override
    public int insertBook(String name) {
        return bookRepository.insertBook(name);
    }


}