package com.lms.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lms.lms.entity.Author;
import com.lms.lms.entity.Book;
import com.lms.lms.entity.Loan;
import com.lms.lms.entity.Member;
import com.lms.lms.repository.AuthorRepository;
import com.lms.lms.repository.BookRepository;
import com.lms.lms.repository.LoanRepository;
import com.lms.lms.repository.MemberRepository;
import com.lms.lms.util.ResponseStructure;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoanRepository loanRepository;

    // ************************************************* */
    @PostMapping("/authors")
    public ResponseEntity<ResponseStructure<Author>> saveAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Author saved successfully", savedAuthor));
        
    }

    @GetMapping("/authors")
    public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return ResponseEntity.ok(new ResponseStructure<>(200, "Authors retrieved successfully", authors));
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<ResponseStructure<Author>> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(a -> ResponseEntity.ok(new ResponseStructure<>(200, "Author retrieved successfully", a)))
                .orElseGet(
                        () -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Author not found", null)));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<ResponseStructure<Author>> updateAuthor(@PathVariable Long id,
            @RequestBody Author updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            Author savedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(new ResponseStructure<>(200, "Author updated successfully", savedAuthor));
        }).orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Author not found", null)));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Author deleted successfully", null));
    }

    @PostMapping("/books")
    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Book saved successfully", savedBook));
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(new ResponseStructure<>(200, "Books retrieved successfully", books));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(b -> ResponseEntity.ok(new ResponseStructure<>(200, "Book retrieved successfully", b)))
                .orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Book not found", null)));
    }

    @GetMapping("/books/genre/{genre}")
    public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookRepository.findAll().stream().filter(b -> b.getGenre().equalsIgnoreCase(genre)).toList();
        return ResponseEntity.ok(new ResponseStructure<>(200, "Books retrieved by genre successfully", books));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<ResponseStructure<Book>> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setGenre(updatedBook.getGenre());
            book.setAuthor(updatedBook.getAuthor());
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.ok(new ResponseStructure<>(200, "Book updated successfully", savedBook));
        }).orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Book not found", null)));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Book deleted successfully", null));
    }

    @PostMapping("/members")
    public ResponseEntity<ResponseStructure<Member>> saveMember(@RequestBody Member member) {
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Member saved successfully", savedMember));
    }

    @GetMapping("/members")
    public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(new ResponseStructure<>(200, "Members retrieved successfully", members));
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<ResponseStructure<Member>> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(m -> ResponseEntity.ok(new ResponseStructure<>(200, "Member retrieved successfully", m)))
                .orElseGet(
                        () -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Member not found", null)));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<ResponseStructure<Member>> updateMember(@PathVariable Long id,
            @RequestBody Member updatedMember) {
        return memberRepository.findById(id).map(member -> {
            member.setName(updatedMember.getName());
            member.setEmail(updatedMember.getEmail());
            member.setPhone(updatedMember.getPhone());
            Member savedMember = memberRepository.save(member);
            return ResponseEntity.ok(new ResponseStructure<>(200, "Member updated successfully", savedMember));
        }).orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Member not found", null)));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Member deleted successfully", null));
    }

    @PostMapping("/loans")
    public ResponseEntity<ResponseStructure<Loan>> saveLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Loan saved successfully", savedLoan));
    }

    @GetMapping("/loans")
    public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return ResponseEntity.ok(new ResponseStructure<>(200, "Loans retrieved successfully", loans));
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<ResponseStructure<Loan>> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.map(l -> ResponseEntity.ok(new ResponseStructure<>(200, "Loan retrieved successfully", l)))
                .orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Loan not found", null)));
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<ResponseStructure<Loan>> updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan) {
        return loanRepository.findById(id).map(loan -> {
            loan.setLoanDate(updatedLoan.getLoanDate());
            loan.setReturnDate(updatedLoan.getReturnDate());
            loan.setMember(updatedLoan.getMember());
            loan.setBook(updatedLoan.getBook());
            Loan savedLoan = loanRepository.save(loan);
            return ResponseEntity.ok(new ResponseStructure<>(200, "Loan updated successfully", savedLoan));
        }).orElseGet(() -> ResponseEntity.status(404).body(new ResponseStructure<>(404, "Loan not found", null)));
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteLoan(@PathVariable Long id) {
        loanRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseStructure<>(200, "Loan deleted successfully", null));
    }

    // ----------------------------------------------------------------------
    /*
     * // Author Endpoints
     * 
     * @PostMapping("/authors")
     * public Author saveAuthor(@RequestBody Author author) {
     * return authorRepository.save(author);
     * }
     * 
     * @GetMapping("/authors")
     * public List<Author> getAllAuthors() {
     * return authorRepository.findAll();
     * }
     * 
     * @GetMapping("/authors/{id}")
     * public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
     * Optional<Author> author = authorRepository.findById(id);
     * return author.map(ResponseEntity::ok).orElseGet(() ->
     * ResponseEntity.notFound().build());
     * }
     * 
     * @PutMapping("/authors/{id}")
     * public ResponseEntity<Author> updateAuthor(@PathVariable Long
     * id, @RequestBody Author updatedAuthor) {
     * return authorRepository.findById(id).map(author -> {
     * author.setName(updatedAuthor.getName());
     * return ResponseEntity.ok(authorRepository.save(author));
     * }).orElseGet(() -> ResponseEntity.notFound().build());
     * }
     * 
     * @DeleteMapping("/authors/{id}")
     * public void deleteAuthor(@PathVariable Long id) {
     * authorRepository.deleteById(id);
     * }
     * 
     * // Book Endpoints
     * 
     * @PostMapping("/books")
     * public Book saveBook(@RequestBody Book book) {
     * return bookRepository.save(book);
     * }
     * 
     * @GetMapping("/books")
     * public List<Book> getAllBooks() {
     * return bookRepository.findAll();
     * }
     * 
     * @GetMapping("/books/{id}")
     * public ResponseEntity<Book> getBookById(@PathVariable Long id) {
     * Optional<Book> book = bookRepository.findById(id);
     * return book.map(ResponseEntity::ok).orElseGet(() ->
     * ResponseEntity.notFound().build());
     * }
     * 
     * @GetMapping("/books/genre/{genre}")
     * public List<Book> getBooksByGenre(@PathVariable String genre) {
     * return bookRepository.findAll().stream().filter(book ->
     * book.getGenre().equalsIgnoreCase(genre)).toList();
     * }
     * 
     * @PutMapping("/books/{id}")
     * public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody
     * Book updatedBook) {
     * return bookRepository.findById(id).map(book -> {
     * book.setTitle(updatedBook.getTitle());
     * book.setGenre(updatedBook.getGenre());
     * book.setAuthor(updatedBook.getAuthor());
     * return ResponseEntity.ok(bookRepository.save(book));
     * }).orElseGet(() -> ResponseEntity.notFound().build());
     * }
     * 
     * @DeleteMapping("/books/{id}")
     * public void deleteBook(@PathVariable Long id) {
     * bookRepository.deleteById(id);
     * }
     * 
     * 
     * // Member Endpoints
     * 
     * @PostMapping("/members")
     * public Member saveMember(@RequestBody Member member) {
     * return memberRepository.save(member);
     * }
     * 
     * @GetMapping("/members")
     * public List<Member> getAllMembers() {
     * return memberRepository.findAll();
     * }
     * 
     * @GetMapping("/members/{id}")
     * public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
     * Optional<Member> member = memberRepository.findById(id);
     * return member.map(ResponseEntity::ok).orElseGet(() ->
     * ResponseEntity.notFound().build());
     * }
     * 
     * @PutMapping("/members/{id}")
     * public ResponseEntity<Member> updateMember(@PathVariable Long
     * id, @RequestBody Member updatedMember) {
     * return memberRepository.findById(id).map(member -> {
     * member.setName(updatedMember.getName());
     * member.setEmail(updatedMember.getEmail());
     * member.setPhone(updatedMember.getPhone());
     * return ResponseEntity.ok(memberRepository.save(member));
     * }).orElseGet(() -> ResponseEntity.notFound().build());
     * }
     * 
     * @DeleteMapping("/members/{id}")
     * public void deleteMember(@PathVariable Long id) {
     * memberRepository.deleteById(id);
     * }
     * 
     * // Loan Endpoints
     * 
     * @PostMapping("/loans")
     * public Loan saveLoan(@RequestBody Loan loan) {
     * return loanRepository.save(loan);
     * }
     * 
     * @GetMapping("/loans")
     * public List<Loan> getAllLoans() {
     * return loanRepository.findAll();
     * }
     * 
     * @GetMapping("/loans/{id}")
     * public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
     * Optional<Loan> loan = loanRepository.findById(id);
     * return loan.map(ResponseEntity::ok).orElseGet(() ->
     * ResponseEntity.notFound().build());
     * }
     * 
     * @PutMapping("/loans/{id}")
     * public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody
     * Loan updatedLoan) {
     * return loanRepository.findById(id).map(loan -> {
     * loan.setLoanDate(updatedLoan.getLoanDate());
     * loan.setReturnDate(updatedLoan.getReturnDate());
     * loan.setMember(updatedLoan.getMember());
     * loan.setBook(updatedLoan.getBook());
     * return ResponseEntity.ok(loanRepository.save(loan));
     * }).orElseGet(() -> ResponseEntity.notFound().build());
     * }
     * 
     * @DeleteMapping("/loans/{id}")
     * public void deleteLoan(@PathVariable Long id) {
     * loanRepository.deleteById(id);
     * }
     * 
     */
}
