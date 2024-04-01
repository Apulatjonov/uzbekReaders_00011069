package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "score")
    private Long score;
    @OneToMany(mappedBy = "book")
    @Column(name = "comments")
    private List<Comment> comments;
    @ManyToMany
//    @JoinColumn(name = "customers", referencedColumnName = "books")
    private Set<User> customers;

}
