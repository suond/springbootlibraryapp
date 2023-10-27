package com.book.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    private String author;
    private String description;

    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;

    private String category;

    @Column(name="img")
    private String img;
}
