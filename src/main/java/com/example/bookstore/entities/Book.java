package com.example.bookstore.entities;

import com.example.bookstore.models.BaseBook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 00:26
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "score")
    private Long score;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "book")
    @Column(name = "comments")
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customers", referencedColumnName = "books")
    private Set<User> customers;
    @Column(name = "published_date")
    private LocalDateTime publishedDate = LocalDateTime.now();

    public BaseBook toBaseBook() {
        BaseBook book = new BaseBook();
        book.setId(this.getId());
        book.setCategory(this.getCategory());
        book.setAuthor(this.getAuthor());
        book.setTitle(this.getAuthor());
        book.setPrice(this.getPrice());
        book.setImgUrl(this.getImgUrl());
        return book;
    }
}
