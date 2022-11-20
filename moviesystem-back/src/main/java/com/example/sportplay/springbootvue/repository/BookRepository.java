package com.example.sportplay.springbootvue.repository;



import com.example.sportplay.springbootvue.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update book set num=num+1 where name=?",nativeQuery = true)
    int updataBook(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="insert into book set name=?",nativeQuery = true)
    int insertBook(String name);

}