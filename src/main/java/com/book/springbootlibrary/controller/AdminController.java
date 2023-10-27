package com.book.springbootlibrary.controller;

import com.book.springbootlibrary.requestmodels.AddBookRequest;
import com.book.springbootlibrary.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("https://localhost:3000")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(JwtAuthenticationToken token, @RequestBody AddBookRequest addBookRequest) throws Exception{
        String admin = (String)token.getToken().getClaims().get("userType");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception{
        String admin = (String)token.getToken().getClaims().get("userType");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception{
        String admin = (String)token.getToken().getClaims().get("userType");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        adminService.decreaseBookQuantity(bookId);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(JwtAuthenticationToken token, @RequestParam Long bookId) throws Exception{
        String admin = (String)token.getToken().getClaims().get("userType");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
    }

}
