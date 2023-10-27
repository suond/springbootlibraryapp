package com.book.springbootlibrary.requestmodels;

import lombok.Data;

@Data
public class AddBookRequest {
    private String title;

    private String author;

    private String description;

    private int copies;
    private String category;
    private String img;
}
//public record AddBookRequest(String title, String author, int copies, String category, String img) {
//}