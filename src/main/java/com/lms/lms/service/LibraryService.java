package com.lms.lms.service;


import org.springframework.stereotype.Service;

import com.lms.lms.entity.Book;
import com.lms.lms.entity.Member;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.LoanRepository;
import com.lms.lms.repository.MemberRepository;

import java.util.List;

@Service
public class LibraryService {
     private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    public LibraryService(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
