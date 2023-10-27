package com.book.springbootlibrary.controller;

import com.book.springbootlibrary.entity.Book;
import com.book.springbootlibrary.responsemodels.ShelfCurrentLoansResponse;
import com.book.springbootlibrary.service.BookService;
import com.book.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;
    private final String sub = "\"sub\"";
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/secure/currentloans")
    public List<ShelfCurrentLoansResponse> currentLoans(JwtAuthenticationToken token) throws Exception{
        String userEmail = token.getToken().getSubject();
        return bookService.currentLoans(userEmail);
    }



    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(JwtAuthenticationToken token, @RequestParam Long bookId){
        String userEmail = token.getToken().getSubject();
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount(JwtAuthenticationToken token){
        String userEmail = token.getToken().getSubject();
        return bookService.currentLoansCount(userEmail);
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook (JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception {
        String userEmail = token.getToken().getSubject();
        return bookService.checkoutBook(userEmail, bookId);
    }

    @PutMapping("/secure/return")
    public void returnBook(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception {
        String userEmail = token.getToken().getSubject();
        bookService.returnBook(userEmail,bookId);
    }

    @PutMapping("/secure/renew/loan")
    public void renewLoans(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception{
        bookService.renewLoan(token.getToken().getSubject(), bookId);
    }


}
