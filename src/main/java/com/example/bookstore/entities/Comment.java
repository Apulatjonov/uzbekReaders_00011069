package com.example.bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "author")
    private String user;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "comments")
    private Book book;
}
