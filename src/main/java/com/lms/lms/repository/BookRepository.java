package com.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>  {
    
}
