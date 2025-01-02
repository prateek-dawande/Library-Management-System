package com.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lms.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    
}
