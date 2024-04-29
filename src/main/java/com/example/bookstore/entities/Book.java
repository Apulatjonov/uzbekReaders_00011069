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

    @Column(name = "authorName")
    private String authorName;

    @ManyToOne
    private Author author;

    @Column(name = "title")
    private String title;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    private Category category;

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
    private Set<User> customers;

    @Column(name = "published_date")
    private LocalDateTime publishedDate = LocalDateTime.now();

    @Column(name = "total_page")
    private Long totalPages;

    @OneToMany
    private Set<ReaderBook> readerBook;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Transaction> transactions;

    @OneToOne
    private BookStats stats;

    public BaseBook toBaseBook() {
        BaseBook book = new BaseBook();
        book.setId(this.getId());
        book.setCategoryName(this.getCategory().getCategory());
        book.setAuthorName(this.getAuthorName());
        book.setTitle(this.getTitle());
        book.setPrice(this.getPrice());
        book.setImgUrl(this.getImgUrl());
        return book;
    }
}
