package com.example.bookstore.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/23/2024 00:29
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "author")
    private String user;
    @Column(name = "body")
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments")
    private Book book;
    @Column(name = "user_id")
    private Long userId;
}
